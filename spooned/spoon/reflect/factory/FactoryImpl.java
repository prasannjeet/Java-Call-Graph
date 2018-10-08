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
 * Implements {@link Factory}
 */
public class FactoryImpl implements java.io.Serializable , spoon.reflect.factory.Factory {
    private static final long serialVersionUID = 1L;

    private transient spoon.reflect.factory.Factory parentFactory;

    /**
     * Returns the parent of this factory. When an element is not found in a
     * factory, it can be looked up in its parent factory using a delegation
     * model.
     */
    public spoon.reflect.factory.Factory getParentFactory() {
        return parentFactory;
    }

    private transient spoon.reflect.factory.AnnotationFactory annotation;

    /**
     * The {@link CtAnnotationType} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.AnnotationFactory Annotation() {
        if ((annotation) == null) {
            annotation = new spoon.reflect.factory.AnnotationFactory(this);
        }
        return annotation;
    }

    private transient spoon.reflect.factory.ClassFactory clazz;

    /**
     * The {@link CtClass} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.ClassFactory Class() {
        if ((clazz) == null) {
            clazz = new spoon.reflect.factory.ClassFactory(this);
        }
        return clazz;
    }

    private transient spoon.reflect.factory.CodeFactory code;

    /**
     * The {@link spoon.reflect.code.CtCodeElement} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.CodeFactory Code() {
        if ((code) == null) {
            code = new spoon.reflect.factory.CodeFactory(this);
        }
        return code;
    }

    private transient spoon.reflect.factory.ConstructorFactory constructor;

    /**
     * The {@link CtConstructor} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.ConstructorFactory Constructor() {
        if ((constructor) == null) {
            constructor = new spoon.reflect.factory.ConstructorFactory(this);
        }
        return constructor;
    }

    private transient spoon.reflect.factory.CoreFactory core;

    /**
     * The core factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.CoreFactory Core() {
        if ((core) == null) {
            // During deserialization, the transient field core, is null
            core = new spoon.support.DefaultCoreFactory();
            core.setMainFactory(this);
        }
        return core;
    }

    private transient spoon.reflect.factory.EnumFactory enumF;

    /**
     * The {@link CtEnum} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.EnumFactory Enum() {
        if ((enumF) == null) {
            enumF = new spoon.reflect.factory.EnumFactory(this);
        }
        return enumF;
    }

    private transient spoon.compiler.Environment environment;

    /**
     * Gets the Spoon environment that encloses this factory.
     */
    @java.lang.Override
    public spoon.compiler.Environment getEnvironment() {
        if ((environment) == null) {
            environment = new spoon.support.StandardEnvironment();
        }
        return environment;
    }

    private transient spoon.reflect.factory.ExecutableFactory executable;

    /**
     * The {@link CtExecutable} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.ExecutableFactory Executable() {
        if ((executable) == null) {
            executable = new spoon.reflect.factory.ExecutableFactory(this);
        }
        return executable;
    }

    private transient spoon.reflect.factory.EvalFactory eval;

    /**
     * The evaluators sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.EvalFactory Eval() {
        if ((eval) == null) {
            eval = new spoon.reflect.factory.EvalFactory(this);
        }
        return eval;
    }

    private transient spoon.reflect.factory.FieldFactory field;

    /**
     * The {@link CtField} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.FieldFactory Field() {
        if ((field) == null) {
            field = new spoon.reflect.factory.FieldFactory(this);
        }
        return field;
    }

    /**
     * The {@link CtInterface} sub-factory.
     */
    private transient spoon.reflect.factory.InterfaceFactory interfaceF;

    /**
     * The {@link CtInterface} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.InterfaceFactory Interface() {
        if ((interfaceF) == null) {
            interfaceF = new spoon.reflect.factory.InterfaceFactory(this);
        }
        return interfaceF;
    }

    private transient spoon.reflect.factory.MethodFactory methodF;

    /**
     * The {@link CtMethod} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.MethodFactory Method() {
        if ((methodF) == null) {
            methodF = new spoon.reflect.factory.MethodFactory(this);
        }
        return methodF;
    }

    private transient spoon.reflect.factory.PackageFactory packageF;

    /**
     * The {@link CtPackage} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.PackageFactory Package() {
        if ((packageF) == null) {
            packageF = new spoon.reflect.factory.PackageFactory(this);
        }
        return packageF;
    }

    private transient spoon.reflect.factory.CompilationUnitFactory compilationUnit;

    /**
     * The {@link CompilationUnit} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.CompilationUnitFactory CompilationUnit() {
        if ((compilationUnit) == null) {
            compilationUnit = new spoon.reflect.factory.CompilationUnitFactory(this);
        }
        return compilationUnit;
    }

    private transient spoon.reflect.factory.TypeFactory type;

    /**
     * The {@link CtType} sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.TypeFactory Type() {
        if ((type) == null) {
            type = new spoon.reflect.factory.TypeFactory(this);
        }
        return type;
    }

    private transient spoon.reflect.factory.QueryFactory query;

    /**
     * The query sub-factory.
     */
    @java.lang.Override
    public spoon.reflect.factory.QueryFactory Query() {
        if ((query) == null) {
            query = new spoon.reflect.factory.QueryFactory(this);
        }
        return query;
    }

    private transient spoon.reflect.factory.ModuleFactory module;

    /**
     * The module sub-factory
     */
    @java.lang.Override
    public spoon.reflect.factory.ModuleFactory Module() {
        if ((module) == null) {
            module = new spoon.reflect.factory.ModuleFactory(this);
        }
        return module;
    }

    /**
     * A constructor that takes the parent factory
     */
    public FactoryImpl(spoon.reflect.factory.CoreFactory coreFactory, spoon.compiler.Environment environment, spoon.reflect.factory.Factory parentFactory) {
        this.environment = environment;
        this.core = coreFactory;
        this.core.setMainFactory(this);
        this.parentFactory = parentFactory;
    }

    /**
     * Should not be called directly. Use {@link spoon.Launcher#createFactory()} instead.
     */
    public FactoryImpl(spoon.reflect.factory.CoreFactory coreFactory, spoon.compiler.Environment environment) {
        this(coreFactory, environment, null);
    }

    // Deduplication
    // See http://shipilev.net/talks/joker-Oct2014-string-catechism.pdf
    private static class Dedup {
        java.util.Map<java.lang.String, java.lang.String> cache = new java.util.HashMap<>();

        java.util.Random random = java.util.concurrent.ThreadLocalRandom.current();
    }

    /**
     * Note this is an instance field. To avoid memory leaks and dedup being
     * targeted to each Spoon Launching, that could differ a lot by
     * frequently used symbols.
     */
    private transient java.lang.ThreadLocal<spoon.reflect.factory.FactoryImpl.Dedup> threadLocalDedup = new java.lang.ThreadLocal<spoon.reflect.factory.FactoryImpl.Dedup>() {
        @java.lang.Override
        protected spoon.reflect.factory.FactoryImpl.Dedup initialValue() {
            return new spoon.reflect.factory.FactoryImpl.Dedup();
        }
    };

    /**
     * Returns a String equal to the given symbol. Performs probablilistic
     * deduplication.
     */
    public java.lang.String dedup(java.lang.String symbol) {
        spoon.reflect.factory.FactoryImpl.Dedup dedup = threadLocalDedup.get();
        java.util.Map<java.lang.String, java.lang.String> cache = dedup.cache;
        java.lang.String cached;
        if ((cached = cache.get(symbol)) != null) {
            return cached;
        }else {
            // Puts the symbol into cache with 20% probability
            int prob = ((int) ((java.lang.Integer.MIN_VALUE) + (0.2 * (1L << 32))));
            if ((dedup.random.nextInt()) < prob) {
                cache.put(symbol, symbol);
            }
            return symbol;
        }
    }

    /**
     * Needed to restore state of transient fields during reading from stream
     */
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
        threadLocalDedup = new java.lang.ThreadLocal<spoon.reflect.factory.FactoryImpl.Dedup>() {
            @java.lang.Override
            protected spoon.reflect.factory.FactoryImpl.Dedup initialValue() {
                return new spoon.reflect.factory.FactoryImpl.Dedup();
            }
        };
        in.defaultReadObject();
    }

    private final spoon.reflect.CtModel model = new spoon.reflect.CtModelImpl(this);

    @java.lang.Override
    public spoon.reflect.CtModel getModel() {
        return model;
    }

    @java.lang.Override
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation(spoon.reflect.reference.CtTypeReference<A> annotationType) {
        return Code().createAnnotation(annotationType);
    }

    @java.lang.Override
    public <A, T extends A> spoon.reflect.code.CtAssignment<A, T> createVariableAssignment(spoon.reflect.reference.CtVariableReference<A> variable, boolean isStatic, spoon.reflect.code.CtExpression<T> expression) {
        return Code().createVariableAssignment(variable, isStatic, expression);
    }

    @java.lang.Override
    public <R> spoon.reflect.code.CtStatementList createStatementList(spoon.reflect.code.CtBlock<R> block) {
        return Code().createStatementList(block);
    }

    @java.lang.Override
    public <T extends spoon.reflect.code.CtStatement> spoon.reflect.code.CtBlock<?> createCtBlock(T element) {
        return Code().createCtBlock(element);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator(spoon.reflect.code.CtExpression<?> left, spoon.reflect.code.CtExpression<?> right, spoon.reflect.code.BinaryOperatorKind kind) {
        return Code().createBinaryOperator(left, right, kind);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.declaration.ModifierKind... modifierKinds) {
        return Code().createCatchVariable(type, name, modifierKinds);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression(java.lang.String expression) {
        return Code().createCodeSnippetExpression(expression);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.code.CtExpression<?>... parameters) {
        return Code().createConstructorCall(type, parameters);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtFieldAccess<java.lang.Class<T>> createClassAccess(spoon.reflect.reference.CtTypeReference<T> type) {
        return Code().createClassAccess(type);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, java.util.List<spoon.reflect.code.CtExpression<?>> arguments) {
        return Code().createInvocation(target, executable, arguments);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtInvocation<T> createInvocation(spoon.reflect.code.CtExpression<?> target, spoon.reflect.reference.CtExecutableReference<T> executable, spoon.reflect.code.CtExpression<?>... arguments) {
        return Code().createInvocation(target, executable, arguments);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtLiteral<T> createLiteral(T value) {
        return Code().createLiteral(value);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.code.CtExpression<T> defaultExpression) {
        return Code().createLocalVariable(type, name, defaultExpression);
    }

    @java.lang.SuppressWarnings("unchecked")
    @java.lang.Override
    public <T> spoon.reflect.code.CtNewArray<T[]> createLiteralArray(T[] value) {
        return Code().createLiteralArray(value);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtNewClass<T> createNewClass(spoon.reflect.reference.CtTypeReference<T> type, spoon.reflect.declaration.CtClass<?> anonymousClass, spoon.reflect.code.CtExpression<?>... parameters) {
        return Code().createNewClass(type, anonymousClass, parameters);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtStatementList createVariableAssignments(java.util.List<? extends spoon.reflect.declaration.CtVariable<T>> variables, java.util.List<? extends spoon.reflect.code.CtExpression<T>> expressions) {
        return Code().createVariableAssignments(variables, expressions);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type) {
        return Code().createThisAccess(type);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtThisAccess<T> createThisAccess(spoon.reflect.reference.CtTypeReference<T> type, boolean isImplicit) {
        return Code().createThisAccess(type, isImplicit);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType) {
        return Code().createTypeAccess(accessedType);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess(spoon.reflect.reference.CtTypeReference<T> accessedType, boolean isImplicit) {
        return Code().createTypeAccess(accessedType, isImplicit);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccessWithoutCloningReference(spoon.reflect.reference.CtTypeReference<T> accessedType) {
        return Code().createTypeAccessWithoutCloningReference(accessedType);
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtVariableAccess<T> createVariableRead(spoon.reflect.reference.CtVariableReference<T> variable, boolean isStatic) {
        return Code().createVariableRead(variable, isStatic);
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtField<T> createCtField(java.lang.String name, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String exp, spoon.reflect.declaration.ModifierKind... visibilities) {
        return Code().createCtField(name, type, exp, visibilities);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference(spoon.reflect.code.CtCatchVariable<T> catchVariable) {
        return Code().createCatchVariableReference(catchVariable);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.code.CtLocalVariable<T> localVariable) {
        return Code().createLocalVariableReference(localVariable);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference(spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name) {
        return Code().createLocalVariableReference(type, name);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtTypeReference<T> createCtTypeReference(java.lang.Class<?> originalClass) {
        return Code().createCtTypeReference(originalClass);
    }

    @java.lang.Override
    public java.util.List<spoon.reflect.code.CtExpression<?>> createVariableReads(java.util.List<? extends spoon.reflect.declaration.CtVariable<?>> variables) {
        return Code().createVariableReads(variables);
    }

    @java.lang.Override
    public spoon.reflect.code.CtCatch createCtCatch(java.lang.String nameCatch, java.lang.Class<? extends java.lang.Throwable> exception, spoon.reflect.code.CtBlock<?> ctBlock) {
        return Code().createCtCatch(nameCatch, exception, ctBlock);
    }

    @java.lang.Override
    public spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement(java.lang.String statement) {
        return Code().createCodeSnippetStatement(statement);
    }

    @java.lang.Override
    public spoon.reflect.code.CtComment createComment(java.lang.String content, spoon.reflect.code.CtComment.CommentType type) {
        return Code().createComment(content, type);
    }

    @java.lang.Override
    public spoon.reflect.code.CtComment createInlineComment(java.lang.String content) {
        return Code().createInlineComment(content);
    }

    @java.lang.Override
    public spoon.reflect.code.CtJavaDocTag createJavaDocTag(java.lang.String content, spoon.reflect.code.CtJavaDocTag.TagType type) {
        return Code().createJavaDocTag(content, type);
    }

    @java.lang.Override
    public spoon.reflect.code.CtThrow createCtThrow(java.lang.String thrownExp) {
        return Code().createCtThrow(thrownExp);
    }

    @java.lang.Override
    public spoon.reflect.reference.CtPackageReference createCtPackageReference(java.lang.Package originalPackage) {
        return Code().createCtPackageReference(originalPackage);
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtConstructor<T> createDefault(spoon.reflect.declaration.CtClass<T> target) {
        return Constructor().createDefault(target);
    }

    @java.lang.Override
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> createAnnotation() {
        return Core().createAnnotation();
    }

    @java.lang.Override
    public <R> spoon.reflect.code.CtBlock<R> createBlock() {
        return Core().createBlock();
    }

    @java.lang.Override
    public <R> spoon.reflect.code.CtReturn<R> createReturn() {
        return Core().createReturn();
    }

    @java.lang.Override
    public <R> spoon.reflect.code.CtStatementList createStatementList() {
        return Core().createStatementList();
    }

    @java.lang.Override
    public <S> spoon.reflect.code.CtCase<S> createCase() {
        return Core().createCase();
    }

    @java.lang.Override
    public <S> spoon.reflect.code.CtSwitch<S> createSwitch() {
        return Core().createSwitch();
    }

    @java.lang.Override
    public <T extends java.lang.Enum<?>> spoon.reflect.declaration.CtEnum<T> createEnum() {
        return Core().createEnum();
    }

    @java.lang.Override
    public <T extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotationType<T> createAnnotationType() {
        return Core().createAnnotationType();
    }

    @java.lang.Override
    public <T, A extends T> spoon.reflect.code.CtAssignment<T, A> createAssignment() {
        return Core().createAssignment();
    }

    @java.lang.Override
    public <T, A extends T> spoon.reflect.code.CtOperatorAssignment<T, A> createOperatorAssignment() {
        return Core().createOperatorAssignment();
    }

    @java.lang.Override
    public <T, E extends spoon.reflect.code.CtExpression<?>> spoon.reflect.code.CtExecutableReferenceExpression<T, E> createExecutableReferenceExpression() {
        return Core().createExecutableReferenceExpression();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtAnnotationFieldAccess<T> createAnnotationFieldAccess() {
        return Core().createAnnotationFieldAccess();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtArrayRead<T> createArrayRead() {
        return Core().createArrayRead();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtArrayWrite<T> createArrayWrite() {
        return Core().createArrayWrite();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtAssert<T> createAssert() {
        return Core().createAssert();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtBinaryOperator<T> createBinaryOperator() {
        return Core().createBinaryOperator();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtCatchVariable<T> createCatchVariable() {
        return Core().createCatchVariable();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtCodeSnippetExpression<T> createCodeSnippetExpression() {
        return Core().createCodeSnippetExpression();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtConditional<T> createConditional() {
        return Core().createConditional();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtConstructorCall<T> createConstructorCall() {
        return Core().createConstructorCall();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtFieldRead<T> createFieldRead() {
        return Core().createFieldRead();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtFieldWrite<T> createFieldWrite() {
        return Core().createFieldWrite();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtInvocation<T> createInvocation() {
        return Core().createInvocation();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtLambda<T> createLambda() {
        return Core().createLambda();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtLiteral<T> createLiteral() {
        return Core().createLiteral();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtLocalVariable<T> createLocalVariable() {
        return Core().createLocalVariable();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtNewArray<T> createNewArray() {
        return Core().createNewArray();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtNewClass<T> createNewClass() {
        return Core().createNewClass();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtSuperAccess<T> createSuperAccess() {
        return Core().createSuperAccess();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtThisAccess<T> createThisAccess() {
        return Core().createThisAccess();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtTypeAccess<T> createTypeAccess() {
        return Core().createTypeAccess();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtUnaryOperator<T> createUnaryOperator() {
        return Core().createUnaryOperator();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtVariableRead<T> createVariableRead() {
        return Core().createVariableRead();
    }

    @java.lang.Override
    public <T> spoon.reflect.code.CtVariableWrite<T> createVariableWrite() {
        return Core().createVariableWrite();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtAnnotationMethod<T> createAnnotationMethod() {
        return Core().createAnnotationMethod();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtClass<T> createClass() {
        return Core().createClass();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtConstructor<T> createConstructor() {
        return Core().createConstructor();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtConstructor<T> createInvisibleArrayConstructor() {
        return Core().createInvisibleArrayConstructor();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtEnumValue<T> createEnumValue() {
        return Core().createEnumValue();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtField<T> createField() {
        return Core().createField();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtInterface<T> createInterface() {
        return Core().createInterface();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtMethod<T> createMethod() {
        return Core().createMethod();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtParameter<T> createParameter() {
        return Core().createParameter();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtArrayTypeReference<T> createArrayTypeReference() {
        return Core().createArrayTypeReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtCatchVariableReference<T> createCatchVariableReference() {
        return Core().createCatchVariableReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtExecutableReference<T> createExecutableReference() {
        return Core().createExecutableReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtFieldReference<T> createFieldReference() {
        return Core().createFieldReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtIntersectionTypeReference<T> createIntersectionTypeReference() {
        return Core().createIntersectionTypeReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtLocalVariableReference<T> createLocalVariableReference() {
        return Core().createLocalVariableReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference() {
        return Core().createParameterReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtTypeReference<T> createTypeReference() {
        return Core().createTypeReference();
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtUnboundVariableReference<T> createUnboundVariableReference() {
        return Core().createUnboundVariableReference();
    }

    @java.lang.Override
    public spoon.reflect.code.CtBreak createBreak() {
        return Core().createBreak();
    }

    @java.lang.Override
    public spoon.reflect.code.CtCatch createCatch() {
        return Core().createCatch();
    }

    @java.lang.Override
    public spoon.reflect.code.CtCodeSnippetStatement createCodeSnippetStatement() {
        return Core().createCodeSnippetStatement();
    }

    @java.lang.Override
    public spoon.reflect.code.CtComment createComment() {
        return Core().createComment();
    }

    @java.lang.Override
    public spoon.reflect.code.CtContinue createContinue() {
        return Core().createContinue();
    }

    @java.lang.Override
    public spoon.reflect.code.CtDo createDo() {
        return Core().createDo();
    }

    @java.lang.Override
    public spoon.reflect.code.CtFor createFor() {
        return Core().createFor();
    }

    @java.lang.Override
    public spoon.reflect.code.CtForEach createForEach() {
        return Core().createForEach();
    }

    @java.lang.Override
    public spoon.reflect.code.CtIf createIf() {
        return Core().createIf();
    }

    @java.lang.Override
    public spoon.reflect.code.CtSynchronized createSynchronized() {
        return Core().createSynchronized();
    }

    @java.lang.Override
    public spoon.reflect.code.CtThrow createThrow() {
        return Core().createThrow();
    }

    @java.lang.Override
    public spoon.reflect.code.CtTry createTry() {
        return Core().createTry();
    }

    @java.lang.Override
    public spoon.reflect.code.CtTryWithResource createTryWithResource() {
        return Core().createTryWithResource();
    }

    @java.lang.Override
    public spoon.reflect.code.CtWhile createWhile() {
        return Core().createWhile();
    }

    @java.lang.Override
    public spoon.reflect.cu.CompilationUnit createCompilationUnit() {
        return Core().createCompilationUnit();
    }

    @java.lang.Override
    public spoon.reflect.cu.SourcePosition createSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int[] lineSeparatorPositions) {
        return Core().createSourcePosition(compilationUnit, startSource, end, lineSeparatorPositions);
    }

    @java.lang.Override
    public spoon.reflect.cu.position.BodyHolderSourcePosition createBodyHolderSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int bodyStart, int bodyEnd, int[] lineSeparatorPositions) {
        return Core().createBodyHolderSourcePosition(compilationUnit, startSource, end, modifierStart, modifierEnd, declarationStart, declarationEnd, bodyStart, bodyEnd, lineSeparatorPositions);
    }

    @java.lang.Override
    public spoon.reflect.cu.position.DeclarationSourcePosition createDeclarationSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit, int startSource, int end, int modifierStart, int modifierEnd, int declarationStart, int declarationEnd, int[] lineSeparatorPositions) {
        return Core().createDeclarationSourcePosition(compilationUnit, startSource, end, modifierStart, modifierEnd, declarationStart, declarationEnd, lineSeparatorPositions);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtAnonymousExecutable createAnonymousExecutable() {
        return Core().createAnonymousExecutable();
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtPackage createPackage() {
        return Core().createPackage();
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtTypeParameter createTypeParameter() {
        return Core().createTypeParameter();
    }

    @java.lang.Override
    public spoon.reflect.reference.CtPackageReference createPackageReference() {
        return Core().createPackageReference();
    }

    @java.lang.Override
    public spoon.reflect.reference.CtTypeParameterReference createTypeParameterReference() {
        return Core().createTypeParameterReference();
    }

    @java.lang.Override
    public spoon.reflect.reference.CtWildcardReference createWildcardReference() {
        return Core().createWildcardReference();
    }

    @java.lang.Override
    public spoon.reflect.eval.PartialEvaluator createPartialEvaluator() {
        return Eval().createPartialEvaluator();
    }

    @java.lang.Override
    public <T> spoon.reflect.declaration.CtParameter<T> createParameter(spoon.reflect.declaration.CtExecutable<?> parent, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name) {
        return Executable().createParameter(parent, type, name);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtParameterReference<T> createParameterReference(spoon.reflect.declaration.CtParameter<T> parameter) {
        return Executable().createParameterReference(parameter);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtAnonymousExecutable createAnonymous(spoon.reflect.declaration.CtClass<?> target, spoon.reflect.code.CtBlock<java.lang.Void> body) {
        return Executable().createAnonymous(target, body);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtArrayTypeReference<T> createArrayReference(java.lang.String qualifiedName) {
        return Type().createArrayReference(qualifiedName);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtArrayTypeReference<T[]> createArrayReference(spoon.reflect.declaration.CtType<T> type) {
        return Type().createArrayReference(type);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtArrayTypeReference<T[]> createArrayReference(spoon.reflect.reference.CtTypeReference<T> reference) {
        return Type().createArrayReference(reference);
    }

    @java.lang.Override
    public <T> spoon.reflect.reference.CtIntersectionTypeReference<T> createIntersectionTypeReferenceWithBounds(java.util.List<spoon.reflect.reference.CtTypeReference<?>> bounds) {
        return Type().createIntersectionTypeReferenceWithBounds(bounds);
    }

    @java.lang.Override
    public spoon.support.visitor.GenericTypeAdapter createTypeAdapter(spoon.reflect.declaration.CtFormalTypeDeclarer formalTypeDeclarer) {
        return Type().createTypeAdapter(formalTypeDeclarer);
    }

    @java.lang.Override
    public java.util.List<spoon.reflect.reference.CtTypeReference<?>> createReferences(java.util.List<java.lang.Class<?>> classes) {
        return Type().createReferences(classes);
    }

    @java.lang.Override
    public spoon.reflect.reference.CtArrayTypeReference<?> createArrayReference(spoon.reflect.reference.CtTypeReference<?> reference, int n) {
        return Type().createArrayReference(reference, n);
    }

    @java.lang.Override
    public spoon.reflect.reference.CtTypeParameterReference createTypeParameterReference(java.lang.String name) {
        return Type().createTypeParameterReference(name);
    }

    @java.lang.Override
    public spoon.reflect.visitor.chain.CtQuery createQuery() {
        return Query().createQuery();
    }

    @java.lang.Override
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object input) {
        return Query().createQuery(input);
    }

    @java.lang.Override
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Object[] input) {
        return Query().createQuery(input);
    }

    @java.lang.Override
    public spoon.reflect.visitor.chain.CtQuery createQuery(java.lang.Iterable<?> input) {
        return Query().createQuery(input);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtAnnotationType createAnnotationType(java.lang.String qualifiedName) {
        return Annotation().create(qualifiedName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtAnnotationType createAnnotationType(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        return Annotation().create(owner, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtClass createClass(java.lang.String qualifiedName) {
        return Class().create(qualifiedName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtClass createClass(spoon.reflect.declaration.CtClass<?> declaringClass, java.lang.String simpleName) {
        return Class().create(declaringClass, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtClass createClass(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        return Class().create(owner, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, spoon.reflect.declaration.CtConstructor<?> source) {
        return Constructor().create(target, source);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, spoon.reflect.declaration.CtMethod<?> source) {
        return Constructor().create(target, source);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes) {
        return Constructor().create(target, modifiers, parameters, thrownTypes);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtConstructor createConstructor(spoon.reflect.declaration.CtClass target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock body) {
        return Constructor().create(target, modifiers, parameters, thrownTypes, body);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtEnum<?> createEnum(java.lang.String qualifiedName) {
        return Enum().create(qualifiedName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtEnum<?> createEnum(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        return Enum().create(owner, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference type, java.lang.String name) {
        return Field().create(target, modifiers, type, name);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference type, java.lang.String name, spoon.reflect.code.CtExpression defaultExpression) {
        return Field().create(target, modifiers, type, name, defaultExpression);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtField createField(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtField source) {
        return Field().create(target, source);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtInterface createInterface(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        return Interface().create(owner, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtInterface createInterface(spoon.reflect.declaration.CtType owner, java.lang.String simpleName) {
        return Interface().create(owner, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtInterface createInterface(java.lang.String qualifiedName) {
        return Interface().create(qualifiedName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtClass<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes, spoon.reflect.code.CtBlock body) {
        return Method().create(target, modifiers, returnType, name, parameters, thrownTypes, body);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtMethod source, boolean redirectReferences) {
        return Method().create(target, source, redirectReferences);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtMethod createMethod(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference returnType, java.lang.String name, java.util.List<spoon.reflect.declaration.CtParameter<?>> parameters, java.util.Set<spoon.reflect.reference.CtTypeReference<? extends java.lang.Throwable>> thrownTypes) {
        return Method().create(target, modifiers, returnType, name, parameters, thrownTypes);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtPackage createPackage(spoon.reflect.declaration.CtPackage parent, java.lang.String simpleName) {
        return Package().create(parent, simpleName);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtElement createElement(java.lang.Class<? extends spoon.reflect.declaration.CtElement> klass) {
        return Core().create(klass);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtImport createImport(spoon.reflect.reference.CtReference reference) {
        return Type().createImport(reference);
    }

    @java.lang.Override
    public spoon.reflect.reference.CtTypeReference createWildcardStaticTypeMemberReference(spoon.reflect.reference.CtTypeReference typeReference) {
        return Type().createWildcardStaticTypeMemberReference(typeReference);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtPackageExport createPackageExport(spoon.reflect.reference.CtPackageReference ctPackageReference) {
        return Module().createPackageExport(ctPackageReference);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtProvidedService createProvidedService(spoon.reflect.reference.CtTypeReference ctTypeReference) {
        return Module().createProvidedService(ctTypeReference);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtModuleRequirement createModuleRequirement(spoon.reflect.reference.CtModuleReference ctModuleReference) {
        return Module().createModuleRequirement(ctModuleReference);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtModule createModule(java.lang.String moduleName) {
        return Module().getOrCreate(moduleName);
    }

    @java.lang.Override
    public spoon.reflect.reference.CtModuleReference createModuleReference(spoon.reflect.declaration.CtModule ctModule) {
        return Module().createReference(ctModule);
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtUsedService createUsedService(spoon.reflect.reference.CtTypeReference typeReference) {
        return Module().createUsedService(typeReference);
    }

    @java.lang.Override
    public spoon.reflect.cu.SourcePosition createPartialSourcePosition(spoon.reflect.cu.CompilationUnit compilationUnit) {
        return Core().createPartialSourcePosition(compilationUnit);
    }
}

