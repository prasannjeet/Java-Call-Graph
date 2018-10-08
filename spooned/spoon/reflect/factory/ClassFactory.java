/**
 * Copyright (C) 2006-2018 INRIA and contributors
 * Spoon - http://spoon.gforge.inria.fr/
 *
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify
 * and/or redistribute the software under the terms of the CeCILL-C license as
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */
package spoon.reflect.factory;


/**
 * The {@link CtClass} sub-factory.
 */
public class ClassFactory extends spoon.reflect.factory.TypeFactory {
    /**
     * Creates a class sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public ClassFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates an inner class.
     *
     * @param declaringClass
     * 		declaring class
     * @param simpleName
     * 		simple name of inner class (without . or $)
     */
    public <T> spoon.reflect.declaration.CtClass<T> create(spoon.reflect.declaration.CtClass<?> declaringClass, java.lang.String simpleName) {
        spoon.reflect.declaration.CtClass<T> c = factory.Core().createClass();
        c.setSimpleName(simpleName);
        declaringClass.addNestedType(c);
        return c;
    }

    /**
     * Creates a top-level class.
     *
     * @param owner
     * 		the declaring package
     * @param simpleName
     * 		the simple name
     */
    public <T> spoon.reflect.declaration.CtClass<T> create(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        spoon.reflect.declaration.CtClass<T> c = factory.Core().createClass();
        c.setSimpleName(simpleName);
        owner.addType(c);
        return c;
    }

    /**
     * Creates a class from its qualified name.
     *
     * @param <T>
     * 		type of created class
     * @param qualifiedName
     * 		full name of class to create. Name can contain . or $ for
     * 		inner types
     */
    public <T> spoon.reflect.declaration.CtClass<T> create(java.lang.String qualifiedName) {
        if ((hasInnerType(qualifiedName)) > 0) {
            spoon.reflect.declaration.CtClass<?> declaringClass = create(getDeclaringTypeName(qualifiedName));
            return create(declaringClass, getSimpleName(qualifiedName));
        }
        return create(factory.Package().getOrCreate(getPackageName(qualifiedName)), getSimpleName(qualifiedName));
    }

    /**
     * Gets a class from its runtime Java class.
     *
     * @param <T>
     * 		type of created class
     * @param cl
     * 		the java class: note that this class should be Class&lt;T&gt; but
     * 		it then poses problem when T is a generic type itself
     */
    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtClass<T> get(java.lang.Class<?> cl) {
        try {
            return ((spoon.reflect.declaration.CtClass<T>) (super.get(cl)));
        } catch (java.lang.Exception e) {
            return null;
        }
    }

    /**
     * Searches for a class from his qualified name.
     *
     * @param <T>
     * 		the type of the class
     * @param qualifiedName
     * 		to search
     * @return found class or null
     */
    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtClass<T> get(java.lang.String qualifiedName) {
        try {
            return ((spoon.reflect.declaration.CtClass<T>) (super.get(qualifiedName)));
        } catch (java.lang.Exception e) {
            return null;
        }
    }
}

