package CallGraphHierarchyGraph;

import spoon.reflect.declaration.*;
import spoon.reflect.visitor.chain.CtQuery;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.*;

public class HelpMethods {
    static Set<String> getAllClasses(Collection<CtPackage> allPackages) {
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

    static Set<String> getAllInterfaces(Collection<CtPackage> allPackages) {
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

    static Set<CtInterface> getAllInterfaceObjects(Collection<CtPackage> allPackages) {
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

    static Set<CtClass> getAllClassObjects(Collection<CtPackage> allPackages) {
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

    static Set<CtMethod> getAllProgramMethods(Set<CtClass> allClasses, Set<CtInterface> allInterfaces){
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

    static String getQualifiedMethodName (CtMethod theMethod){

        //CtClass theClass = (CtClass) theMethod.getParent();
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
}
