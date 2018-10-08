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
 * The {@link CtExecutable} sub-factory.
 */
public class ExecutableFactory extends spoon.reflect.factory.SubFactory {
    /**
     * Creates a new executable sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public ExecutableFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates an anonymous executable (initializer block) in a target class).
     */
    public spoon.reflect.declaration.CtAnonymousExecutable createAnonymous(spoon.reflect.declaration.CtClass<?> target, spoon.reflect.code.CtBlock<java.lang.Void> body) {
        spoon.reflect.declaration.CtAnonymousExecutable a = factory.Core().createAnonymousExecutable();
        target.addAnonymousExecutable(a);
        a.setBody(body);
        return a;
    }

    /**
     * Creates a new parameter.
     */
    public <T> spoon.reflect.declaration.CtParameter<T> createParameter(spoon.reflect.declaration.CtExecutable<?> parent, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name) {
        spoon.reflect.declaration.CtParameter<T> parameter = factory.Core().createParameter();
        parameter.setType(type);
        parameter.setSimpleName(name);
        if (parent != null) {
            parent.addParameter(parameter);
        }
        return parameter;
    }

    /**
     * Creates a parameter reference from an existing parameter.
     *
     * @param <T>
     * 		the parameter's type
     * @param parameter
     * 		the parameter
     */
    public <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference(spoon.reflect.declaration.CtParameter<T> parameter) {
        spoon.reflect.reference.CtParameterReference<T> ref = factory.Core().createParameterReference();
        ref.setSimpleName(parameter.getSimpleName());
        ref.setType(parameter.getType());
        return ref;
    }

    /**
     * Creates an executable reference from an existing executable.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.declaration.CtExecutable<T> e) {
        spoon.reflect.reference.CtExecutableReference<T> er = createReferenceInternal(e);
        er.setParent(e);
        return er;
    }

    private <T> spoon.reflect.reference.CtExecutableReference<T> createReferenceInternal(spoon.reflect.declaration.CtExecutable<T> e) {
        spoon.reflect.reference.CtTypeReference<?>[] refs = new spoon.reflect.reference.CtTypeReference[e.getParameters().size()];
        int i = 0;
        for (spoon.reflect.declaration.CtParameter<?> param : e.getParameters()) {
            refs[(i++)] = getMethodParameterType(param.getType());
        }
        java.lang.String executableName = e.getSimpleName();
        if (e instanceof spoon.reflect.declaration.CtMethod) {
            boolean isStatic = ((spoon.reflect.declaration.CtMethod) (e)).hasModifier(spoon.reflect.declaration.ModifierKind.STATIC);
            return createReference(((spoon.reflect.declaration.CtMethod<T>) (e)).getDeclaringType().getReference(), isStatic, ((spoon.reflect.declaration.CtMethod<T>) (e)).getType().clone(), executableName, refs);
        }else
            if (e instanceof spoon.reflect.code.CtLambda) {
                spoon.reflect.declaration.CtMethod<T> lambdaMethod = ((spoon.reflect.code.CtLambda) (e)).getOverriddenMethod();
                return createReference(e.getParent(spoon.reflect.declaration.CtType.class).getReference(), (lambdaMethod == null ? null : lambdaMethod.getType().clone()), executableName, refs);
            }else
                if (e instanceof spoon.reflect.declaration.CtAnonymousExecutable) {
                    return createReference(((spoon.reflect.declaration.CtAnonymousExecutable) (e)).getDeclaringType().getReference(), e.getType().clone(), executableName);
                }


        // constructor
        return createReference(((spoon.reflect.declaration.CtConstructor<T>) (e)).getDeclaringType().getReference(), ((spoon.reflect.declaration.CtConstructor<T>) (e)).getType().clone(), spoon.reflect.reference.CtExecutableReference.CONSTRUCTOR_NAME, refs);
    }

    private spoon.reflect.reference.CtTypeReference<?> getMethodParameterType(spoon.reflect.reference.CtTypeReference<?> paramType) {
        if (paramType instanceof spoon.reflect.reference.CtTypeParameterReference) {
            paramType = ((spoon.reflect.reference.CtTypeParameterReference) (paramType)).getBoundingType();
        }
        if (paramType instanceof spoon.reflect.reference.CtArrayTypeReference) {
            spoon.reflect.reference.CtArrayTypeReference atr = ((spoon.reflect.reference.CtArrayTypeReference) (paramType));
            spoon.reflect.reference.CtTypeReference<?> originCT = atr.getComponentType();
            spoon.reflect.reference.CtTypeReference<?> erasedCT = getMethodParameterType(originCT);
            if (originCT != erasedCT) {
                spoon.reflect.reference.CtArrayTypeReference<?> erased = atr;
                erased.setComponentType(erasedCT);
                paramType = erased;
            }
        }
        if (paramType == null) {
            paramType = factory.Type().OBJECT;
        }
        return paramType.clone();
    }

    /**
     * Creates an executable reference.
     *
     * @param declaringType
     * 		reference to the declaring type
     * @param type
     * 		the executable's type
     * @param methodName
     * 		simple name
     * @param parameterTypes
     * 		list of parameter's types
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.reference.CtTypeReference<?> declaringType, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String methodName, spoon.reflect.reference.CtTypeReference<?>... parameterTypes) {
        return createReference(declaringType, false, type, methodName, parameterTypes);
    }

    /**
     * Creates an executable reference.
     *
     * @param declaringType
     * 		reference to the declaring type
     * @param isStatic
     * 		if this reference references a static executable
     * @param type
     * 		the return type of the executable
     * @param methodName
     * 		simple name
     * @param parameterTypes
     * 		list of parameter's types
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.reference.CtTypeReference<?> declaringType, boolean isStatic, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String methodName, spoon.reflect.reference.CtTypeReference<?>... parameterTypes) {
        return createReference(declaringType, isStatic, type, methodName, java.util.Arrays.asList(parameterTypes));
    }

    /**
     * Creates an executable reference.
     *
     * @param declaringType
     * 		reference to the declaring type
     * @param isStatic
     * 		if this reference references a static executable
     * @param type
     * 		the return type of the executable
     * @param methodName
     * 		simple name
     * @param parameterTypes
     * 		list of parameter's types
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.reference.CtTypeReference<?> declaringType, boolean isStatic, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String methodName, java.util.List<spoon.reflect.reference.CtTypeReference<?>> parameterTypes) {
        spoon.reflect.reference.CtExecutableReference<T> methodRef = factory.Core().createExecutableReference();
        methodRef.setStatic(isStatic);
        methodRef.setDeclaringType(declaringType);
        methodRef.setSimpleName(methodName);
        methodRef.setType(type);
        java.util.List<spoon.reflect.reference.CtTypeReference<?>> l = new java.util.ArrayList<>(parameterTypes);
        methodRef.setParameters(l);
        return methodRef;
    }

    /**
     * Creates an executable reference.
     *
     * @param declaringType
     * 		reference to the declaring type
     * @param type
     * 		the return type of the executable
     * @param methodName
     * 		simple name
     * @param parameterTypes
     * 		list of parameter's types
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(spoon.reflect.reference.CtTypeReference<?> declaringType, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String methodName, java.util.List<spoon.reflect.reference.CtTypeReference<?>> parameterTypes) {
        spoon.reflect.reference.CtExecutableReference<T> methodRef = factory.Core().createExecutableReference();
        methodRef.setDeclaringType(declaringType);
        methodRef.setSimpleName(methodName);
        methodRef.setType(type);
        java.util.List<spoon.reflect.reference.CtTypeReference<?>> l = new java.util.ArrayList<>(parameterTypes);
        methodRef.setParameters(l);
        return methodRef;
    }

    /**
     * Creates an executable reference from its signature, as defined by the
     * executable reference's toString.
     */
    public <T> spoon.reflect.reference.CtExecutableReference<T> createReference(java.lang.String signature) {
        spoon.reflect.reference.CtExecutableReference<T> executableRef = factory.Core().createExecutableReference();
        java.lang.String type = signature.substring(0, signature.indexOf(' '));
        java.lang.String declaringType = signature.substring(((signature.indexOf(' ')) + 1), signature.indexOf(spoon.reflect.declaration.CtExecutable.EXECUTABLE_SEPARATOR));
        java.lang.String executableName = signature.substring(((signature.indexOf(spoon.reflect.declaration.CtExecutable.EXECUTABLE_SEPARATOR)) + 1), signature.indexOf('('));
        executableRef.setSimpleName(executableName);
        executableRef.setDeclaringType(factory.Type().createReference(declaringType));
        spoon.reflect.reference.CtTypeReference<T> typeRef = factory.Type().createReference(type);
        executableRef.setType(typeRef);
        java.lang.String parameters = signature.substring(((signature.indexOf('(')) + 1), signature.indexOf(')'));
        java.util.List<spoon.reflect.reference.CtTypeReference<?>> params = new java.util.ArrayList<>(spoon.reflect.ModelElementContainerDefaultCapacities.PARAMETERS_CONTAINER_DEFAULT_CAPACITY);
        java.util.StringTokenizer t = new java.util.StringTokenizer(parameters, ",");
        while (t.hasMoreTokens()) {
            java.lang.String paramType = t.nextToken();
            params.add(factory.Type().createReference(paramType));
        } 
        executableRef.setParameters(params);
        return executableRef;
    }
}

