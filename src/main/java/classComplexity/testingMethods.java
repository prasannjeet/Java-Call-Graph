package classComplexity;

//import callGraphHierarchyGraph.HelpMethods;
//import org.junit.platform.console.shadow.picocli.CommandLine;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.code.CtLoop;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
//import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
//import java.util.Set;

public class testingMethods {
    public static void main (String[] args){
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource("src/main/java/example");
        spoon.buildModel();
        CtClass theClass = spoon.getFactory().Class().get("example.Overload");
        Collection<CtMethod> allMethods = theClass.getMethods();
        for (CtMethod theMethod: allMethods){
            List<Object> allLoops = theMethod.filterChildren(new TypeFilter(CtLoop.class)).list();
            Iterator<Object> loopIterator = allLoops.iterator();
            while (loopIterator.hasNext()){
                CtLoop currentLoop = (CtLoop) loopIterator.next();
                System.out.println(currentLoop.toString());
//                System.out.println(HelpMethods.getLoopDepth(currentLoop, 0));
                System.out.println("=======================================================");

            }
        }

    }
}
