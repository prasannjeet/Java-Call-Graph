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
 * Provides the sub-factories required by Spoon.
 *
 * Most classes provides a method getFactory() that returns the current factory.
 *
 * Otherwise FactoryImpl is a default implementation.
 */
public interface Factory {
    /**
     * returns the Spoon model that has been built with this factory or one of its subfactories
     */
    spoon.reflect.CtModel getModel();

    /**
     * Access to {@link CoreFactory} subfactory
     */
    spoon.reflect.factory.CoreFactory Core();

    /**
     * Access to {@link TypeFactory} subfactory
     */
    spoon.reflect.factory.TypeFactory Type();// used 107 times


    /**
     * Access to {@link EnumFactory} subfactory
     */
    spoon.reflect.factory.EnumFactory Enum();

    /**
     * Access to the {@link Environment}
     */
    spoon.compiler.Environment getEnvironment();

    /**
     * Access to {@link PackageFactory} subfactory
     */
    spoon.reflect.factory.PackageFactory Package();

    /**
     * Access to {@link CodeFactory} subfactory
     */
    spoon.reflect.factory.CodeFactory Code();

    /**
     * Access to {@link ClassFactory} subfactory
     */
    spoon.reflect.factory.ClassFactory Class();

    /**
     * Access to {@link FieldFactory} subfactory
     */
    spoon.reflect.factory.FieldFactory Field();

    /**
     * Access to {@link ExecutableFactory} subfactory
     */
    spoon.reflect.factory.ExecutableFactory Executable();

    /**
     * Access to {@link CompilationUnitFactory} subfactory
     */
    spoon.reflect.factory.CompilationUnitFactory CompilationUnit();

    /**
     * Access to {@link InterfaceFactory} subfactory
     */
    spoon.reflect.factory.InterfaceFactory Interface();

    /**
     * Access to {@link MethodFactory} subfactory
     */
    spoon.reflect.factory.MethodFactory Method();

    /**
     * Access to {@link AnnotationFactory} subfactory
     */
    spoon.reflect.factory.AnnotationFactory Annotation();

    /**
     * Access to {@link EvalFactory} subfactory
     */
    spoon.reflect.factory.EvalFactory Eval();

    /**
     * Access to {@link ConstructorFactory} subfactory
     */
    spoon.reflect.factory.ConstructorFactory Constructor();

    /**
     * Access to {@link QueryFactory} subfactory
     */
    spoon.reflect.factory.QueryFactory Query();

    /**
     * Access to {@link ModuleFactory} subfactory for Java 9 modules
     */
    spoon.reflect.factory.ModuleFactory Module();

    /**
     *
     *
     * @see CodeFactory#createAnnotation(CtTypeReference)
     */
    <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation(spoon.reflect.reference.CtTypeReference<A> annotationType);

    /**
     *
     *
     * @see CodeFactory#createVariableAssignment(CtVariableReference,boolean, CtExpression)
     */
    <A, T extends A> spoon.reflect.code.CtAssignment<A, T> createVariableAssignment(spoon.reflect.reference.CtVariableReference<A> variable, boolean isStatic, spoon.reflect.code.CtExpression<T> expression);

    /**
     *
     *
     * @see CodeFactory#createStatementList(CtBlock)
     */
    <R> spoon.reflect.code.CtStatementList createStatementList(spoon.reflect.code.CtBlock<R> block);

    /**
     *
     *
     * @see CodeFactory#createCtBlock(CtStatement)
     */
    <T extends spoon.reflect.code.CtStatement> spoon.reflect.code.CtBlock<?> createCtBlock(T element);

    /**
     *
     *
     * @see CodeFactory#createBinaryOperator(CtExpression,CtExpression, BinaryOperatorKind)
     */
    <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator(spoon.reflect.code.CtExpression<?> left, spoon.reflect.code.CtExpression<?> right, spoon.reflect.code.BinaryOperatorKind kind);

    /**
     *
     *
     * @see CodeFactory#createCatchVariable(CtTypeReference,String, ModifierKind[])
     */
    <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.declaration.ModifierKind... modifierKinds);

    /**
     *
     *
     * @see CodeFactory#createCodeSnippetExpression(String)
     */
    <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression(java.lang.String expression);

    /**
     *
     *
     * @see CodeFactory#createConstructorCall(CtTypeReference,CtExpression[])
     */
    <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.code.CtExpression<?>... parameters);

    /**
     *
     *
     * @see CodeFactory#createClassAccess(CtTypeReference)
     */
    <T> spoon.reflect.code.CtFieldAccess<java.lang.Class<T>> createClassAccess(spoon.reflect.reference.CtTypeReference<T> type);

    /**
     *
     *
     * @see CodeFactory#createInvocation(CtExpression, CtExecutableReference, List)
     */
    <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, java.util.List<spoon.reflect.code.CtExpression<?>> arguments);

    /**
     *
     *
     * @see CodeFactory#createInvocation(CtExpression,CtExecutableReference,CtExpression[])
     */
    <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, spoon.reflect.code.CtExpression<?>... arguments);

    /**
     *
     *
     * @see CodeFactory#createLiteral(Object)
     */
    <T> spoon.reflect.code.CtLiteral<T> createLiteral(T value);

    /**
     *
     *
     * @see CodeFactory#createLocalVariable(CtTypeReference,String,CtExpression)
     */
    <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.code.CtExpression<T> defaultExpression);

    /**
     *
     *
     * @see CodeFactory#createLiteralArray(Object[])
     */
    @java.lang.SuppressWarnings("unchecked")
    <T> spoon.reflect.code.CtNewArray<T[]> createLiteralArray(T[] value);

    /**
     *
     *
     * @see CodeFactory#createNewClass(CtTypeReference, CtClass,CtExpression[])
     */
    <T> spoon.reflect.code.CtNewClass<T> createNewClass(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.declaration.CtClass<?> anonymousClass, spoon.reflect.code.CtExpression<?>... parameters);

    /**
     *
     *
     * @see CodeFactory#createVariableAssignments(List,List)
     */
    <T> spoon.reflect.code.CtStatementList createVariableAssignments(java.util.List<? extends spoon.reflect.declaration.CtVariable<T>> variables, java.util.List<? extends spoon.reflect.code.CtExpression<T>> expressions);

    /**
     *
     *
     * @see CodeFactory#createThisAccess(CtTypeReference)
     */
    <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type);

    /**
     *
     *
     * @see CodeFactory#createThisAccess(CtTypeReference,boolean)
     */
    <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type, boolean isImplicit);

    /**
     *
     *
     * @see CodeFactory#createTypeAccess(CtTypeReference)
     */
    <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType);

    /**
     *
     *
     * @see CodeFactory#createTypeAccess(CtTypeReference,boolean)
     */
    <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType, boolean isImplicit);

    /**
     *
     *
     * @see CodeFactory#createTypeAccessWithoutCloningReference(CtTypeReference)
     */
    <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccessWithoutCloningReference(spoon.reflect.reference.CtTypeReference<T> accessedType);

    /**
     *
     *
     * @see CodeFactory#createVariableRead(CtVariableReference,boolean)
     */
    <T> spoon.reflect.code.CtVariableAccess<T> createVariableRead(spoon.reflect.reference.CtVariableReference<T> variable, boolean isStatic);

    /**
     *
     *
     * @see CodeFactory#createCtField(String,CtTypeReference,String,ModifierKind[])
     */
    <T> spoon.reflect.declaration.CtField<T> createCtField(java.lang.String name, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String exp, spoon.reflect.declaration.ModifierKind... visibilities);

    /**
     *
     *
     * @see CodeFactory#createCatchVariableReference(CtCatchVariable)
     */
    <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference(spoon.reflect.code.CtCatchVariable<T> catchVariable);

    /**
     *
     *
     * @see CodeFactory#createLocalVariableReference(CtLocalVariable)
     */
    <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.code.CtLocalVariable<T> localVariable);

    /**
     *
     *
     * @see CodeFactory#createLocalVariableReference(CtTypeReference,String)
     */
    <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name);

    /**
     *
     *
     * @see CodeFactory#createCtTypeReference(Class)
     */
    <T> spoon.reflect.reference.CtTypeReference<T> createCtTypeReference(java.lang.Class<?> originalClass);

    /**
     *
     *
     * @see CodeFactory#createVariableReads(List)
     */
    java.util.List<spoon.reflect.code.CtExpression<?>> createVariableReads(java.util.List<? extends spoon.reflect.declaration.CtVariable<?>> variables);

    /**
     *
     *
     * @see CodeFactory#createCtCatch(String,Class,CtBlock)
     */
    spoon.reflect.code.CtCatch createCtCatch(java.lang.String nameCatch, java.lang.Class<? extends java.lang.Throwable> exception, spoon.reflect.code.CtBlock<?> ctBlock);

    /**
     *
     *
     * @see CodeFactory#createCodeSnippetStatement(String)
     */
    spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement(java.lang.String statement);

    /**
     *
     *
     * @see CodeFactory#createComment(String,CtComment.CommentType)
     */
    spoon.reflect.code.CtComment createComment(java.lang.String content, spoon.reflect.code.CtComment.CommentType type);

    /**
     *
     *
     * @see CodeFactory#createJavaDocTag(String,CtJavaDocTag.TagType)
     */
    spoon.reflect.code.CtJavaDocTag createJavaDocTag(java.lang.String content, spoon.reflect.code.CtJavaDocTag.TagType type);

    /**
     *
     *
     * @see CodeFactory#createInlineComment(String)
     */
    spoon.reflect.code.CtComment createInlineComment(java.lang.String content);

    /**
     *
     *
     * @see CodeFactory#createCtThrow(String)
     */
    spoon.reflect.code.CtThrow createCtThrow(java.lang.String thrownExp);

    /**
     *
     *
     * @see CodeFactory#createCtPackageReference(Package)
     */
    spoon.reflect.reference.CtPackageReference createCtPackageReference(java.lang.Package originalPackage);

    /**
     *
     *
     * @see ConstructorFactory#createDefault(CtClass)
     */
    <T> spoon.reflect.declaration.CtConstructor<T> createDefault(spoon.reflect.declaration.CtClass<T> target);

    /**
     *
     *
     * @see CoreFactory#createAnnotation()
     */
    <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation();

    /**
     *
     *
     * @see CoreFactory#createBlock()
     */
    <R> spoon.reflect.code.CtBlock<R> createBlock();

    /**
     *
     *
     * @see CoreFactory#createReturn()
     */
    <R> spoon.reflect.code.CtReturn<R> createReturn();

    /**
     *
     *
     * @see CoreFactory#createStatementList()
     */
    <R> spoon.reflect.code.CtStatementList createStatementList();

    /**
     *
     *
     * @see CoreFactory#createCase()
     */
    <S> spoon.reflect.code.CtCase<S> createCase();

    /**
     *
     *
     * @see CoreFactory#createSwitch()
     */
    <S> spoon.reflect.code.CtSwitch<S> createSwitch();

    /**
     *
     *
     * @see CoreFactory#createEnum()
     */
    <T extends java.lang.Enum<?>> spoon.reflect.declaration.CtEnum<T> createEnum();

    /**
     *
     *
     * @see CoreFactory#createAnnotationType()
     */
    <T extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotationType<T> createAnnotationType();

    /**
     *
     *
     * @see CoreFactory#createAssignment()
     */
    <T, A extends T> spoon.reflect.code.CtAssignment<T, A> createAssignment();

    /**
     *
     *
     * @see CoreFactory#createOperatorAssignment()
     */
    <T, A extends T> spoon.reflect.code.CtOperatorAssignment<T, A> createOperatorAssignment();

    /**
     *
     *
     * @see CoreFactory#createExecutableReferenceExpression()
     */
    <T, E extends spoon.reflect.code.CtExpression<?>> spoon.reflect.code.CtExecutableReferenceExpression<T, E> createExecutableReferenceExpression();

    /**
     *
     *
     * @see CoreFactory#createAnnotationFieldAccess()
     */
    <T> spoon.reflect.code.CtAnnotationFieldAccess<T> createAnnotationFieldAccess();

    /**
     *
     *
     * @see CoreFactory#createArrayRead()
     */
    <T> spoon.reflect.code.CtArrayRead<T> createArrayRead();

    /**
     *
     *
     * @see CoreFactory#createArrayWrite()
     */
    <T> spoon.reflect.code.CtArrayWrite<T> createArrayWrite();

    /**
     *
     *
     * @see CoreFactory#createAssert()
     */
    <T> spoon.reflect.code.CtAssert<T> createAssert();

    /**
     *
     *
     * @see CoreFactory#createBinaryOperator()
     */
    <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator();

    /**
     *
     *
     * @see CoreFactory#createCatchVariable()
     */
    <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable();

    /**
     *
     *
     * @see CoreFactory#createCodeSnippetExpression()
     */
    <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression();

    /**
     *
     *
     * @see CoreFactory#createConditional()
     */
    <T> spoon.reflect.code.CtConditional<T> createConditional();

    /**
     *
     *
     * @see CoreFactory#createConstructorCall()
     */
    <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall();

    /**
     *
     *
     * @see CoreFactory#createFieldRead()
     */
    <T> spoon.reflect.code.CtFieldRead<T> createFieldRead();

    /**
     *
     *
     * @see CoreFactory#createFieldWrite()
     */
    <T> spoon.reflect.code.CtFieldWrite<T> createFieldWrite();

    /**
     *
     *
     * @see CoreFactory#createInvocation()
     */
    <T> spoon.reflect.code.CtInvocation<T> createInvocation();

    /**
     *
     *
     * @see CoreFactory#createLambda()
     */
    <T> spoon.reflect.code.CtLambda<T> createLambda();

    /**
     *
     *
     * @see CoreFactory#createLiteral()
     */
    <T> spoon.reflect.code.CtLiteral<T> createLiteral();

    /**
     *
     *
     * @see CoreFactory#createLocalVariable()
     */
    <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable();

    /**
     *
     *
     * @see CoreFactory#createNewArray()
     */
    <T> spoon.reflect.code.CtNewArray<T> createNewArray();

    /**
     *
     *
     * @see CoreFactory#createNewClass()
     */
    <T> spoon.reflect.code.CtNewClass<T> createNewClass();

    /**
     *
     *
     * @see CoreFactory#createSuperAccess()
     */
    <T> spoon.reflect.code.CtSuperAccess<T> createSuperAccess();

    /**
     *
     *
     * @see CoreFactory#createThisAccess()
     */
    <T> spoon.reflect.code.CtThisAccess<T> createThisAccess();

    /**
     *
     *
     * @see CoreFactory#createTypeAccess()
     */
    <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess();

    /**
     *
     *
     * @see CoreFactory#createUnaryOperator()
     */
    <T> spoon.reflect.code.CtUnaryOperator<T> createUnaryOperator();

    /**
     *
     *
     * @see CoreFactory#createVariableRead()
     */
    <T> spoon.reflect.code.CtVariableRead<T> createVariableRead();

    /**
     *
     *
     * @see CoreFactory#createVariableWrite()
     */
    <T> spoon.reflect.code.CtVariableWrite<T> createVariableWrite();

    /**
     *
     *
     * @see CoreFactory#createAnnotationMethod()
     */
    <T> spoon.reflect.declaration.CtAnnotationMethod<T> createAnnotationMethod();

    /**
     *
     *
     * @see CoreFactory#createClass()
     */
    <T> spoon.reflect.declaration.CtClass<T> createClass();

    /**
     *
     *
     * @see CoreFactory#createConstructor()
     */
    <T> spoon.reflect.declaration.CtConstructor<T> createConstructor();

    /**
     *
     *
     * @see CoreFactory#createInvisibleArrayConstructor() ()
     */
    <T> spoon.reflect.declaration.CtConstructor<T> createInvisibleArrayConstructor();

    /**
     *
     *
     * @see CoreFactory#createEnumValue()
     */
    <T> spoon.reflect.declaration.CtEnumValue<T> createEnumValue();

    /**
     *
     *
     * @see CoreFactory#createField()
     */
    <T> spoon.reflect.declaration.CtField<T> createField();

    /**
     *
     *
     * @see CoreFactory#createInterface()
     */
    <T> spoon.reflect.declaration.CtInterface<T> createInterface();

    /**
     *
     *
     * @see CoreFactory#createMethod()
     */
    <T> spoon.reflect.declaration.CtMethod<T> createMethod();

    /**
     *
     *
     * @see CoreFactory#createParameter()
     */
    <T> spoon.reflect.declaration.CtParameter<T> createParameter();

    /**
     *
     *
     * @see CoreFactory#createArrayTypeReference()
     */
    <T> spoon.reflect.reference.CtArrayTypeReference<T> createArrayTypeReference();

    /**
     *
     *
     * @see CoreFactory#createCatchVariableReference()
     */
    <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference();

    /**
     *
     *
     * @see CoreFactory#createExecutableReference()
     */
    <T> spoon.reflect.reference.CtExecutableReference<T> createExecutableReference();

    /**
     *
     *
     * @see CoreFactory#createFieldReference()
     */
    <T> spoon.reflect.reference.CtFieldReference<T> createFieldReference();

    /**
     *
     *
     * @see CoreFactory#createIntersectionTypeReference()
     */
    <T> spoon.reflect.reference.CtIntersectionTypeReference<T> createIntersectionTypeReference();

    /**
     *
     *
     * @see CoreFactory#createLocalVariableReference()
     */
    <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference();

    /**
     *
     *
     * @see CoreFactory#createParameterReference()
     */
    <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference();

    /**
     *
     *
     * @see CoreFactory#createTypeReference()
     */
    <T> spoon.reflect.reference.CtTypeReference<T> createTypeReference();

    /**
     *
     *
     * @see CoreFactory#createUnboundVariableReference()
     */
    <T> spoon.reflect.reference.CtUnboundVariableReference<T> createUnboundVariableReference();

    /**
     *
     *
     * @see CoreFactory#createBreak()
     */
    spoon.reflect.code.CtBreak createBreak();

    /**
     *
     *
     * @see CoreFactory#createCatch()
     */
    spoon.reflect.code.CtCatch createCatch();

    /**
     *
     *
     * @see CoreFactory#createCodeSnippetStatement()
     */
    spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement();

    /**
     *
     *
     * @see CoreFactory#createComment()
     */
    spoon.reflect.code.CtComment createComment();

    /**
     *
     *
     * @see CoreFactory#createContinue()
     */
    spoon.reflect.code.CtContinue createContinue();

    /**
     *
     *
     * @see CoreFactory#createDo()
     */
    spoon.reflect.code.CtDo createDo();

    /**
     *
     *
     * @see CoreFactory#createFor()
     */
    spoon.reflect.code.CtFor createFor();

    /**
     *
     *
     * @see CoreFactory#createForEach()
     */
    spoon.reflect.code.CtForEach createForEach();

    /**
     *
     *
     * @see CoreFactory#createIf()
     */
    spoon.reflect.code.CtIf createIf();

    /**
     *
     *
     * @see CoreFactory#createSynchronized()
     */
    spoon.reflect.code.CtSynchronized createSynchronized();

    /**
     *
     *
     * @see CoreFactory#createThrow()
     */
    spoon.reflect.code.CtThrow createThrow();

    /**
     *
     *
     * @see CoreFactory#createTry()
     */
    spoon.reflect.code.CtTry createTry();

    /**
     *
     *
     * @see CoreFactory#createTryWithResource()
     */
    spoon.reflect.code.CtTryWithResource createTryWithResource();

    /**
     *
     *
     * @see CoreFactory#createWhile()
     */
    spoon.reflect.code.CtWhile createWhile();

    /**
     *
     *
     * @see CoreFactory#createCompilationUnit()
     */
    spoon.reflect.cu.CompilationUnit createCompilationUnit();

    /**
     *
     *
     * @see CoreFactory#createSourcePosition(CompilationUnit,int,int,int[])
     */
    spoon.reflect.cu.SourcePosition createSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int[] lineSeparatorPositions);

    /**
     *
     *
     * @see CoreFactory#createBodyHolderSourcePosition(CompilationUnit,int,int,int,int,int,int,int,int,int[])
     */
    spoon.reflect.cu.position.BodyHolderSourcePosition createBodyHolderSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int bodyStart, int bodyEnd, int[] lineSeparatorPositions);

    /**
     *
     *
     * @see CoreFactory#createDeclarationSourcePosition(CompilationUnit,int,int,int,int,int,int,int[])
     */
    spoon.reflect.cu.position.DeclarationSourcePosition createDeclarationSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int[] lineSeparatorPositions);

    /**
     *
     *
     * @see CoreFactory#createAnonymousExecutable()
     */
    spoon.reflect.declaration.CtAnonymousExecutable createAnonymousExecutable();

    /**
     *
     *
     * @see CoreFactory#createPackage()
     */
    spoon.reflect.declaration.CtPackage createPackage();

    /**
     *
     *
     * @see CoreFactory#createTypeParameter()
     */
    spoon.reflect.declaration.CtTypeParameter createTypeParameter();

    /**
     *
     *
     * @see CoreFactory#createPackageReference()
     */
    spoon.reflect.reference.CtPackageReference createPackageReference();

    /**
     *
     *
     * @see CoreFactory#createTypeParameterReference()
     */
    spoon.reflect.reference.CtTypeParameterReference createTypeParameterReference();

    /**
     *
     *
     * @see CoreFactory#createWildcardReference()
     */
    spoon.reflect.reference.CtWildcardReference createWildcardReference();

    /**
     *
     *
     * @see EvalFactory#createPartialEvaluator()
     */
    spoon.reflect.eval.PartialEvaluator createPartialEvaluator();

    /**
     *
     *
     * @see ExecutableFactory#createParameter(CtExecutable,CtTypeReference,String)
     */
    <T> spoon.reflect.declaration.CtParameter<T> createParameter(spoon.reflect.declaration.CtExecutable<?> parent, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name);

    /**
     *
     *
     * @see ExecutableFactory#createParameterReference(CtParameter)
     */
    <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference(spoon.reflect.declaration.CtParameter<T> parameter);

    /**
     *
     *
     * @see ExecutableFactory#createAnonymous(CtClass,CtBlock)
     */
    spoon.reflect.declaration.CtAnonymousExecutable createAnonymous(spoon.reflect.declaration.CtClass<?> target, spoon.reflect.code.CtBlock<java.lang.Void> body);

    /**
     *
     *
     * @see TypeFactory#createArrayReference(String)
     */
    <T> spoon.reflect.reference.CtArrayTypeReference<T> createArrayReference(java.lang.String qualifiedName);

    /**
     *
     *
     * @see TypeFactory#createArrayReference(CtType)
     */
    <T> spoon.reflect.reference.CtArrayTypeReference<T[]> createArrayReference(spoon.reflect.declaration.CtType<T> type);

    /**
     *
     *
     * @see TypeFactory#createArrayReference(CtTypeReference)
     */
    <T> spoon.reflect.reference.CtArrayTypeReference<T[]> createArrayReference(spoon.reflect.reference.CtTypeReference<T> reference);

    /**
     *
     *
     * @see TypeFactory#createIntersectionTypeReferenceWithBounds(List)
     */
    <T> spoon.reflect.reference.CtIntersectionTypeReference<T> createIntersectionTypeReferenceWithBounds(java.util.List<spoon.reflect.reference.CtTypeReference<?>> bounds);

    /**
     *
     *
     * @see TypeFactory#createTypeAdapter(CtFormalTypeDeclarer)
     */
    spoon.support.visitor.GenericTypeAdapter createTypeAdapter(spoon.reflect.declaration.CtFormalTypeDeclarer formalTypeDeclarer);

    /**
     *
     *
     * @see TypeFactory#createReferences(List)
     */
    java.util.List<spoon.reflect.reference.CtTypeReference<?>> createReferences(java.util.List<java.lang.Class<?>> classes);

    /**
     *
     *
     * @see TypeFactory#createArrayReference(CtTypeReference,int)
     */
    spoon.reflect.reference.CtArrayTypeReference<?> createArrayReference(spoon.reflect.reference.CtTypeReference<?> reference, int n);

    /**
     *
     *
     * @see TypeFactory#createTypeParameterReference(String)
     */
    spoon.reflect.reference.CtTypeParameterReference createTypeParameterReference(java.lang.String name);

    /**
     *
     *
     * @see QueryFactory#createQuery()
     */
    spoon.reflect.visitor.chain.CtQuery createQuery();

    /**
     *
     *
     * @see QueryFactory#createQuery(Object)
     */
    spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object input);

    /**
     *
     *
     * @see QueryFactory#createQuery(Object...)
     */
    spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object... input);

    /**
     *
     *
     * @see QueryFactory#createQuery(Iterable)
     */
    spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Iterable<?> input);

    /**
     *
     *
     * @see AnnotationFactory#create(String)
     */
    spoon.reflect.declaration.CtAnnotationType createAnnotationType(java.lang.String qualifiedName);

    /**
     *
     *
     * @see AnnotationFactory#create(CtPackage, String)
     */
    spoon.reflect.declaration.CtAnnotationType createAnnotationType(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName);

    /**
     *
     *
     * @see ClassFactory#create(String)
     */
    spoon.reflect.declaration.CtClass createClass(java.lang.String qualifiedName);

    /**
     *
     *
     * @see ClassFactory#create(CtClass, String)
     */
    spoon.reflect.declaration.CtClass createClass(spoon.reflect.declaration.CtClass<?> declaringClass, java.lang.String simpleName);

    /**
     *
     *
     * @see ClassFactory#create(CtPackage, String)
     */
    spoon.reflect.declaration.CtClass createClass(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName);

    /**
     *
     *
     * @see ConstructorFactory#create(CtClass, CtConstructor)
     */
    spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, spoon.reflect.declaration.CtConstructor<?> source);

    /**
     *
     *
     * @see ConstructorFactory#create(CtClass, CtMethod)
     */
    spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, spoon.reflect.declaration.CtMethod<?> source);

    /**
     *
     *
     * @see ConstructorFactory#create(CtClass, Set, List, Set)
     */
    spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes);

    /**
     *
     *
     * @see ConstructorFactory#create(CtClass, Set, List, Set, CtBlock)
     */
    spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock body);

    /**
     *
     *
     * @see EnumFactory#create(String)
     */
    spoon.reflect.declaration.CtEnum<?> createEnum(java.lang.String qualifiedName);

    /**
     *
     *
     * @see EnumFactory#create(CtPackage, String)
     */
    spoon.reflect.declaration.CtEnum<?> createEnum(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName);

    /**
     *
     *
     * @see FieldFactory#create(CtType, Set, CtTypeReference, String)
     */
    spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference type, java.lang.String name);

    /**
     *
     *
     * @see FieldFactory#create(CtType, Set, CtTypeReference, String, CtExpression)
     */
    spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference type, java.lang.String name, spoon.reflect.code.CtExpression defaultExpression);

    /**
     *
     *
     * @see FieldFactory#create(CtType, CtField)
     */
    spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtField source);

    /**
     *
     *
     * @see InterfaceFactory#create(CtPackage, String)
     */
    spoon.reflect.declaration.CtInterface createInterface(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName);

    /**
     *
     *
     * @see InterfaceFactory#create(CtType, String)
     */
    spoon.reflect.declaration.CtInterface createInterface(spoon.reflect.declaration.CtType owner, java.lang.String simpleName);

    /**
     *
     *
     * @see InterfaceFactory#create(String)
     */
    spoon.reflect.declaration.CtInterface createInterface(java.lang.String qualifiedName);

    /**
     *
     *
     * @see MethodFactory#create(CtClass, Set, CtTypeReference, String, List, Set, CtBlock)
     */
    spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtClass<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock body);

    /**
     *
     *
     * @see MethodFactory#create(CtType, CtMethod, boolean)
     */
    spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtMethod source, boolean redirectReferences);

    /**
     *
     *
     * @see MethodFactory#create(CtType, Set, CtTypeReference, String, List, Set)
     */
    spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes);

    /**
     *
     *
     * @see PackageFactory#create(CtPackage, String)
     */
    spoon.reflect.declaration.CtPackage createPackage(spoon.reflect.declaration.CtPackage parent, java.lang.String simpleName);

    /**
     *
     *
     * @see CoreFactory#create(Class)
     */
    spoon.reflect.declaration.CtElement createElement(java.lang.Class<? extends spoon.reflect.declaration.CtElement> klass);

    /**
     *
     *
     * @see TypeFactory#createImport(CtReference)
     */
    spoon.reflect.declaration.CtImport createImport(spoon.reflect.reference.CtReference reference);

    /**
     *
     *
     * @see TypeFactory#createWildcardStaticTypeMemberReference(CtTypeReference)
     */
    spoon.reflect.reference.CtTypeReference createWildcardStaticTypeMemberReference(spoon.reflect.reference.CtTypeReference typeReference);

    /**
     *
     *
     * @see ModuleFactory#createPackageExport(CtPackageReference)
     */
    spoon.reflect.declaration.CtPackageExport createPackageExport(spoon.reflect.reference.CtPackageReference ctPackageReference);

    /**
     *
     *
     * @see ModuleFactory#createProvidedService(CtTypeReference)
     */
    spoon.reflect.declaration.CtProvidedService createProvidedService(spoon.reflect.reference.CtTypeReference ctTypeReference);

    /**
     *
     *
     * @see ModuleFactory#createModuleRequirement(CtModuleReference)
     */
    spoon.reflect.declaration.CtModuleRequirement createModuleRequirement(spoon.reflect.reference.CtModuleReference ctModuleReference);

    /**
     *
     *
     * @see ModuleFactory#getOrCreate(String)
     */
    spoon.reflect.declaration.CtModule createModule(java.lang.String moduleName);

    /**
     *
     *
     * @see ModuleFactory#createReference(CtModule)
     */
    spoon.reflect.reference.CtModuleReference createModuleReference(spoon.reflect.declaration.CtModule ctModule);

    /**
     *
     *
     * @see ModuleFactory#createUsedService(CtTypeReference)
     */
    spoon.reflect.declaration.CtUsedService createUsedService(spoon.reflect.reference.CtTypeReference typeReference);

    /**
     *
     *
     * @see CoreFactory#createPartialSourcePosition(CompilationUnit)
     */
    spoon.reflect.cu.SourcePosition createPartialSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit);
}

