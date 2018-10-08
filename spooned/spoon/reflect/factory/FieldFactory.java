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
 * The {@link CtField} sub-factory.
 */
public class FieldFactory extends spoon.reflect.factory.SubFactory {
    /**
     * Creates a new field sub-factory.
     *
     * @param factory
     * 		the parent factory
     */
    public FieldFactory(spoon.reflect.factory.Factory factory) {
        super(factory);
    }

    /**
     * Creates a field.
     *
     * @param target
     * 		the target type to which the field is added
     * @param modifiers
     * 		the modifiers
     * @param type
     * 		the field's type
     * @param name
     * 		the field's name
     */
    public <T> spoon.reflect.declaration.CtField<T> create(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name) {
        spoon.reflect.declaration.CtField<T> field = factory.Core().createField();
        field.setModifiers(modifiers);
        field.setType(type);
        field.setSimpleName(name);
        if (target != null) {
            target.addField(field);
        }
        return field;
    }

    /**
     * Creates a field.
     *
     * @param target
     * 		the target type to which the field is added
     * @param modifiers
     * 		the modifiers
     * @param type
     * 		the field's type
     * @param name
     * 		the field's name
     * @param defaultExpression
     * 		the initializing expression
     */
    public <T> spoon.reflect.declaration.CtField<T> create(spoon.reflect.declaration.CtType<?> target, java.util.Set<spoon.reflect.declaration.ModifierKind> modifiers, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String name, spoon.reflect.code.CtExpression<T> defaultExpression) {
        spoon.reflect.declaration.CtField<T> field = create(target, modifiers, type, name);
        field.setDefaultExpression(defaultExpression);
        return field;
    }

    /**
     * Creates a field by copying an existing field.
     *
     * @param <T>
     * 		the type of the field
     * @param target
     * 		the target type where the new field has to be inserted to
     * @param source
     * 		the source field to be copied
     * @return the newly created field
     */
    public <T> spoon.reflect.declaration.CtField<T> create(spoon.reflect.declaration.CtType<?> target, spoon.reflect.declaration.CtField<T> source) {
        spoon.reflect.declaration.CtField<T> newField = source.clone();
        if (target != null) {
            target.addField(newField);
        }
        return newField;
    }

    /**
     * Creates a field reference from an existing field.
     */
    public <T> spoon.reflect.reference.CtFieldReference<T> createReference(spoon.reflect.declaration.CtField<T> field) {
        final spoon.reflect.reference.CtFieldReference<T> reference = createReference(factory.Type().createReference(field.getDeclaringType()), field.getType().clone(), field.getSimpleName());
        reference.setFinal(field.hasModifier(spoon.reflect.declaration.ModifierKind.FINAL));
        reference.setStatic(field.hasModifier(spoon.reflect.declaration.ModifierKind.STATIC));
        return reference;
    }

    /**
     * Creates a field reference.
     */
    public <T> spoon.reflect.reference.CtFieldReference<T> createReference(spoon.reflect.reference.CtTypeReference<?> declaringType, spoon.reflect.reference.CtTypeReference<T> type, java.lang.String fieldName) {
        spoon.reflect.reference.CtFieldReference<T> fieldRef = factory.Core().createFieldReference();
        fieldRef.setSimpleName(fieldName);
        fieldRef.setDeclaringType(declaringType);
        fieldRef.setType(type);
        return fieldRef;
    }

    /**
     * Creates a field reference from a <code>java.lang.reflect</code> field.
     */
    @java.lang.SuppressWarnings("unchecked")
    public <T> spoon.reflect.reference.CtFieldReference<T> createReference(java.lang.reflect.Field field) {
        spoon.reflect.reference.CtFieldReference<T> fieldRef = factory.Core().createFieldReference();
        fieldRef.setSimpleName(field.getName());
        fieldRef.setDeclaringType(factory.Type().createReference(field.getDeclaringClass()));
        spoon.reflect.reference.CtTypeReference<T> t = factory.Type().createReference(((java.lang.Class<T>) (field.getType())));
        fieldRef.setType(t);
        return fieldRef;
    }

    /**
     * Creates a field reference from its signature, as defined by the field
     * reference's toString.
     */
    public <T> spoon.reflect.reference.CtFieldReference<T> createReference(java.lang.String signature) {
        spoon.reflect.reference.CtFieldReference<T> fieldRef = factory.Core().createFieldReference();
        java.lang.String type = signature.substring(0, signature.indexOf(' '));
        java.lang.String declaringType = signature.substring(((signature.indexOf(' ')) + 1), signature.indexOf(spoon.reflect.declaration.CtField.FIELD_SEPARATOR));
        java.lang.String fieldName = signature.substring(((signature.indexOf(spoon.reflect.declaration.CtField.FIELD_SEPARATOR)) + 1));
        fieldRef.setSimpleName(fieldName);
        fieldRef.setDeclaringType(factory.Type().createReference(declaringType));
        spoon.reflect.reference.CtTypeReference<T> typeRef = factory.Type().createReference(type);
        fieldRef.setType(typeRef);
        return fieldRef;
    }
}

