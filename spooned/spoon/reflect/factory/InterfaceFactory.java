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
 * The {@link CtInterface} sub-factory.
 */
public class InterfaceFactory extends spoon.reflect.factory.TypeFactory {
    /**
     * Creates a new interface sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public InterfaceFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates an interface.
     */
    public <T> spoon.reflect.declaration.CtInterface<T> create(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        spoon.reflect.declaration.CtInterface<T> i = factory.Core().createInterface();
        i.setSimpleName(simpleName);
        owner.addType(i);
        return i;
    }

    /**
     * Creates an inner interface
     */
    public <T> spoon.reflect.declaration.CtInterface<T> create(spoon.reflect.declaration.CtType<T> owner, java.lang.String simpleName) {
        spoon.reflect.declaration.CtInterface<T> ctInterface = factory.Core().createInterface();
        ctInterface.setSimpleName(simpleName);
        owner.addNestedType(ctInterface);
        return ctInterface;
    }

    /**
     * Creates an interface.
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtInterface<T> create(java.lang.String qualifiedName) {
        if ((hasInnerType(qualifiedName)) > 0) {
            return create(create(getDeclaringTypeName(qualifiedName)), getSimpleName(qualifiedName));
        }
        return create(factory.Package().getOrCreate(getPackageName(qualifiedName)), getSimpleName(qualifiedName));
    }

    /**
     * Gets a created interface
     *
     * @return the interface or null if does not exist
     */
    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtInterface<T> get(java.lang.String qualifiedName) {
        try {
            return ((spoon.reflect.declaration.CtInterface<T>) (super.get(qualifiedName)));
        } catch (java.lang.Exception e) {
            return null;
        }
    }

    /**
     * Gets a interface from its runtime Java class.
     *
     * @param <T>
     * 		type of created class
     * @param cl
     * 		the java class: note that this class should be Class&lt;T&gt; but
     * 		it then poses problem when T is a generic type itself
     */
    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtInterface<T> get(java.lang.Class<?> cl) {
        try {
            return ((spoon.reflect.declaration.CtInterface<T>) (super.get(cl)));
        } catch (java.lang.Exception e) {
            return null;
        }
    }
}

