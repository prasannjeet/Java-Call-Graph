package root;

import ps222vt.DirectedGraph;
import ps222vt.MyGML;
import ps222vt.MyGraph;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.compiler.ModelBuildingException;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;

import java.io.*;
import java.util.*;

/**
 * Class: ClassHierarchy
 * Creates a class hierarchy graph.
 */
public class ClassHierarchy {
    public static void main (String[] args) throws IOException {
        // Using SPOON API for parsing JAVA files. https://github.com/INRIA/spoon
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource(args[0]);

        try {
            spoon.buildModel();
        } catch (ModelBuildingException e) {
            System.out.println("EXCEPTION: Cannot create the class hierarchy graph, as one of the class in the target project has the same name as one of the classes in this project.\n" +
                    "Read the stack trace below for more information.\n" +
                    "The program will now terminate.");
            e.printStackTrace();
            System.exit(0);
        }

        //Getting the list of all the packages
        Collection<CtPackage> allPackages = spoon.getModel().getRootPackage().getPackages();
        //Getting the list of all the Classes
        Set<String> allClasses = HelpMethods.getAllClasses(allPackages);
        //Getting the list of all the Interfaces
        Set<String> allInterfaces = HelpMethods.getAllInterfaces(allPackages);
        //Creating a Directed Graph, and adding a node for all the Classes and Interfaces available in the Program
        DirectedGraph<String> classHierarchy = new MyGraph<>();
        for (String theClass: allClasses){
            classHierarchy.addNodeFor(theClass);
        }
        for (String theInterface: allInterfaces){
            classHierarchy.addNodeFor(theInterface);
        }

        //For every class, finding the Superclass (if any), and adding an edge between them.
        //Further, also finding the list of all Super Interfaces (if any), and adding an edge between them.
        for (String theClass: allClasses){
            CtTypeReference<?> superClass = spoon.getFactory().Class().get(theClass).getSuperclass();
            if ((superClass != null) && classHierarchy.containsNodeFor(superClass.getQualifiedName())) classHierarchy.addEdgeFor(superClass.getQualifiedName(), theClass);

            Collection<CtTypeReference<?>> superInterface = spoon.getFactory().Class().get(theClass).getSuperInterfaces();
            for (CtTypeReference<?> anInterface: superInterface){
                if(classHierarchy.containsNodeFor(anInterface.getQualifiedName())) classHierarchy.addEdgeFor(anInterface.getQualifiedName(), theClass);
            }
        }

        //Creating GML string and saving it in the file "CLassHierarchy.gml" in the "src" folder.
        MyGML<String> myGML = new MyGML<>(classHierarchy);
        BufferedWriter out = new BufferedWriter(new FileWriter("ClassHierarchy.gml"));
        try {
            out.write(myGML.toGML());
        }
        catch (IOException e)
        {
            System.out.println("IO Exception Has Occurred in Saving ClassHierarchy.gml");
        }
        finally
        {
            out.close();
            System.out.println("Created the Class Hierarchy GML file. Saved as ClassHierarchy.gml in root project folder.");
        }
    }
}