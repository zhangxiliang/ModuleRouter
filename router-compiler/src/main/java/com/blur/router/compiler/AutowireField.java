package com.blur.router.compiler;

import com.blur.router.compiler.utils.FieldTypeKind;
import com.squareup.javapoet.TypeName;

/**
 * 保存字段相关信息
 * Created by kumu
 */

public class AutowireField {

    private final String fieldName;
    private final TypeName fieldType;
    private final String annotationValue;
    private final String assignStatement;
    private final FieldTypeKind typeKind;

    private AutowireField(String name, TypeName type, String value, String assignStatement, FieldTypeKind typeKind) {
        this.fieldName = name;
        this.fieldType = type;
        this.annotationValue = value;
        this.assignStatement = assignStatement;
        this.typeKind = typeKind;
    }

    public static AutowireField create(String name, TypeName type, String value, String assignStatement, FieldTypeKind typeKind) {
        return new AutowireField(name, type, value, assignStatement, typeKind);
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeName getFieldType() {
        return fieldType;
    }

    public String getAnnotationValue() {
        return annotationValue;
    }

    public String getAssignStatement() {
        return assignStatement;
    }

    public FieldTypeKind getTypeKind() {
        return typeKind;
    }

    @Override
    public String toString() {
        return "AutowireField{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                ", annotationValue='" + annotationValue + '\'' +
                ", assignStatement='" + assignStatement + '\'' +
                ", typeKind=" + typeKind +
                '}';
    }
}
