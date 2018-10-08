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
 * The {@link CtEnum} sub-factory.
 */
public class EnumFactory extends spoon.reflect.factory.TypeFactory {
    /**
     * Creates a new enum sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public EnumFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a new enumeration type
     *
     * @param owner
    		package
     * 		
     * @param simpleName
     * 		the simple name
     */
    public spoon.reflect.declaration.CtEnum<?> create(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        spoon.reflect.declaration.CtEnum<?> e = factory.Core().createEnum();
        e.setSimpleName(simpleName);
        owner.addType(e);
        return e;
    }

    /**
     * Creates an enum from its qualified name.
     */
    public spoon.reflect.declaration.CtEnum<?> create(java.lang.String qualifiedName) {
        return create(factory.Package().getOrCreate(getPackageName(qualifiedName)), getSimpleName(qualifiedName));
    }

    /**
     * Gets an already created enumeration from its qualified name.
     *
     * @return the enumeration or null if does not exist
     */
    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public spoon.reflect.declaration.CtEnum<?> get(java.lang.String qualifiedName) {
        try {
            return ((spoon.reflect.declaration.CtEnum<?>) (super.get(qualifiedName)));
        } catch (java.lang.Exception e) {
            return null;
        }
    }

    /**
     * Gets a class from its runtime Java class.
     *
     * @param <T>
     * 		type of created class
     * @param cl
     * 		the java class: note that this class should be Class&lt;T&gt; but it
     * 		then poses problem when T is a generic type itself
     */
    public <T extends java.lang.Enum<?>> spoon.reflect.declaration.CtEnum<T> getEnum(java.lang.Class<T> cl) {
        try {
            spoon.reflect.declaration.CtType<T> t = super.get(cl);
            return ((spoon.reflect.declaration.CtEnum<T>) (t));
        } catch (java.lang.Exception e) {
            return null;
        }
    }
}

