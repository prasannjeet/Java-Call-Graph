package callGraphHierarchyGraph;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.GmlExporter;
import org.jgrapht.io.IntegerComponentNameProvider;
import ps222vt.*;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.reference.CtExecutableReferenceImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ClassUsageGraph {
    private static Set<CtClass> allClassObjects = null;
    public static void main(String[] args) throws IOException {
        // Using SPOON API for parsing JAVA files. https://github.com/INRIA/spoon
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource(args[0]);

        HelpMethods.buildSpoon(spoon);

        //Getting the list of all the packages.
        Collection<CtPackage> allPackages = spoon.getModel().getRootPackage().getPackages();

        //Getting the list of all the classes.
        allClassObjects = HelpMethods.getAllClassObjects(allPackages);

        //Getting the main() method of the program. If more than one main methods are found, the first one will be used
        // with a warning. If no main method is found, the program will exit.
        Collection<CtMethod<Void>> mainMethods = spoon.getFactory().Method().getMainMethods();
        System.out.println(mainMethods.size());
        CtMethod mainMethod = null;
        mainMethod = HelpMethods.getMainCtMethod(args, mainMethods, mainMethod);

        //Finding all the methods in a particular program.
        Set<CtMethod> allProgramMethods = HelpMethods.getAllProgramMethods(allClassObjects, HelpMethods.getAllInterfaceObjects(allPackages));

        //Creating a new directed graph for saving the call graph.
        DirectedGraph<CtMethod> callGraph = new MyGraph<>();

        //Calling the recursive method "constructCallGraph()" to create the call graph.
        callGraph = constructCallGraph(callGraph, mainMethod, allProgramMethods, 0);

        //Initializing a Directed Graph for representing Class Usage. Also saving Edge Labels
        DirectedGraph<String> classUsageGraph = new MyGraph<>();
        //Edge Labels Counts and Class Usage Graph Making
        Iterator<Node<CtMethod>> nodeIterator = callGraph.iterator();
        HashMap<String, HashMap<String,Integer>> edgeCount = new HashMap<>();
        while (nodeIterator.hasNext()){
            Node<CtMethod> currentNode = nodeIterator.next();
            String currentClassName = HelpMethods.getParentClassIgnoreInner(currentNode.item());
            Iterator<Node<CtMethod>> succIterator = currentNode.succsOfUnsorted();
            while (succIterator.hasNext()){
                Node<CtMethod> currentSuccNode = succIterator.next();
                String connectedClassName = HelpMethods.getParentClassIgnoreInner(currentSuccNode.item());
                if(currentClassName.equals(connectedClassName)) continue;
                classUsageGraph.addEdgeFor(currentClassName, connectedClassName);
                if (edgeCount.containsKey(currentClassName)){
                    HashMap<String,Integer> curHashMap = edgeCount.get(currentClassName);
                    if (curHashMap.containsKey(connectedClassName)){
                        int x = curHashMap.get(connectedClassName);
                        x = x+1;
                        curHashMap.put(connectedClassName, x);
                    }
                    else {
                        curHashMap.put(connectedClassName, 1);
                    }
                }
                else {
                    HashMap<String,Integer> curHashMap = new HashMap<>();
                    curHashMap.put(connectedClassName, 1);
                    edgeCount.put(currentClassName, curHashMap);
                }
            }
        }

        //Saving the Original Class Usage Graph with Edge Labels
        MyGML<String> classUsageGraphGML = new MyGML<>(classUsageGraph);
        String filename = "ClassUsage_Original_WithEdgeLabels.gml";
        String fileDescription = "Class Usage Graph GML file with edge labels";
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(classUsageGraphGML.toGMLUndirected(edgeCount));
        } catch (IOException e) {
            System.err.println("IO Exception Has Occurred In Saving "+filename);
        } finally {
            System.out.println("Created the "+fileDescription+". Saved as "+filename+" in root project folder.");
        }


        //Initializing JGraphT for further actions
        Graph<String, Integer> finalGraph = HelpMethods.convertToJgraph(classUsageGraph);

        //Leaf Removal
        int nodeCount = finalGraph.vertexSet().size();
        do {
            Set<String> nodes = finalGraph.vertexSet();
            Set<String> nodesCopy = new HashSet<>();
            if (nodes ==  null) continue;
            nodesCopy.addAll(nodes);
            for (String theNode: nodesCopy) {
                if (finalGraph.degreeOf(theNode) == 1)
                    finalGraph.removeVertex(theNode);
            }
            nodeCount--;
        }while (nodeCount > 0);


        ConnectivityInspector<String, Integer> connectivityInspector = new ConnectivityInspector<>(finalGraph);
//        System.out.println("Total Connectivity: "+connectivityInspector.connectedSets().size());
        GmlExporter<String, Integer> gmlExporter = createExporter();

        Graph<String, Integer> duplicateGraph = HelpMethods.makeNClusters(finalGraph, 5);

        filename = "ClassUsageGraph.gml";
        fileDescription = "Class Usage Graph GML file";
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            gmlExporter.exportGraph(duplicateGraph, out);
        } catch (IOException e) {
            System.err.println("IO Exception Has Occurred In Saving "+filename);
        } finally {
            System.out.println("Created the "+fileDescription+". Saved as "+filename+" in root project folder.");
        }
    }

    /**
     * The recursive method takes three parameters as mentioned below and returns the final call graph.
     *
     * @param callGraph
     * @param mainMethod
     * @param allProgramMethods
     * @return callGraph
     */
    static DirectedGraph<CtMethod> constructCallGraph(DirectedGraph<CtMethod> callGraph, CtMethod mainMethod, Set<CtMethod> allProgramMethods, int i) {
        i++;
        if (i>100) return callGraph;
        //Add a node if this method is a part of the program.
        if (allProgramMethods.contains(mainMethod)){
            callGraph.addNodeFor(mainMethod);

            //Find all the methods called by this method.
            List<Object> allCalledMethods = mainMethod.filterChildren(new TypeFilter(CtInvocation.class)).map((CtInvocation inv) -> inv.getExecutable()).list();
            List<CtMethod> allCalledMethodsNew = new LinkedList<>();
            for (Object loopObject : allCalledMethods){
                CtExecutableReferenceImpl loopTemp = (CtExecutableReferenceImpl) loopObject;
                CtMethod loopMethod = null;
                try {
                    loopMethod = (CtMethod) loopTemp.getExecutableDeclaration();
                } catch (ClassCastException e) {
                    if (HelpMethods.getQualifiedConstructorName(loopTemp.getExecutableDeclaration(), allClassObjects) != null)
                    System.err.println("Warning, ignoring constructor "+HelpMethods.getQualifiedConstructorName(loopTemp.getExecutableDeclaration(), allClassObjects));
                    continue;
                }
                if (allProgramMethods.contains(loopMethod)){
                    if ((HelpMethods.getQualifiedMethodName(loopMethod)).equals(HelpMethods.getQualifiedMethodName(mainMethod))) continue;
                    allCalledMethodsNew.add(loopMethod);
                }
            }
            //For each method that is called by the "current method" (the method called in the parameter --mainmethod"), add
            //an edge, and after that, call the "constructorCallGraph" again for this method.
            for (CtMethod loopMethod : allCalledMethodsNew) {
                if ((HelpMethods.getQualifiedMethodName(loopMethod)).equals(HelpMethods.getQualifiedMethodName(mainMethod))) continue;
//                System.out.println("Main Method: "+HelpMethods.getQualifiedMethodName(mainMethod)+" LoopMethod: "+HelpMethods.getQualifiedMethodName(loopMethod));
                callGraph.addEdgeFor(mainMethod, loopMethod);
                constructCallGraph(callGraph, loopMethod, allProgramMethods,i);
            }
        }
        return callGraph;
    }

    public static GmlExporter<String, Integer> createExporter()
    {
        ComponentNameProvider<String> vertexIdProvider = new IntegerComponentNameProvider<>();;
        ComponentNameProvider<String> vertexLabelProvider = v -> v;
        ComponentNameProvider<Integer> edgeIdProvider = new IntegerComponentNameProvider<>();
        ComponentNameProvider<Integer> edgeLabelProvider = Object::toString;
        GmlExporter<String, Integer> exporter = new GmlExporter<>(
                vertexIdProvider, vertexLabelProvider, edgeIdProvider,
                edgeLabelProvider);
        exporter.setParameter(GmlExporter.Parameter.EXPORT_VERTEX_LABELS, true);
        return exporter;
    }
}
