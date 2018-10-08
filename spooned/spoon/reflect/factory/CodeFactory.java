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
 * This sub-factory contains utility methods to create code elements. To avoid
 * over-using reflection, consider using {@link spoon.template.Template}.
 */
public class CodeFactory extends spoon.reflect.factory.SubFactory {
    /**
     * Creates a {@link spoon.reflect.code.CtCodeElement} sub-factory.
     */
    public CodeFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a binary operator.
     *
     * @param <T>
     * 		the type of the expression
     * @param left
     * 		the left operand
     * @param right
     * 		the right operand
     * @param kind
     * 		the operator kind
     * @return a binary operator expression
     */
    public <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator(spoon.reflect.code.CtExpression<?> left, spoon.reflect.code.CtExpression<?> right, spoon.reflect.code.BinaryOperatorKind kind) {
        return factory.Core().<T>createBinaryOperator().setLeftHandOperand(left).setKind(kind).setRightHandOperand(right);
    }

    /**
     * Creates a accessed type.
     *
     * <p>This method sets a <i>clone</i> of the given {@code accessedType} object to the
     * {@linkplain CtTypeAccess#getAccessedType() accessedType} field of the returned {@link CtTypeAccess}. If the
     * given {@code accessedType} is unique and cloning is not needed, use
     * {@link #createTypeAccessWithoutCloningReference(CtTypeReference)} instead of this method.</p>
     *
     * @param accessedType
     * 		a type reference to the accessed type.
     * @param <T>
     * 		the type of the expression.
     * @return a accessed type expression.
     */
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType) {
        if (accessedType == null) {
            return factory.Core().createTypeAccess();
        }
        spoon.reflect.reference.CtTypeReference<T> access = accessedType.clone();
        // a type access doesn't contain actual type parameters
        access.setActualTypeArguments(null);
        return createTypeAccessWithoutCloningReference(access);
    }

    /**
     * Creates a accessed type.
     *
     * <p>This method sets a <i>clone</i> of the given {@code accessedType} object to the
     * {@linkplain CtTypeAccess#getAccessedType() accessedType} field of the returned {@link CtTypeAccess}. If the
     * given {@code accessedType} is unique and cloning is not needed, use
     * {@link #createTypeAccessWithoutCloningReference(CtTypeReference)} instead of this method.</p>
     *
     * @param accessedType
     * 		a type reference to the accessed type.
     * @param isImplicit
     * 		type of the type access is implicit or not.
     * @param <T>
     * 		the type of the expression.
     * @return a accessed type expression.
     */
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType, boolean isImplicit) {
        return createTypeAccess(accessedType).setImplicit(isImplicit);
    }

    /**
     * Creates a accessed type, see {@link #createTypeAccess(CtTypeReference)} for details.
     *
     * @param accessedType
     * 		a type reference to the accessed type.
     * @param <T>
     * 		the type of the expression.
     * @return a accessed type expression.
     */
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccessWithoutCloningReference(spoon.reflect.reference.CtTypeReference<T> accessedType) {
        final spoon.reflect.code.CtTypeAccess<T> typeAccess = factory.Core().createTypeAccess();
        typeAccess.setAccessedType(accessedType);
        return typeAccess;
    }

    /**
     * Creates a class access expression of the form <code>C.class</code>.
     *
     * @param <T>
     * 		the actual type of the accessed class if available
     * @param type
     * 		a type reference to the accessed class
     * @return the class access expression.
     */
    public <T> spoon.reflect.code.CtFieldAccess<java.lang.Class<T>> createClassAccess(spoon.reflect.reference.CtTypeReference<T> type) {
        @java.lang.SuppressWarnings({ "rawtypes", "unchecked" })
        spoon.reflect.reference.CtTypeReference<java.lang.Class<T>> classType = ((spoon.reflect.reference.CtTypeReference) (factory.Type().createReference(java.lang.Class.class)));
        spoon.reflect.code.CtTypeAccess<T> typeAccess = factory.Code().createTypeAccess(type);
        spoon.reflect.reference.CtFieldReference<java.lang.Class<T>> fieldReference = factory.Core().createFieldReference();
        fieldReference.setSimpleName("class");
        fieldReference.setType(classType);
        fieldReference.setDeclaringType(type);
        spoon.reflect.code.CtFieldRead<java.lang.Class<T>> fieldRead = factory.Core().createFieldRead();
        fieldRead.setType(classType.clone());
        fieldRead.setVariable(fieldReference);
        fieldRead.setTarget(typeAccess);
        return fieldRead;
    }

    /**
     * Creates a constructor call. The correct constructor is inferred based on parameters
     *
     * @param type
     * 		the decelerating type of the constructor
     * @param parameters
     * 		the arguments of the constructor call
     * @param <T>
     * 		the actual type of the decelerating type of the constructor if available
     * @return the constructor call
     */
    public <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.code.CtExpression<?>... parameters) {
        spoon.reflect.code.CtConstructorCall<T> constructorCall = factory.Core().createConstructorCall();
        spoon.reflect.reference.CtExecutableReference<T> executableReference = factory.Core().createExecutableReference();
        executableReference.setType(type);
        executableReference.setDeclaringType((type == null ? type : type.clone()));
        executableReference.setSimpleName(spoon.reflect.reference.CtExecutableReference.CONSTRUCTOR_NAME);
        java.util.List<spoon.reflect.reference.CtTypeReference<?>> typeReferences = new java.util.ArrayList<>();
        for (spoon.reflect.code.CtExpression<?> parameter : parameters) {
            typeReferences.add(parameter.getType());
        }
        executableReference.setParameters(typeReferences);
        constructorCall.setArguments(java.util.Arrays.asList(parameters));
        constructorCall.setExecutable(executableReference);
        return constructorCall;
    }

    /**
     * Creates a new class with an anonymous class.
     *
     * @param type
     * 		the decelerating type of the constructor.
     * @param anonymousClass
     * 		Anonymous class in the new class.
     * @param parameters
     * 		the arguments of the constructor call.
     * @param <T>
     * 		the actual type of the decelerating type of the constructor if available/
     * @return the new class.
     */
    public <T> spoon.reflect.code.CtNewClass<T> createNewClass(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.declaration.CtClass<?> anonymousClass, spoon.reflect.code.CtExpression<?>... parameters) {
        spoon.reflect.code.CtNewClass<T> ctNewClass = factory.Core().createNewClass();
        spoon.reflect.reference.CtExecutableReference<T> executableReference = factory.Constructor().createReference(type, parameters);
        ctNewClass.setArguments(java.util.Arrays.asList(parameters));
        ctNewClass.setExecutable(executableReference);
        ctNewClass.setAnonymousClass(anonymousClass);
        anonymousClass.setSimpleName("0");
        return ctNewClass;
    }

    /**
     * Creates an invocation (can be a statement or an expression).
     *
     * @param <T>
     * 		the return type of the invoked method
     * @param target
     * 		the target expression
     * @param executable
     * 		the invoked executable
     * @param arguments
     * 		the argument list
     * @return the new invocation
     */
    public <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, spoon.reflect.code.CtExpression<?>... arguments) {
        java.util.List<spoon.reflect.code.CtExpression<?>> ext = new java.util.ArrayList<>(arguments.length);
        java.util.Collections.addAll(ext, arguments);
        return createInvocation(target, executable, ext);
    }

    /**
     * Creates an invocation (can be a statement or an expression).
     *
     * @param <T>
     * 		the return type of the invoked method
     * @param target
     * 		the target expression (may be null for static methods)
     * @param executable
     * 		the invoked executable
     * @param arguments
     * 		the argument list
     * @return the new invocation
     */
    public <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, java.util.List<spoon.reflect.code.CtExpression<?>> arguments) {
        return factory.Core().<T>createInvocation().<spoon.reflect.code.CtInvocation<T>>setTarget(target).<spoon.reflect.code.CtInvocation<T>>setExecutable(executable).setArguments(arguments);
    }

    /**
     * Creates a literal with a given value.
     *
     * @param <T>
     * 		the type of the literal
     * @param value
     * 		the value of the literal
     * @return a new literal
     */
    public <T> spoon.reflect.code.CtLiteral<T> createLiteral(T value) {
        spoon.reflect.code.CtLiteral<T> literal = factory.Core().<T>createLiteral();
        literal.setValue(value);
        if (value != null) {
            literal.setType(((spoon.reflect.reference.CtTypeReference<T>) (factory.Type().<T>createReference(((java.lang.Class<T>) (value.getClass()))).unbox())));
        }else {
            literal.setType(((spoon.reflect.reference.CtTypeReference<T>) (factory.Type().nullType())));
        }
        return literal;
    }

    /**
     * Creates a one-dimension array that must only contain literals.
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.code.CtNewArray<T[]> createLiteralArray(T[] value) {
        if (!(value.getClass().isArray())) {
            throw new java.lang.RuntimeException("value is not an array");
        }
        if (value.getClass().getComponentType().isArray()) {
            throw new java.lang.RuntimeException("can only create one-dimension arrays");
        }
        final spoon.reflect.reference.CtTypeReference<T> componentTypeRef = factory.Type().createReference(((java.lang.Class<T>) (value.getClass().getComponentType())));
        final spoon.reflect.reference.CtArrayTypeReference<T[]> arrayReference = factory.Type().createArrayReference(componentTypeRef);
        spoon.reflect.code.CtNewArray<T[]> array = factory.Core().<T[]>createNewArray().setType(arrayReference);
        for (T e : value) {
            spoon.reflect.code.CtLiteral<T> l = factory.Core().createLiteral();
            l.setValue(e);
            array.addElement(l);
        }
        return array;
    }

    /**
     * Creates a local variable declaration.
     *
     * @param <T>
     * 		the local variable type
     * @param type
     * 		the reference to the type
     * @param name
     * 		the name of the variable
     * @param defaultExpression
     * 		the assigned default expression
     * @return a new local variable declaration
     */
    public <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.code.CtExpression<T> defaultExpression) {
        return factory.Core().<T>createLocalVariable().<spoon.reflect.code.CtLocalVariable<T>>setSimpleName(name).<spoon.reflect.code.CtLocalVariable<T>>setType(type).setDefaultExpression(defaultExpression);
    }

    /**
     * Creates a local variable reference that points to an existing local
     * variable (strong referencing).
     */
    public <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.code.CtLocalVariable<T> localVariable) {
        spoon.reflect.reference.CtLocalVariableReference<T> ref = factory.Core().createLocalVariableReference();
        ref.setType(((localVariable.getType()) == null ? null : localVariable.getType().clone()));
        ref.setSimpleName(localVariable.getSimpleName());
        ref.setParent(localVariable);
        return ref;
    }

    /**
     * Creates a local variable reference with its name an type (weak
     * referencing).
     */
    public <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name) {
        return factory.Core().<T>createLocalVariableReference().setType(type).setSimpleName(name);
    }

    /**
     * Creates a catch variable declaration.
     *
     * @param <T>
     * 		the catch variable type
     * @param type
     * 		the reference to the type
     * @param name
     * 		the name of the variable
     * @param modifierKinds
     * 		Modifiers of the catch variable
     * @return a new catch variable declaration
     */
    public <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.declaration.ModifierKind... modifierKinds) {
        return factory.Core().<T>createCatchVariable().<spoon.reflect.code.CtCatchVariable<T>>setSimpleName(name).<spoon.reflect.code.CtCatchVariable<T>>setType(type).setModifiers(new java.util.HashSet<>(java.util.Arrays.asList(modifierKinds)));
    }

    /**
     * Creates a catch variable reference that points to an existing catch
     * variable (strong referencing).
     */
    public <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference(spoon.reflect.code.CtCatchVariable<T> catchVariable) {
        return factory.Core().<T>createCatchVariableReference().setType(catchVariable.getType()).<spoon.reflect.reference.CtCatchVariableReference<T>>setSimpleName(catchVariable.getSimpleName());
    }

    /**
     * Creates a new statement list from an existing block.
     */
    public <R> spoon.reflect.code.CtStatementList createStatementList(spoon.reflect.code.CtBlock<R> block) {
        spoon.reflect.code.CtStatementList l = factory.Core().createStatementList();
        for (spoon.reflect.code.CtStatement s : block.getStatements()) {
            l.addStatement(s.clone());
        }
        return l;
    }

    /**
     * Creates an explicit access to a <code>this</code> variable (of the form
     * <code>type.this</code>).
     *
     * @param <T>
     * 		the actual type of <code>this</code>
     * @param type
     * 		the reference to the type that holds the <code>this</code>
     * 		variable
     * @return a <code>type.this</code> expression
     */
    public <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type) {
        return createThisAccess(type, false);
    }

    /**
     * Creates an access to a <code>this</code> variable (of the form
     * <code>type.this</code>).
     *
     * @param <T>
     * 		the actual type of <code>this</code>
     * @param type
     * 		the reference to the type that holds the <code>this</code>
     * 		variable
     * @param isImplicit
     * 		type of the this access is implicit or not.
     * @return a <code>type.this</code> expression
     */
    public <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type, boolean isImplicit) {
        spoon.reflect.code.CtThisAccess<T> thisAccess = factory.Core().<T>createThisAccess();
        thisAccess.setImplicit(isImplicit);
        thisAccess.setType(type);
        spoon.reflect.code.CtTypeAccess<T> typeAccess = factory.Code().createTypeAccess(type);
        thisAccess.setTarget(typeAccess);
        return thisAccess;
    }

    /**
     * Creates a variable access.
     */
    public <T> spoon.reflect.code.CtVariableAccess<T> createVariableRead(spoon.reflect.reference.CtVariableReference<T> variable, boolean isStatic) {
        spoon.reflect.code.CtVariableAccess<T> va;
        if (variable instanceof spoon.reflect.reference.CtFieldReference) {
            va = factory.Core().createFieldRead();
            // creates a this target for non-static fields to avoid name conflicts...
            if (!isStatic) {
                ((spoon.reflect.code.CtFieldAccess<T>) (va)).setTarget(createThisAccess(((spoon.reflect.reference.CtFieldReference<T>) (variable)).getDeclaringType()));
            }
        }else {
            va = factory.Core().createVariableRead();
        }
        return va.setVariable(variable);
    }

    /**
     * Creates a list of variable accesses.
     *
     * @param variables
     * 		the variables to be accessed
     */
    public java.util.List<spoon.reflect.code.CtExpression<?>> createVariableReads(java.util.List<? extends spoon.reflect.declaration.CtVariable<?>> variables) {
        java.util.List<spoon.reflect.code.CtExpression<?>> result = new java.util.ArrayList<>(variables.size());
        for (spoon.reflect.declaration.CtVariable<?> v : variables) {
            result.add(createVariableRead(v.getReference(), v.getModifiers().contains(spoon.reflect.declaration.ModifierKind.STATIC)));
        }
        return result;
    }

    /**
     * Creates a variable assignment (can be an expression or a statement).
     *
     * @param <T>
     * 		the type of the assigned variable
     * @param variable
     * 		a reference to the assigned variable
     * @param isStatic
     * 		tells if the assigned variable is static or not
     * @param expression
     * 		the assigned expression
     * @return a variable assignment
     */
    public <A, T extends A> spoon.reflect.code.CtAssignment<A, T> createVariableAssignment(spoon.reflect.reference.CtVariableReference<A> variable, boolean isStatic, spoon.reflect.code.CtExpression<T> expression) {
        spoon.reflect.code.CtVariableAccess<A> vaccess = createVariableRead(variable, isStatic);
        return factory.Core().<A, T>createAssignment().<spoon.reflect.code.CtAssignment<A, T>>setAssignment(expression).setAssigned(vaccess);
    }

    /**
     * Creates a list of statements that contains the assignments of a set of
     * variables.
     *
     * @param variables
     * 		the variables to be assigned
     * @param expressions
     * 		the assigned expressions
     * @return a list of variable assignments
     */
    public <T> spoon.reflect.code.CtStatementList createVariableAssignments(java.util.List<? extends spoon.reflect.declaration.CtVariable<T>> variables, java.util.List<? extends spoon.reflect.code.CtExpression<T>> expressions) {
        spoon.reflect.code.CtStatementList result = factory.Core().createStatementList();
        for (int i = 0; i < (variables.size()); i++) {
            result.addStatement(createVariableAssignment(variables.get(i).getReference(), variables.get(i).getModifiers().contains(spoon.reflect.declaration.ModifierKind.STATIC), expressions.get(i)));
        }
        return result;
    }

    /**
     * Creates a field.
     *
     * @param name
     * 		Name of the field.
     * @param type
     * 		Type of the field.
     * @param exp
     * 		Default expression of the field.
     * @param visibilities
     * 		All visibilities of the field.
     * @param <T>
     * 		Generic type for the type of the field.
     * @return a field
     */
    public <T> spoon.reflect.declaration.CtField<T> createCtField(java.lang.String name, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String exp, spoon.reflect.declaration.ModifierKind... visibilities) {
        return factory.Core().createField().<spoon.reflect.declaration.CtField<T>>setModifiers(modifiers(visibilities)).<spoon.reflect.declaration.CtField<T>>setSimpleName(name).<spoon.reflect.declaration.CtField<T>>setType(type).setDefaultExpression(this.<T>createCodeSnippetExpression(exp));
    }

    /**
     * Creates a block.
     *
     * @param element
     * 		Statement of the block.
     * @param <T>
     * 		Subclasses of CtStatement.
     * @return a block.
     */
    public <T extends spoon.reflect.code.CtStatement> spoon.reflect.code.CtBlock<?> createCtBlock(T element) {
        return factory.Core().createBlock().addStatement(element);
    }

    /**
     * Accepts instance of CtStatement or CtBlock.
     * If element is CtStatement, then it creates wrapping CtBlock, which contains the element
     * If element is CtBlock, then it directly returns that element
     * If element is null, then it returns null.
     * note: It must not create empty CtBlock - as expected in CtCatch, CtExecutable, CtLoop and CtTry setBody implementations
     *
     * @param element
     * 		
     * @return CtBlock instance
     */
    public <T extends spoon.reflect.code.CtStatement> spoon.reflect.code.CtBlock<?> getOrCreateCtBlock(T element) {
        if (element == null) {
            return null;
        }
        if (element instanceof spoon.reflect.code.CtBlock<?>) {
            return ((spoon.reflect.code.CtBlock<?>) (element));
        }
        return this.createCtBlock(element);
    }

    /**
     * Creates a throw.
     *
     * @param thrownExp
     * 		Expression of the throw.
     * @return a throw.
     */
    public spoon.reflect.code.CtThrow createCtThrow(java.lang.String thrownExp) {
        return factory.Core().createThrow().setThrownExpression(this.<java.lang.Throwable>createCodeSnippetExpression(thrownExp));
    }

    /**
     * Creates a catch element.
     *
     * @param nameCatch
     * 		Name of the variable in the catch.
     * @param exception
     * 		Type of the exception.
     * @param ctBlock
     * 		Content of the catch.
     * @return a catch.
     */
    public spoon.reflect.code.CtCatch createCtCatch(java.lang.String nameCatch, java.lang.Class<? extends java.lang.Throwable> exception, spoon.reflect.code.CtBlock<?> ctBlock) {
        final spoon.reflect.code.CtCatchVariable<java.lang.Throwable> catchVariable = factory.Core().<java.lang.Throwable>createCatchVariable().<spoon.reflect.code.CtCatchVariable<java.lang.Throwable>>setType(this.<java.lang.Throwable>createCtTypeReference(exception)).setSimpleName(nameCatch);
        return factory.Core().createCatch().setParameter(catchVariable).setBody(ctBlock);
    }

    /**
     * Creates a type reference.
     *
     * @param originalClass
     * 		Original class of the reference.
     * @param <T>
     * 		Type of the reference.
     * @return a type reference.
     */
    public <T> spoon.reflect.reference.CtTypeReference<T> createCtTypeReference(java.lang.Class<?> originalClass) {
        if (originalClass == null) {
            return null;
        }
        spoon.reflect.reference.CtTypeReference<T> typeReference = factory.Core().<T>createTypeReference();
        typeReference.setSimpleName(originalClass.getSimpleName());
        if (originalClass.isPrimitive()) {
            return typeReference;
        }
        if ((originalClass.getDeclaringClass()) != null) {
            // the inner class reference does not have package
            return typeReference.setDeclaringType(createCtTypeReference(originalClass.getDeclaringClass()));
        }
        return typeReference.setPackage(createCtPackageReference(originalClass.getPackage()));
    }

    /**
     * Creates a package reference.
     *
     * @param originalPackage
     * 		Original package of the reference.
     * @return a package reference.
     */
    public spoon.reflect.reference.CtPackageReference createCtPackageReference(java.lang.Package originalPackage) {
        return factory.Core().createPackageReference().setSimpleName(originalPackage.getName());
    }

    /**
     * Creates an annotation.
     *
     * @param annotationType
     * 		Type of the annotation.
     * @return an annotation.
     */
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation(spoon.reflect.reference.CtTypeReference<A> annotationType) {
        final spoon.reflect.declaration.CtAnnotation<A> a = factory.Core().createAnnotation();
        a.setAnnotationType(annotationType);
        return a;
    }

    /**
     * Gets a list of references from a list of elements.
     *
     * @param <R>
     * 		the expected reference type
     * @param <E>
     * 		the element type
     * @param elements
     * 		the element list
     * @return the corresponding list of references
     */
    @java.lang.SuppressWarnings("unchecked")
    public <R extends spoon.reflect.reference.CtReference, E extends spoon.reflect.declaration.CtNamedElement> java.util.List<R> getReferences(java.util.List<E> elements) {
        java.util.List<R> refs = new java.util.ArrayList<>(elements.size());
        for (E e : elements) {
            refs.add(((R) (e.getReference())));
        }
        return refs;
    }

    /**
     * Creates a modifier set.
     *
     * @param modifiers
     * 		to put in set
     * @return Set of given modifiers
     */
    public java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers(spoon.reflect.declaration.ModifierKind... modifiers) {
        java.util.Set<spoon.reflect.declaration.ModifierKind> ret = java.util.EnumSet.noneOf(spoon.reflect.declaration.ModifierKind.class);
        java.util.Collections.addAll(ret, modifiers);
        return ret;
    }

    /**
     * Creates a Code Snippet expression.
     *
     * @param <T>
     * 		The type of the expression represented by the CodeSnippet
     * @param expression
     * 		The string that contains the expression.
     * @return a new CtCodeSnippetExpression.
     */
    public <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression(java.lang.String expression) {
        spoon.reflect.code.CtCodeSnippetExpression<T> e = factory.Core().createCodeSnippetExpression();
        e.setValue(expression);
        return e;
    }

    /**
     * Creates a Code Snippet statement.
     *
     * @param statement
     * 		The String containing the statement.
     * @return a new CtCodeSnippetStatement
     */
    public spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement(java.lang.String statement) {
        spoon.reflect.code.CtCodeSnippetStatement e = factory.Core().createCodeSnippetStatement();
        e.setValue(statement);
        return e;
    }

    /**
     * Creates a comment
     *
     * @param content
     * 		The content of the comment
     * @param type
     * 		The comment type
     * @return a new CtComment
     */
    public spoon.reflect.code.CtComment createComment(java.lang.String content, spoon.reflect.code.CtComment.CommentType type) {
        if (type == (spoon.reflect.code.CtComment.CommentType.JAVADOC)) {
            return factory.Core().createJavaDoc().setContent(content);
        }
        return factory.Core().createComment().setContent(content).setCommentType(type);
    }

    /**
     * Creates an inline comment
     *
     * @param content
     * 		The content of the comment
     * @return a new CtComment
     */
    public spoon.reflect.code.CtComment createInlineComment(java.lang.String content) {
        if (content.contains(spoon.reflect.code.CtComment.LINE_SEPARATOR)) {
            throw new spoon.SpoonException(("The content of your comment contain at least one line separator. " + "Please consider using a block comment by calling createComment(\"your content\", CtComment.CommentType.BLOCK)."));
        }
        return createComment(content, spoon.reflect.code.CtComment.CommentType.INLINE);
    }

    /**
     * Creates a javadoc tag
     *
     * @param content
     * 		The content of the javadoc tag with a possible paramater
     * @param type
     * 		The tag type
     * @return a new CtJavaDocTag
     */
    public spoon.reflect.code.CtJavaDocTag createJavaDocTag(java.lang.String content, spoon.reflect.code.CtJavaDocTag.TagType type) {
        if (content == null) {
            content = "";
        }
        spoon.reflect.code.CtJavaDocTag docTag = factory.Core().createJavaDocTag();
        if ((type != null) && (type.hasParam())) {
            int firstWord = content.indexOf(' ');
            int firstLine = content.indexOf('\n');
            if ((firstLine < firstWord) && (firstLine >= 0)) {
                firstWord = firstLine;
            }
            if (firstWord == (-1)) {
                firstWord = content.length();
            }
            java.lang.String param = content.substring(0, firstWord);
            content = content.substring(firstWord);
            docTag.setParam(param);
        }
        return docTag.setContent(content.trim()).setType(type);
    }
}

