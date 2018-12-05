package classComplexity;

import callGraphHierarchyGraph.HelpMethods;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.compiler.ModelBuildingException;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.reference.CtExecutableReferenceImpl;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


import java.util.Collection;
import java.util.Set;
import java.util.stream.IntStream;

public class WMC {
    public static void main (String[] args) {
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource(args[0]);

        try {
            spoon.buildModel();
        } catch (ModelBuildingException e) {
            System.out.println("EXCEPTION: Cannot proceed, as one of the class in the target project has the same name as one of the classes in this project.\n" +
                    "Read the stack trace below for more information.\n" +
                    "The program will now terminate.");
            e.printStackTrace();
            System.exit(0);
        }

        //Getting the list of all the packages
        Collection<CtPackage> allPackages = spoon.getModel().getRootPackage().getPackages();
        //Getting the list of all the Classes
        Set<CtClass> allClasses = HelpMethods.getAllClassObjects(allPackages);
        Set<CtMethod> allProgramMethods = HelpMethods.getAllProgramMethods(allClasses, HelpMethods.getAllInterfaceObjects(allPackages));

        Map<CtClass, Integer> WMCData = new HashMap<>();
        XYSeriesCollection theDataSet = new XYSeriesCollection();
        XYSeries theSeries = new XYSeries("Classes");

        StringBuilder stringBuilder = new StringBuilder("FullQualifiedName,Name,WMC,RFC\n");
        for (CtClass theClass: allClasses){
            Collection<CtMethod> allMethods = theClass.getMethods();
            int[] methodDepth = new int[allMethods.size()];
            int i = 0;
            for (CtMethod theMethod: allMethods){
               methodDepth[i++] = HelpMethods.getLoopDepth(theMethod);
//                System.out.println(theMethod.getSimpleName()+" "+methodDepth[i-1]);
//                System.out.println("===============================================");
            }
            int theClassWCM = IntStream.of(methodDepth).sum();
            int theClassRFC = getRFC(theClass, allProgramMethods);
            WMCData.put(theClass, theClassWCM);
            stringBuilder.append(theClass.getQualifiedName()).append(",").append(theClass.getSimpleName()).append(",").append(theClassWCM).append(",").append(theClassRFC).append("\n");
            theSeries.add(theClassWCM, theClassRFC);
        }
        theDataSet.addSeries(theSeries);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("WMC_LND_Values.csv"))) {
            out.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("IO Exception Has Occurred in Saving WMC_LND_Values.csv");
            System.exit(-1);
        } finally {
            System.out.println("Created CSV file containing WMC values for all the methods. Saved as WMC_LND_Values.csv in root project folder.");
        }

        SwingUtilities.invokeLater(() -> {
            PlotTheData plotTheData = null;
            try {
                plotTheData = new PlotTheData("Assignment 2: Submitted by Prasannjeet Singh", theDataSet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert plotTheData != null;
            plotTheData.setSize(800, 400);
            plotTheData.setLocationRelativeTo(null);
            plotTheData.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            plotTheData.setVisible(true);
        });

    }

    private static int getRFC(CtClass theClass, Set<CtMethod> allProgramMethods){
        Collection<CtMethod> allMethods = theClass.getMethods();
        Set<CtMethod> childMethodSet = new HashSet<>();
        for (CtMethod theMethod: allMethods){
            if (!theMethod.isPrivate()){
                childMethodSet.add(theMethod);
                List<Object> allCalledMethods = theMethod.filterChildren(new TypeFilter(CtInvocation.class)).map((CtInvocation inv) -> inv.getExecutable()).list();
                for (Object loopObject : allCalledMethods) {
                    CtExecutableReferenceImpl loopTemp = (CtExecutableReferenceImpl) loopObject;
                    if (loopTemp.getExecutableDeclaration() instanceof CtMethod){
                        CtMethod loopMethod = (CtMethod) loopTemp.getExecutableDeclaration();
                        if (allProgramMethods.contains(loopMethod)) {
                            childMethodSet.add(loopMethod);
                        }
                    }
                }
            }
        }
        return childMethodSet.size();
    }
}
