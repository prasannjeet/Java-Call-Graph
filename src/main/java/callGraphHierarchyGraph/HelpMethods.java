package callGraphHierarchyGraph;

import org.apache.commons.lang3.ArrayUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;
import ps222vt.DirectedGraph;
import ps222vt.MyNode;
import ps222vt.Node;
import ps222vt.HelpClass;
import spoon.SpoonAPI;
import spoon.compiler.ModelBuildingException;
import spoon.reflect.code.CtLoop;
import spoon.reflect.declaration.*;
import spoon.reflect.visitor.chain.CtQuery;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtClassImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HelpMethods {
    public static Set<String> getAllClasses(Collection<CtPackage> allPackages) {
        Iterator<CtPackage> packIterator = allPackages.iterator();
        HashSet<String> allClasses = new HashSet<>();
        while (packIterator.hasNext()) {
            CtPackage curPackage = packIterator.next();
            CtQuery classQuery = curPackage.filterChildren(new TypeFilter(CtClass.class));
            List<Object> classObject = classQuery.list();
            Iterator<Object> classIterator = classObject.iterator();
            while (classIterator.hasNext()) {
                CtClass currentClass = (CtClass) classIterator.next();
                if (!currentClass.isAnonymous()) allClasses.add(currentClass.getQualifiedName());
            }
        }
        return allClasses;
    }

    public static Set<String> getAllInterfaces(Collection<CtPackage> allPackages) {
        Iterator<CtPackage> packIterator = allPackages.iterator();
        HashSet<String> allInterface = new HashSet<>();
        while (packIterator.hasNext()) {
            CtPackage curPackage = packIterator.next();
            CtQuery interfaceQuery = curPackage.filterChildren(new TypeFilter(CtInterface.class));
            List<Object> interfaceObject = interfaceQuery.list();
            Iterator<Object> interfaceIterator = interfaceObject.iterator();
            while (interfaceIterator.hasNext()) {
                CtInterface currentInterface = (CtInterface) interfaceIterator.next();
                allInterface.add(currentInterface.getQualifiedName());
            }
        }
        return allInterface;
    }

    public static Set<CtInterface> getAllInterfaceObjects(Collection<CtPackage> allPackages) {
        Iterator<CtPackage> packIterator = allPackages.iterator();
        HashSet<CtInterface> allInterface = new HashSet<>();
        while (packIterator.hasNext()) {
            CtPackage curPackage = packIterator.next();
            CtQuery interfaceQuery = curPackage.filterChildren(new TypeFilter(CtInterface.class));
            Iterator<Object> interfaceIterator = interfaceQuery.list().iterator();
            while (interfaceIterator.hasNext()) {
                CtInterface currentInterface = (CtInterface) interfaceIterator.next();
                allInterface.add(currentInterface);
            }
        }
        return allInterface;
    }

    public static Set<CtClass> getAllClassObjects(Collection<CtPackage> allPackages) {
        Iterator<CtPackage> packIterator = allPackages.iterator();
        HashSet<CtClass> allClasses = new HashSet<>();
        while (packIterator.hasNext()) {
            CtPackage curPackage = packIterator.next();
            List<Object> classObject = curPackage.filterChildren(new TypeFilter(CtClass.class)).list();
            Iterator<Object> classIterator = classObject.iterator();
            while (classIterator.hasNext()) {
                CtClass currentClass = (CtClass) classIterator.next();
                if (!currentClass.isAnonymous()) allClasses.add(currentClass);
            }
        }
        return allClasses;
    }

    public static Set<CtMethod> getAllProgramMethods(Set<CtClass> allClasses, Set<CtInterface> allInterfaces){
        Set<CtMethod> allProgramMethods = new HashSet<>();
        for (CtClass theClass: allClasses){
            Collection<CtMethod> allMethods = theClass.getMethods();
            allProgramMethods.addAll(allMethods);
        }
        for (CtInterface theInterface: allInterfaces){
            Collection<CtMethod> allMethods = theInterface.getMethods();
            allProgramMethods.addAll(allMethods);
        }
        return allProgramMethods;
    }

    public static String getQualifiedMethodName (Object obj){

        CtMethod theMethod = null;
        if (obj instanceof CtMethod) {
            theMethod = (CtMethod) obj;
            Object theParent = theMethod.getParent();
            if (theParent instanceof CtClass){
                CtClass theClass = (CtClass) theParent;
                return theClass.getQualifiedName() + "." + theMethod.getSimpleName();
            }
            else if (theParent instanceof CtInterface){
                CtInterface theClass = (CtInterface) theParent;
                return theClass.getQualifiedName() + "." + theMethod.getSimpleName();
            }
            else {
                return "unreadableMethodName";
            }
        }
        else if (obj instanceof CtConstructor) {
            CtConstructor theConstructor = (CtConstructor) obj;
            Object theParent = theConstructor.getParent();
            CtClass theClass = (CtClass) theParent;
            return theClass.getQualifiedName() + "." + theConstructor.getSimpleName();
        }
        else return "unknownError";
        //CtClass theClass = (CtClass) theMethod.getParent();
    }

    public static String getQualifiedConstructorName (Object obj, Set<CtClass> allClasses){
        if (obj instanceof CtConstructor) {
            CtConstructor theConstructor = (CtConstructor) obj;
            Object theParent = theConstructor.getParent();
            CtClass theClass = (CtClass) theParent;
            if (allClasses.contains(theClass)){
                return theClass.getQualifiedName() + "." + theConstructor.getSimpleName();
            }
            return null;
        }
        else return null;
    }

    public static int getLoopDepth(CtMethod theMethod){
        List<Object> allLoops = theMethod.filterChildren(new TypeFilter(CtLoop.class)).list();
        if (allLoops.size() == 0) return 0;
        int[] depth = new int[allLoops.size()];
        Arrays.fill(depth, 0);
        for (int i=0; i<allLoops.size(); i++){
            CtLoop currentLoop = (CtLoop) allLoops.get(i);
            depth[i] = getLoopDepth(currentLoop, 0);
        }
        IntSummaryStatistics stat = Arrays.stream(depth).summaryStatistics();
        return  stat.getMax()+1;
    }

    private static int getLoopDepth (CtLoop theLoop, int theDepth){
        List<Object> list = theLoop.getBody().filterChildren(new TypeFilter<>(CtLoop.class)).list();
        if (list.size() == 0) return theDepth;
        int[] depth = new int[list.size()];
        Arrays.fill(depth, 1);
        for (int i = 0; i<list.size(); i++){
            CtLoop thisLoop = (CtLoop)list.get(i);
            depth[i] = getLoopDepth(thisLoop, depth[i]);
        }
        IntSummaryStatistics stat = Arrays.stream(depth).summaryStatistics();
        return  stat.getMax()+theDepth;
    }

    static void buildSpoon(SpoonAPI spoon) {
        try {
            spoon.buildModel();
        } catch (ModelBuildingException e) {
            System.out.println("EXCEPTION: Cannot create the call graph, as one of the class in the target project has the same name as one of the classes in this project.\n" +
                    "Read the stack trace below for more information.\n" +
                    "The program will now terminate.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    static CtMethod getMainCtMethod(String[] args, Collection<CtMethod<Void>> mainMethods, CtMethod mainMethod) {
        if (mainMethods.size() > 1) {
            if (args.length == 1) {
                mainMethod = (CtMethod) mainMethods.toArray()[0];
                System.err.println("A total of " + mainMethods.size() + " main methods found. Using the first method as main.");
                System.out.println("The main method used is: " + HelpMethods.getQualifiedMethodName(mainMethod));
            } else {
                System.out.println("A total of " + mainMethods.size() + " main methods found. Using the second argument parameter to find out which main method to use.\n" +
                        "Argument format: 1: Use first main method. 2: Use 2nd main method. and so on...");
                try {
                    int methodIndex = Integer.parseInt(args[1]) - 1;
                    mainMethod = (CtMethod) mainMethods.toArray()[methodIndex];
                    System.out.println("The main method used is: " + HelpMethods.getQualifiedMethodName(mainMethod));
                } catch (Exception e) {
                    System.out.println("Exception in reading the second argument / Invalid second argument. The first main method will be used.");
                    mainMethod = (CtMethod) mainMethods.toArray()[0];
                }
            }

        } else if (mainMethods.size() == 0) {
            System.err.println("Error: Couldn't find any main method in the package. The program will terminate.");
            System.exit(0);
        } else {
            mainMethod = (CtMethod) mainMethods.toArray()[0];
            System.out.println("The main method used is: " + HelpMethods.getQualifiedMethodName(mainMethod));
        }
        return mainMethod;
    }

    static String getParentClassIgnoreInner (CtMethod theMethod){
//       Object obj = (theMethod.getParent() instanceof CtClass || theMethod.getParent() instanceof CtClassImpl) ? (  ((CtClass)theMethod.getParent()).isTopLevel() ? ((CtClass)theMethod.getParent()) : ((CtClass)((CtClass)theMethod.getParent()).getTopLevelType())) : (  ((CtInterface)theMethod.getParent()).isTopLevel() ? ((CtInterface)theMethod.getParent()) : ((CtInterface)((CtInterface)theMethod.getParent()).getTopLevelType()));
        Object obj = null;
       if (theMethod.getParent() instanceof  CtClass) {
           if (((CtClass)theMethod.getParent()).isTopLevel()){
               obj = theMethod.getParent();
           }
           else obj = (((CtClass)theMethod.getParent()).getTopLevelType());
       }
       else {
           if (((CtInterface)theMethod.getParent()).isTopLevel()){
               obj = theMethod.getParent();
           }
           else obj = (((CtInterface)theMethod.getParent()).getTopLevelType());
       }

       return  obj instanceof CtClass ? ((CtClass)obj).getQualifiedName() : ((CtInterface)obj).getQualifiedName();
    }

    static DirectedGraph<String> returnUndirectedGraph (DirectedGraph<String> dg) {
        HelpClass<String> helpclass = new HelpClass<>();
        DirectedGraph<String> copyGraph = helpclass.copyDG(dg);
        List<Node<String>> addEdges = helpclass.nodesAsList(copyGraph);
        for (Node<String> tempEdgeNode : addEdges) {
            List<Node<String>> tempPred = helpclass.predAsList(tempEdgeNode);
            for (Node<String> eachPred : tempPred) {
                copyGraph.addEdgeFor(tempEdgeNode.item(), eachPred.item());
//                int x = edgeCount.get(eachPred.item()).get(tempEdgeNode.item());
            }
        }
        return copyGraph;
    }

    static Graph<String, Integer> convertToJgraph (DirectedGraph<String> dg){
        Graph<String, Integer> theGraph = new SimpleDirectedGraph<>(Integer.class);
        Iterator<Node<String>> nodeIterator = dg.iterator();
        int edgeID = 0;
        while (nodeIterator.hasNext()){
            Node<String> theNode = nodeIterator.next();
            theGraph.addVertex(theNode.item());
            Iterator<Node<String>> succIterator = theNode.succsOfUnsorted();
            while (succIterator.hasNext()){
                Node<String> theSuccessor = succIterator.next();
                theGraph.addVertex(theSuccessor.item());
                if((!theGraph.containsEdge(theNode.item(), theSuccessor.item())) || !theGraph.containsEdge(theSuccessor.item(), theNode.item())){
                    theGraph.removeEdge(theNode.item(), theSuccessor.item());
                    theGraph.addEdge(theNode.item(), theSuccessor.item(), edgeID);
                    edgeID++;
                }
            }
        }
        return theGraph;
    }

    static Graph<String, Integer> makeNClusters(Graph<String, Integer> theGraph, int totalClusters) {
        if (totalClusters > theGraph.vertexSet().size()) {
            System.err.println("Error: Total number of clusters cannot be more than total number of nodes. Program will now terminate.");
            System.exit(-1);
        }
        String endl = System.lineSeparator();
        Set<Integer> edgeSet = theGraph.edgeSet();
        ICombinatoricsVector<Integer> setComb = createVector(edgeSet);
        Graph<String, Integer> duplicateGraph = null;
        StringBuilder EdgeRemovalNotes = new StringBuilder();
        do {
            int count = 1;
            do {
                int totalComponents = calculateConnectivity(theGraph);
                duplicateGraph = (Graph<String, Integer>)((AbstractBaseGraph<String, Integer>) theGraph).clone();
                Generator<Integer> gen = createSimpleCombinationGenerator(setComb, count);
                Iterator<ICombinatoricsVector<Integer>> firstIterator = gen.iterator();
                while (firstIterator.hasNext()) {
                    Set<Integer> theIntSet = returnIntSet(firstIterator.next());
                    for (int e: theIntSet) {
                        duplicateGraph.removeEdge(e);
                    }
                    if (calculateConnectivity(duplicateGraph) > totalComponents) {
                        EdgeRemovalNotes.append("Successfully increased the cluster size to: "+calculateConnectivity(duplicateGraph)+endl+
                                "Following edges were removed in this removal: "+endl);

                        for (int e: theIntSet) {
                            EdgeRemovalNotes.append("From => "+theGraph.getEdgeSource(e)+" TO=> "+theGraph.getEdgeTarget(e)+endl);
                        }
                        EdgeRemovalNotes.append("Total Edges Removed: "+theIntSet.size()+endl+endl+endl);
                        break;
                    }
                    else {
                        duplicateGraph = (Graph<String, Integer>)((AbstractBaseGraph<String, Integer>) theGraph).clone();
                    }
                }
                if (calculateConnectivity(duplicateGraph) > totalComponents) break;
                else count++;
            } while (true);
            theGraph = duplicateGraph;
        } while (calculateConnectivity(theGraph) < totalClusters);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("EdgeRemovalLog.log"))) {
            out.write(EdgeRemovalNotes.toString());
        } catch (IOException e) {
            System.err.println("IO Exception Has Occurred In Saving EdgeRemovalLog.log");
        } finally {
            System.out.println("Created the log file for removing edges. Saved as EdgeRemovalLog.log in root project folder.");
        }

        return duplicateGraph;
    }

    static Set<Integer> returnIntSet (ICombinatoricsVector<Integer> tempVar) {
        Set<Integer> returnItem = new HashSet<>();
        Iterator<Integer> theIterator = tempVar.iterator();
        while (theIterator.hasNext()) {
            returnItem.add(theIterator.next());
        }
        return returnItem;
    }

    static int calculateConnectivity (Graph<String, Integer> theGraph) {
        ConnectivityInspector<String, Integer> connectivityInspector = new ConnectivityInspector<>(theGraph);
       return connectivityInspector.connectedSets().size();
    }
}
