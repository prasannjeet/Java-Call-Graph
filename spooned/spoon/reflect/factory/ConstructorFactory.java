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
 * The {@link CtConstructor} sub-factory.
 */
public class ConstructorFactory extends spoon.reflect.factory.ExecutableFactory {
    /**
     * Creates a new constructor sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public ConstructorFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Copies a constructor into a target class.
     *
     * @param target
     * 		the target class
     * @param source
     * 		the constructor to be copied
     * @return the new constructor
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtConstructor<T> create(spoon.reflect.declaration.CtClass<T> target, spoon.reflect.declaration.CtConstructor<?> source) {
        spoon.reflect.declaration.CtConstructor<T> newConstructor = ((spoon.reflect.declaration.CtConstructor<T>) (source.clone()));
        target.addConstructor(newConstructor);
        return newConstructor;
    }

    /**
     * Creates a constructor into a target class by copying it from a source
     * method.
     *
     * @param target
     * 		the target class
     * @param source
     * 		the method to be copied
     * @return the new constructor
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.declaration.CtConstructor<T> create(spoon.reflect.declaration.CtClass<T> target, spoon.reflect.declaration.CtMethod<?> source) {
        spoon.reflect.declaration.CtMethod<T> method = ((spoon.reflect.declaration.CtMethod<T>) (source.clone()));
        spoon.reflect.declaration.CtConstructor<T> newConstructor = factory.Core().createConstructor();
        newConstructor.setAnnotations(method.getAnnotations());
        newConstructor.setBody(method.getBody());
        newConstructor.setDocComment(method.getDocComment());
        newConstructor.setFormalCtTypeParameters(method.getFormalCtTypeParameters());
        newConstructor.setModifiers(method.getModifiers());
        newConstructor.setParameters(method.getParameters());
        target.addConstructor(newConstructor);
        return newConstructor;
    }

    /**
     * Creates an empty constructor.
     *
     * @param modifiers
     * 		the modifiers
     * @param parameters
     * 		the parameters
     * @param thrownTypes
     * 		the thrown types
     */
    public <T> spoon.reflect.declaration.CtConstructor<T> create(spoon.reflect.declaration.CtClass<T> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes) {
        spoon.reflect.declaration.CtConstructor<T> constructor = factory.Core().createConstructor();
        constructor.setModifiers(modifiers);
        constructor.setParameters(parameters);
        constructor.setThrownTypes(thrownTypes);
        target.addConstructor(constructor);
        return constructor;
    }

    /**
     * Create the default empty constructor.
     *
     * @param target
     * 		the class to insert the constructor into
     * @return the created constructor
     */
    public <T> spoon.reflect.declaration.CtConstructor<T> createDefault(spoon.reflect.declaration.CtClass<T> target) {
        spoon.reflect.declaration.CtConstructor<T> constructor = factory.Core().createConstructor();
        constructor.addModifier(spoon.reflect.declaration.ModifierKind.PUBLIC);
        target.addConstructor(constructor);
        return constructor;
    }

    /**
     * Creates a constructor.
     *
     * @param modifiers
     * 		the modifiers
     * @param parameters
     * 		the parameters
     * @param thrownTypes
     * 		the thrown types
     * @param body
     * 		the body
     */
    public <T> spoon.reflect.declaration.CtConstructor<T> create(spoon.reflect.declaration.CtClass<T> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock<T> body) {
        spoon.reflect.declaration.CtConstructor<T> constructor = create(target, modifiers, parameters, thrownTypes);
        constructor.setBody(body);
        return constructor;
    }

    /**
     * Creates a constructor reference from an existing constructor.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.declaration.CtConstructor<T> c) {
        return factory.Executable().createReference(c);
    }

    /**
     * Creates a constructor reference from an actual constructor.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(java.lang.reflect.Constructor<T> constructor) {
        spoon.reflect.reference.CtTypeReference<T> type = factory.Type().createReference(constructor.getDeclaringClass());
        return createReference(type, type.clone(), spoon.reflect.reference.CtExecutableReference.CONSTRUCTOR_NAME, factory.Type().createReferences(java.util.Arrays.asList(constructor.getParameterTypes())));
    }

    /**
     * Creates a constructor reference.
     *
     * @param type
     * 		Declaring type of the constructor.
     * @param parameters
     * 		Constructor parameters.
     * @param <T>
     * 		Infered type of the constructor.
     * @return CtExecutablereference if a constructor.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.code.CtExpression<?>... parameters) {
        final spoon.reflect.reference.CtExecutableReference<T> executableReference = factory.Core().createExecutableReference();
        executableReference.setType(type);
        executableReference.setDeclaringType((type == null ? null : type.clone()));
        executableReference.setSimpleName(spoon.reflect.reference.CtExecutableReference.CONSTRUCTOR_NAME);
        java.util.List<spoon.reflect.reference.CtTypeReference<?>> typeReferences = new java.util.ArrayList<>();
        for (spoon.reflect.code.CtExpression<?> parameter : parameters) {
            typeReferences.add(((parameter.getType()) == null ? null : parameter.getType().clone()));
        }
        executableReference.setParameters(typeReferences);
        return executableReference;
    }
}

