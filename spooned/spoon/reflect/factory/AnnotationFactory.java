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
 * The {@link CtAnnotationType} sub-factory.
 */
public class AnnotationFactory extends spoon.reflect.factory.TypeFactory {
    /**
     * Creates an annotation sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public AnnotationFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates an annotation type.
     *
     * @param owner
     * 		the package of the annotation type
     * @param simpleName
     * 		the name of annotation
     */
    public <T extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotationType<?> create(spoon.reflect.declaration.CtPackage owner, java.lang.String simpleName) {
        spoon.reflect.declaration.CtAnnotationType<T> t = factory.Core().createAnnotationType();
        t.setSimpleName(simpleName);
        owner.addType(t);
        return t;
    }

    /**
     * Creates an annotation type.
     *
     * @param qualifiedName
     * 		the fully qualified name of the annotation type.
     */
    public spoon.reflect.declaration.CtAnnotationType<?> create(java.lang.String qualifiedName) {
        return create(factory.Package().getOrCreate(getPackageName(qualifiedName)), getSimpleName(qualifiedName));
    }

    /**
     * Gets a annotation type from its name.
     */
    public <T extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtType<T> getAnnotationType(java.lang.String qualifiedName) {
        return get(qualifiedName);
    }

    /**
     * Creates/updates an element's annotation value.
     *
     * @param element
     * 		the program element to annotate
     * @param annotationType
     * 		the annotation type
     * @param annotationElementName
     * 		the annotation element name
     * @param value
     * 		the value of the annotation element
     * @return the created/updated annotation
     */
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> annotate(spoon.reflect.declaration.CtElement element, java.lang.Class<A> annotationType, java.lang.String annotationElementName, java.lang.Object value) {
        return annotate(element, factory.Type().createReference(annotationType), annotationElementName, value);
    }

    /**
     * Creates/updates an element's annotation value.
     *
     * @param element
     * 		the program element to annotate
     * @param annotationType
     * 		the annotation type
     * @param annotationElementName
     * 		the annotation element name
     * @param value
     * 		the value of the annotation element
     * @return the created/updated annotation
     */
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> annotate(spoon.reflect.declaration.CtElement element, spoon.reflect.reference.CtTypeReference<A> annotationType, java.lang.String annotationElementName, java.lang.Object value) {
        final spoon.reflect.declaration.CtAnnotation<A> annotation = annotate(element, annotationType);
        boolean isArray;
        // try with CT reflection
        spoon.reflect.declaration.CtAnnotationType<A> ctAnnotationType = ((spoon.reflect.declaration.CtAnnotationType<A>) (annotation.getAnnotationType().getDeclaration()));
        boolean hasAlreadyValue = annotation.getValues().containsKey(annotationElementName);
        if (ctAnnotationType != null) {
            spoon.reflect.declaration.CtMethod<?> e = ctAnnotationType.getMethod(annotationElementName);
            isArray = (e.getType()) instanceof spoon.reflect.reference.CtArrayTypeReference;
        }else {
            java.lang.reflect.Method m;
            try {
                m = annotation.getAnnotationType().getActualClass().getMethod(annotationElementName, new java.lang.Class[0]);
            } catch (java.lang.Exception ex) {
                annotation.addValue(annotationElementName, value);
                return annotation;
            }
            isArray = m.getReturnType().isArray();
        }
        if (isArray || (!hasAlreadyValue)) {
            annotation.addValue(annotationElementName, value);
        }else {
            throw new spoon.SpoonException("cannot assign an array to a non-array annotation element");
        }
        return annotation;
    }

    /**
     * Adds an annotation to an element.
     *
     * @param element
     * 		the program element to annotate
     * @param annotationType
     * 		the annotation type
     * @return the concerned annotation
     */
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> annotate(spoon.reflect.declaration.CtElement element, java.lang.Class<A> annotationType) {
        return annotate(element, factory.Type().createReference(annotationType));
    }

    /**
     * Adds an annotation to an element.
     *
     * @param element
     * 		the program element to annotate
     * @param annotationType
     * 		the annotation type
     * @return the concerned annotation
     */
    public <A extends java.lang.annotation.Annotation> spoon.reflect.declaration.CtAnnotation<A> annotate(spoon.reflect.declaration.CtElement element, spoon.reflect.reference.CtTypeReference<A> annotationType) {
        spoon.reflect.declaration.CtAnnotationType<A> ctAnnotationType = ((spoon.reflect.declaration.CtAnnotationType<A>) (annotationType.getDeclaration()));
        boolean isRepeatable = false;
        if (ctAnnotationType != null) {
            isRepeatable = (ctAnnotationType.getAnnotation(factory.Type().createReference(java.lang.annotation.Repeatable.class))) != null;
        }
        spoon.reflect.declaration.CtAnnotation<A> annotation = element.getAnnotation(annotationType);
        if ((annotation == null) || isRepeatable) {
            annotation = factory.Core().createAnnotation();
            annotation.setAnnotationType(factory.Core().clone(annotationType));
            element.addAnnotation(annotation);
        }
        return annotation;
    }
}

