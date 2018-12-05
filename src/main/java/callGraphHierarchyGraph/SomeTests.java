package callGraphHierarchyGraph;

import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtConstructorCallImpl;
import spoon.support.reflect.declaration.CtClassImpl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SomeTests {
    //Checking how spoon handles inner classes
    public static void main (String[] args){
        String path = "D:\\OneDrivePs222vt\\OneDrive - student.lnu.se\\Development\\Eclipse\\Workspace\\AppliedProgramAnalysis\\src\\main\\java\\testWCM";
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource(path);
        spoon.buildModel();
        Collection<CtPackage> allPackages = spoon.getModel().getRootPackage().getPackages();
        System.out.println("Size: "+allPackages.size());
        //Getting the list of all the classes.
        Set<CtClass> allClassObjects = HelpMethods.getAllClassObjects(allPackages);
        for (CtClass loopClass: allClassObjects){
            System.out.println(loopClass.getQualifiedName()+" "+loopClass.isTopLevel()+" "+((CtClass)loopClass.getTopLevelType()).getQualifiedName());
        }
        CtPackage thePackage = null;
        for (CtPackage loopPackage: allPackages) thePackage = loopPackage;
        List<Object> objectList = thePackage.filterChildren(new TypeFilter<>(CtMethod.class)).list();
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        for (Object obj: objectList){
            CtMethod loopMethod = ((CtMethod)obj);
            System.out.println(loopMethod.getSimpleName());
            System.out.println((((CtClass)loopMethod.getParent()).getQualifiedName()));
            System.out.println(HelpMethods.getParentClassIgnoreInner(loopMethod));
            System.out.println("===========================================");
        }


    }
}
