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
 * This interface defines the core creation methods for the meta-model (to be
 * implemented so that Spoon can manipulate other meta-model implementations).
 * <p>
 * <b>Important</b>: a required post-condition for all the created elements is
 * that the factory (see {@link spoon.processing.FactoryAccessor#getFactory()})
 * is correctly initialized with the main factory returned by
 * {@link #getMainFactory()}, which cannot be null.
 */
public interface CoreFactory {
    /**
     * Recursively clones a given element of the metamodel and all its child
     * elements.
     *
     * @param <T>
     * 		the element's type
     * @param element
     * 		the element
     * @return a clone of <code>element</code>
     * @see spoon.reflect.declaration.CtElement#clone()
     */
    <T extends spoon.reflect.declaration.CtElement> T clone(T element);

    /**
     * Creates an annotation.
     */
    <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation();

    /**
     * Creates an annotation type.
     */
    <T extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotationType<T> createAnnotationType();

    /**
     * Creates an anonymous executable.
     */
    spoon.reflect.declaration.CtAnonymousExecutable createAnonymousExecutable();

    /**
     * Creates an array read access expression.
     */
    <T> spoon.reflect.code.CtArrayRead<T> createArrayRead();

    /**
     * Creates an array write access expression.
     */
    <T> spoon.reflect.code.CtArrayWrite<T> createArrayWrite();

    /**
     * Creates an array type reference.
     */
    <T> spoon.reflect.reference.CtArrayTypeReference<T> createArrayTypeReference();

    /**
     * Creates an <code>assert</code> statement.
     */
    <T> spoon.reflect.code.CtAssert<T> createAssert();

    /**
     * Creates an assignment expression.
     */
    <T, A extends T> spoon.reflect.code.CtAssignment<T, A> createAssignment();

    /**
     * Creates a binary operator.
     */
    <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator();

    /**
     * Creates a block.
     */
    <R> spoon.reflect.code.CtBlock<R> createBlock();

    /**
     * Creates a <code>break</code> statement.
     */
    spoon.reflect.code.CtBreak createBreak();

    /**
     * Creates a <code>case</code> clause.
     */
    <S> spoon.reflect.code.CtCase<S> createCase();

    /**
     * Creates a <code>catch</code> clause.
     */
    spoon.reflect.code.CtCatch createCatch();

    /**
     * Creates a class.
     */
    <T> spoon.reflect.declaration.CtClass<T> createClass();

    /**
     * Creates a type parameter declaration.
     */
    spoon.reflect.declaration.CtTypeParameter createTypeParameter();

    /**
     * Creates a conditional expression (<code>boolExpr?ifTrue:ifFalse</code>).
     */
    <T> spoon.reflect.code.CtConditional<T> createConditional();

    /**
     * Creates a constructor.
     */
    <T> spoon.reflect.declaration.CtConstructor<T> createConstructor();

    /**
     * Creates an invisible array constructor.
     */
    <T> spoon.reflect.declaration.CtConstructor<T> createInvisibleArrayConstructor();

    /**
     * Creates a <code>continue</code> statement.
     */
    spoon.reflect.code.CtContinue createContinue();

    /**
     * Creates a <code>do</code> loop.
     */
    spoon.reflect.code.CtDo createDo();

    /**
     * Creates an enum.
     */
    <T extends java.lang.Enum<?>> spoon.reflect.declaration.CtEnum<T> createEnum();

    /**
     * Creates an executable reference.
     */
    <T> spoon.reflect.reference.CtExecutableReference<T> createExecutableReference();

    /**
     * Creates a field.
     */
    <T> spoon.reflect.declaration.CtField<T> createField();

    /**
     * Creates an enum value.
     */
    <T> spoon.reflect.declaration.CtEnumValue<T> createEnumValue();

    /**
     * Creates a field read access.
     */
    <T> spoon.reflect.code.CtFieldRead<T> createFieldRead();

    /**
     * Creates a field write access.
     */
    <T> spoon.reflect.code.CtFieldWrite<T> createFieldWrite();

    /**
     * Creates an access expression to this.
     */
    <T> spoon.reflect.code.CtThisAccess<T> createThisAccess();

    /**
     * Creates an access expression to super.
     */
    <T> spoon.reflect.code.CtSuperAccess<T> createSuperAccess();

    /**
     * Creates a field reference.
     */
    <T> spoon.reflect.reference.CtFieldReference<T> createFieldReference();

    /**
     * Creates a <code>for</code> loop.
     */
    spoon.reflect.code.CtFor createFor();

    /**
     * Creates a <code>foreach</code> loop.
     */
    spoon.reflect.code.CtForEach createForEach();

    /**
     * Creates an <code>if</code> statement.
     */
    spoon.reflect.code.CtIf createIf();

    /**
     * Creates an interface.
     */
    <T> spoon.reflect.declaration.CtInterface<T> createInterface();

    /**
     * Creates an invocation expression.
     */
    <T> spoon.reflect.code.CtInvocation<T> createInvocation();

    /**
     * Creates a literal expression.
     */
    <T> spoon.reflect.code.CtLiteral<T> createLiteral();

    /**
     * Creates a local variable declaration statement.
     */
    <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable();

    /**
     * Creates a local variable reference.
     */
    <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference();

    /**
     * Creates a catch variable declaration statement.
     */
    <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable();

    /**
     * Creates a catch variable reference.
     */
    <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference();

    /**
     * Creates a method.
     */
    <T> spoon.reflect.declaration.CtMethod<T> createMethod();

    /**
     * Creates an annotation method.
     */
    <T> spoon.reflect.declaration.CtAnnotationMethod<T> createAnnotationMethod();

    /**
     * Creates a new array expression.
     */
    <T> spoon.reflect.code.CtNewArray<T> createNewArray();

    /**
     * Creates a constructor call expression.
     *
     * Example to build "new Foo()":
     * <pre>
     *     CtConstructorCall call = spoon.getFactory().Core().createConstructorCall();
     *     call.setType(spoon.getFactory().Core().createTypeReference().setSimpleName("Foo"));
     * </pre>
     */
    <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall();

    /**
     * Creates a new anonymous class expression.
     */
    <T> spoon.reflect.code.CtNewClass<T> createNewClass();

    /**
     * Creates a new anonymous method expression.
     */
    <T> spoon.reflect.code.CtLambda<T> createLambda();

    /**
     * Creates a new executable reference expression.
     */
    <T, E extends spoon.reflect.code.CtExpression<?>> spoon.reflect.code.CtExecutableReferenceExpression<T, E> createExecutableReferenceExpression();

    /**
     * Creates a new operator assignment (like +=).
     */
    <T, A extends T> spoon.reflect.code.CtOperatorAssignment<T, A> createOperatorAssignment();

    /**
     * Creates a package.
     */
    spoon.reflect.declaration.CtPackage createPackage();

    /**
     * Creates a package reference.
     */
    spoon.reflect.reference.CtPackageReference createPackageReference();

    /**
     * Creates a parameter.
     */
    <T> spoon.reflect.declaration.CtParameter<T> createParameter();

    /**
     * Creates a parameter reference.
     */
    <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference();

    /**
     * Creates a <code>return</code> statement.
     */
    <R> spoon.reflect.code.CtReturn<R> createReturn();

    /**
     * Creates a source position.
     */
    spoon.reflect.cu.SourcePosition createSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int[] lineSeparatorPositions);

    /**
     * Creates a source position that points to the given compilation unit
     */
    spoon.reflect.cu.SourcePosition createPartialSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit);

    /**
     * Creates a compound source position.
     */
    spoon.reflect.cu.position.CompoundSourcePosition createCompoundSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int declarationStart, int declarationEnd, int[] lineSeparatorPositions);

    /**
     * Creates a declaration source position.
     */
    spoon.reflect.cu.position.DeclarationSourcePosition createDeclarationSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int[] lineSeparatorPositions);

    /**
     * Creates a body holder source position.
     */
    spoon.reflect.cu.position.BodyHolderSourcePosition createBodyHolderSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int bodyStart, int bodyEnd, int[] lineSeparatorPositions);

    /**
     * Creates a statement list.
     */
    <R> spoon.reflect.code.CtStatementList createStatementList();

    /**
     * Creates a <code>switch</code> statement.
     */
    <S> spoon.reflect.code.CtSwitch<S> createSwitch();

    /**
     * Creates a <code>synchronized</code> statement.
     */
    spoon.reflect.code.CtSynchronized createSynchronized();

    /**
     * Creates a <code>throw</code> statement.
     */
    spoon.reflect.code.CtThrow createThrow();

    /**
     * Creates a <code>try</code> block.
     */
    spoon.reflect.code.CtTry createTry();

    /**
     * Creates a <code>try</code> with resource block.
     */
    spoon.reflect.code.CtTryWithResource createTryWithResource();

    /**
     * Creates a type parameter reference.
     */
    spoon.reflect.reference.CtTypeParameterReference createTypeParameterReference();

    /**
     * Creates a wildcard reference.
     */
    spoon.reflect.reference.CtWildcardReference createWildcardReference();

    /**
     * Creates an intersection type reference.
     */
    <T> spoon.reflect.reference.CtIntersectionTypeReference<T> createIntersectionTypeReference();

    /**
     * Creates a type reference.
     */
    <T> spoon.reflect.reference.CtTypeReference<T> createTypeReference();

    /**
     * Creates a type access expression.
     */
    <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess();

    /**
     * Creates a unary operator expression.
     */
    <T> spoon.reflect.code.CtUnaryOperator<T> createUnaryOperator();

    /**
     * Creates a variable read expression.
     */
    <T> spoon.reflect.code.CtVariableRead<T> createVariableRead();

    /**
     * Creates a variable write expression.
     */
    <T> spoon.reflect.code.CtVariableWrite<T> createVariableWrite();

    /**
     * Creates a <code>while</code> loop.
     */
    spoon.reflect.code.CtWhile createWhile();

    /**
     * Creates a code snippet expression.
     */
    <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression();

    /**
     * Creates a code snippet statement.
     */
    spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement();

    /**
     * Creates a comment.
     */
    spoon.reflect.code.CtComment createComment();

    /**
     * Creates a javadoc comment.
     */
    spoon.reflect.code.CtJavaDoc createJavaDoc();

    /**
     * Creates a javadoc tag.
     */
    spoon.reflect.code.CtJavaDocTag createJavaDocTag();

    /**
     * Creates an import.
     */
    spoon.reflect.declaration.CtImport createImport();

    /**
     * Gets the main factory of that core factory (cannot be <code>null</code>).
     */
    spoon.reflect.factory.Factory getMainFactory();

    /**
     * Sets the main factory of that core factory.
     */
    void setMainFactory(spoon.reflect.factory.Factory mainFactory);

    /**
     * Creates a compilation unit.
     */
    spoon.reflect.cu.CompilationUnit createCompilationUnit();

    /**
     * Create an access to annotation value
     *
     * @return 
     */
    <T> spoon.reflect.code.CtAnnotationFieldAccess<T> createAnnotationFieldAccess();

    /**
     * Creates an unbound variable used in noclasspath.
     */
    <T> spoon.reflect.reference.CtUnboundVariableReference<T> createUnboundVariableReference();

    /**
     * Creates an instance of the concrete metamodel class given as parameter.
     *
     * This is in particular useful when one uses reflection.
     */
    spoon.reflect.declaration.CtElement create(java.lang.Class<? extends spoon.reflect.declaration.CtElement> klass);

    /**
     * Create a wildcard reference to a type member, used in a static import
     */
    spoon.reflect.reference.CtTypeReference createWildcardStaticTypeMemberReference();

    /**
     * Creates a Java 9 module
     */
    spoon.reflect.declaration.CtModule createModule();

    /**
     * Creates a reference to a Java 9 module
     */
    spoon.reflect.reference.CtModuleReference createModuleReference();

    /**
     * Creates a "requires" directive for a Java 9 module file
     */
    spoon.reflect.declaration.CtModuleRequirement createModuleRequirement();

    /**
     * Creates a "export" directive for a Java 9 module file
     */
    spoon.reflect.declaration.CtPackageExport createPackageExport();

    /**
     * Creates a "provides" directive for a Java 9 module file
     */
    spoon.reflect.declaration.CtProvidedService createProvidedService();

    /**
     * Creates a "uses" directive for a Java 9 module file
     */
    spoon.reflect.declaration.CtUsedService createUsedService();
}

