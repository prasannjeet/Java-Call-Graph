package root;

import ps222vt.*;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.compiler.ModelBuildingException;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.*;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtConstructorCallImpl;
import spoon.support.reflect.declaration.CtClassImpl;
import spoon.support.reflect.reference.CtExecutableReferenceImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Class: CallGraph
 * A java program to create a call graph as per the instructions in the assignment.
 */
public class CallGraph {
    public static void main(String[] args) throws IOException {
        // Using SPOON API for parsing JAVA files. https://github.com/INRIA/spoon
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource(args[0]);

        try {
            spoon.buildModel();
        } catch (ModelBuildingException e) {
            System.out.println("EXCEPTION: Cannot create the call graph, as one of the class in the target project has the same name as one of the classes in this project.\n" +
                    "Read the stack trace below for more information.\n" +
                    "The program will now terminate.");
            e.printStackTrace();
            System.exit(0);
        }

        //Getting the list of all the packages.
        Collection<CtPackage> allPackages = spoon.getModel().getRootPackage().getPackages();

        //Getting the list of all the classes.
        Set<CtClass> allClassObjects = HelpMethods.getAllClassObjects(allPackages);

        //Getting the main() method of the program. If more than one main methods are found, the first one will be used
        // with a warning. If no main method is found, the program will exit.
        Collection<CtMethod<Void>> mainMethods = spoon.getFactory().Method().getMainMethods();
        CtMethod mainMethod = null;
        if (mainMethods.size() > 1) {
            if (args.length == 1){
                mainMethod = (CtMethod) mainMethods.toArray()[0];
                System.err.println("A total of "+mainMethods.size()+" main methods found. Using the first method as main.");
                System.out.println("The main method used is: " + HelpMethods.getQualifiedMethodName(mainMethod));
            }
            else {
                System.out.println("A total of "+mainMethods.size()+" main methods found. Using the second argument parameter to find out which main method to use.\n" +
                        "Argument format: 1: Use first main method. 2: Use 2nd main method. and so on...");
                try {
                    int methodIndex = Integer.parseInt(args[1])-1;
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

        //Finding all the methods in a particular program.
        Set<CtMethod> allProgramMethods = HelpMethods.getAllProgramMethods(allClassObjects, HelpMethods.getAllInterfaceObjects(allPackages));

        //Creating a new directed graph for saving the call graph.
        DirectedGraph<String> callGraph = new MyGraph<>();

        //Calling the recursive method "constructCallGraph()" to create the call graph.
        callGraph = constructCallGraph(callGraph, mainMethod, allProgramMethods);
        GML<String> callGraphGML = new MyGML<>(callGraph);
        BufferedWriter out = new BufferedWriter(new FileWriter("CallGraph.gml"));


        try {

            out.write(callGraphGML.toGML());
        } catch (IOException e) {
            System.err.println("IO Exception Has Occurred In Saving CallGraph.gml");
        } finally {
            out.close();
            System.out.println("Created the Call Graph GML file. Saved as CallGraph.gml in root project.");
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
    static DirectedGraph<String> constructCallGraph(DirectedGraph<String> callGraph, CtMethod mainMethod, Set<CtMethod> allProgramMethods) {
        //Add a node if this method is a part of the program.
        if (allProgramMethods.contains(mainMethod))
            callGraph.addNodeFor(HelpMethods.getQualifiedMethodName(mainMethod));

        //Find all the methods called by this method.
        List<Object> allCalledMethods = mainMethod.filterChildren(new TypeFilter(CtInvocation.class)).map((CtInvocation inv) -> inv.getExecutable()).list();

        //For each method that is called by the "current method" (the method called in the parameter --mainmethod"), add
        //an edge, and after that, call the "constructorCallGraph" again for this method.
        for (Object loopObject : allCalledMethods) {
            CtExecutableReferenceImpl loopTemp = (CtExecutableReferenceImpl) loopObject;
            CtMethod loopMethod = (CtMethod) loopTemp.getExecutableDeclaration();
            if (allProgramMethods.contains(loopMethod)) {
                callGraph.addEdgeFor(HelpMethods.getQualifiedMethodName(mainMethod), HelpMethods.getQualifiedMethodName(loopMethod));
                constructCallGraph(callGraph, loopMethod, allProgramMethods);
            }
        }
        return callGraph;
    }
}