package com.blur.router.compiler;

import com.blur.router.compiler.utils.FieldTypeKind;
import com.squareup.javapoet.TypeName;

/**
 * Created by zhangxiliang on 2018/1/4.
 */

public class BindViewField {
    private final String fieldName;
    private final TypeName fieldType;
    private final int annotationValue;
    private final String assignStatement;
    private final FieldTypeKind typeKind;

    public BindViewField(String fieldName, TypeName fieldType, int annotationValue, String assignStatement, FieldTypeKind typeKind) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.annotationValue = annotationValue;
        this.assignStatement = assignStatement;
        this.typeKind = typeKind;
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeName getFieldType() {
        return fieldType;
    }

    public int getAnnotationValue() {
        return annotationValue;
    }

    public String getAssignStatement() {
        return assignStatement;
    }

    public FieldTypeKind getTypeKind() {
        return typeKind;
    }
}
