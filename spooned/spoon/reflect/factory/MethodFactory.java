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
 * The {@link CtMethod} sub-factory.
 */
public class MethodFactory extends spoon.reflect.factory.ExecutableFactory {
    public final java.util.Set<spoon.reflect.declaration.CtMethod<?>> OBJECT_METHODS = java.util.Collections.unmodifiableSet(factory.Class().get(java.lang.Object.class).getMethods());

    /**
     * Creates a new method sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public MethodFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a method.
     *
     * @param target
     * 		the class where the method is inserted
     * @param modifiers
     * 		the modifiers
     * @param returnType
     * 		the method's return type
     * @param name
     * 		the method's name
     * @param parameters
     * 		the parameters
     * @param thrownTypes
     * 		the thrown types
     * @param body
     * 		the method's body
     */
    public <R, B extends R> spoon.reflect.declaration.CtMethod<R> create(spoon.reflect.declaration.CtClass<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference<R> returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock<B> body) {
        spoon.reflect.declaration.CtMethod<R> method = create(target, modifiers, returnType, name, parameters, thrownTypes);
        method.setBody(body);
        return method;
    }

    /**
     * Creates a method by copying an existing method.
     *
     * @param <T>
     * 		the type of the method
     * @param target
     * 		the target type where the new method has to be inserted to
     * @param source
     * 		the source method to be copied
     * @param redirectReferences
     * 		tells if all the references to the owning type of the source
     * 		method should be redirected to the target type (true is
     * 		recommended for most uses)
     * @return the newly created method
     */
    public <T> spoon.reflect.declaration.CtMethod<T> create(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtMethod<T> source, boolean redirectReferences) {
        spoon.reflect.declaration.CtMethod<T> newMethod = source.clone();
        if (redirectReferences && ((source.getDeclaringType()) != null)) {
            spoon.template.Substitution.redirectTypeReferences(newMethod, source.getDeclaringType().getReference(), target.getReference());
        }
        target.addMethod(newMethod);
        return newMethod;
    }

    /**
     * Creates an empty method.
     *
     * @param target
     * 		the class where the method is inserted
     * @param modifiers
     * 		the modifiers
     * @param returnType
     * 		the method's return type
     * @param name
     * 		the method's name
     * @param parameters
     * 		the parameters
     * @param thrownTypes
     * 		the thrown types
     */
    public <T> spoon.reflect.declaration.CtMethod<T> create(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference<T> returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes) {
        spoon.reflect.declaration.CtMethod<T> method = factory.Core().createMethod();
        if (modifiers != null) {
            method.setModifiers(modifiers);
        }
        method.setType(returnType);
        method.setSimpleName(name);
        if (parameters != null) {
            method.setParameters(parameters);
        }
        if (thrownTypes != null) {
            method.setThrownTypes(thrownTypes);
        }
        target.addMethod(method);
        return method;
    }

    /**
     * Creates a method reference.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.declaration.CtMethod<T> m) {
        return factory.Executable().createReference(m);
    }

    /**
     * Creates a method reference from an actual method.
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(java.lang.reflect.Method method) {
        return createReference(factory.Type().createReference(method.getDeclaringClass()), ((spoon.reflect.reference.CtTypeReference<T>) (factory.Type().createReference(method.getReturnType()))), method.getName(), factory.Type().createReferences(java.util.Arrays.asList(method.getParameterTypes())).toArray(new spoon.reflect.reference.CtTypeReference<?>[0]));
    }

    /**
     * Gets all the main methods stored in this factory.
     */
    public java.util.Collection<spoon.reflect.declaration.CtMethod<java.lang.Void>> getMainMethods() {
        java.util.Collection<spoon.reflect.declaration.CtMethod<java.lang.Void>> methods = new java.util.ArrayList<>();
        for (spoon.reflect.declaration.CtType<?> t : factory.Type().getAll()) {
            if (t instanceof spoon.reflect.declaration.CtClass) {
                spoon.reflect.declaration.CtMethod<java.lang.Void> m = ((spoon.reflect.declaration.CtClass<?>) (t)).getMethod(factory.Type().createReference(void.class), "main", factory.Type().createArrayReference(factory.Type().createReference(java.lang.String.class)));
                if ((m != null) && (m.getModifiers().contains(spoon.reflect.declaration.ModifierKind.STATIC))) {
                    methods.add(m);
                }
            }
        }
        return methods;
    }
}

