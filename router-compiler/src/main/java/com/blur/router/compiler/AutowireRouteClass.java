package com.blur.router.compiler;


import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.BindView;
import com.blur.router.annotation.Router;
import com.blur.router.annotation.utils.Constant;
import com.blur.router.compiler.utils.FieldTypeKind;
import com.blur.router.compiler.utils.ProcessorUtils;
import com.blur.router.compiler.utils.RouteJavaFileUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Description：
 * <br/>
 * Created by kumu on 2017/7/25.
 */

public class AutowireRouteClass {


    private String targetTypeName;
    private String value;

    private TypeName typeName;
    private ClassName className;
    private List<AutowireField> autowireFields;
    private List<BindViewField> bindViewFields;


   public static AutowireRouteClass createWhenApplyClass(Element element) {
        AutowireRouteClass bindClass = new AutowireRouteClass();
        bindClass.targetTypeName = element.asType().toString();
        bindClass.value = element.getAnnotation(Router.class).path();
        return bindClass;
    }

   public static AutowireRouteClass createWhenApplyField(Element element) {
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        AutowireRouteClass autowireRouteClass = new AutowireRouteClass();
        String packageName = enclosingElement.getQualifiedName().toString();
        packageName = packageName.substring(0, packageName.lastIndexOf("."));
        String className = enclosingElement.getSimpleName().toString();
        autowireRouteClass.typeName = ProcessorUtils.getTypeName(enclosingElement);
        //autowireRouteClass.value = element.getAnnotation(Autowired.class).name();
        autowireRouteClass.targetTypeName = element.asType().toString();
        autowireRouteClass.className = ClassName.get(packageName, className +
                Constant.ROUTE_INIT_MODULE_CLASS_AUTOWIRE_SUFFIX);
        autowireRouteClass.autowireFields = new ArrayList<>();
       autowireRouteClass.bindViewFields = new ArrayList<>();
       return autowireRouteClass;
    }


    public void addAnnotationField(AutowireField field) {
        autowireFields.add(field);
    }


    public void addAnnotationField(BindViewField field) {
        bindViewFields.add(field);
    }


    public String getTargetTypeName() {
        return targetTypeName;
    }

    public String getValue() {
        return value;
    }

    public ClassName getClassName() {
        return className;
    }

    public JavaFile preJavaFile() {
        return JavaFile.builder(className.packageName(), createTypeSpec())
                .addFileComment("Generated code from " + Constant.LIB_NAME + ". Do not modify!!!")
                .build();
    }

    private TypeSpec createTypeSpec() {
        TypeSpec.Builder result = TypeSpec.classBuilder(className.simpleName())
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                // .addSuperinterface(IAutowired.class)
                .addMethod(createConstructor(typeName));
        return result.build();
    }

    private MethodSpec createConstructor(TypeName targetType) {
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC);
        constructor.addParameter(targetType, "target", Modifier.FINAL);
        for (AutowireField bindings : autowireFields) {
            addInitStatement(constructor, bindings);
        }
        for(BindViewField bindViewField:bindViewFields){
            addInitStatement(constructor,bindViewField);
        }
        return constructor.build();
    }

    private void  addInitStatement(MethodSpec.Builder result, BindViewField field){
        CodeBlock.Builder builder = CodeBlock.builder();

        builder.add("target.$L = ", field.getFieldName());

        builder.add("($T)target.$L",field.getFieldType(),CodeBlock.of(field.getAssignStatement(), field.getAnnotationValue()));

        result.addStatement("$L", builder.build());


    }


    private void addInitStatement(MethodSpec.Builder result, AutowireField field) {
        CodeBlock.Builder builder = CodeBlock.builder();
        if (field.getTypeKind() == FieldTypeKind.PARCELABLE_ARRAY) {
            builder.add("android.os.Parcelable[] parcelables = target.$L;\n",
                    CodeBlock.of(field.getAssignStatement(), field.getAnnotationValue()))
                    .add("target.$L = java.util.Arrays.copyOf(parcelables,parcelables.length, $T.class)",
                            field.getFieldName(),
                            field.getFieldType());
        } else if (field.getTypeKind() == FieldTypeKind.PROVIDER) {
            builder.add("java.lang.Class clazz = $T.getInstance().getRoute($S);"
                    , RouteJavaFileUtils.ROUTE_MANAGER
                    , field.getAnnotationValue());
            builder.add("\nif(clazz !=null){\n");
            builder.add("try {\n$L" +
                    "        } catch (InstantiationException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        } catch (IllegalAccessException e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }", CodeBlock.of("   target.$L = ($T)clazz.newInstance();\n", field.getFieldName(), field.getFieldType()));


            builder.add("}");
        } else {
            builder.add("target.$L = ", field.getFieldName());
            if (field.getTypeKind() == FieldTypeKind.SERIALIZABLE) {
                builder.add("($T)target.$L",
                        field.getFieldType(),
                        CodeBlock.of(field.getAssignStatement(), field.getAnnotationValue()));
            } else {
                builder.add("target.$L",
                        CodeBlock.of(field.getAssignStatement(), field.getAnnotationValue()));
            }
        }
        result.addStatement("$L", builder.build());

    }

    @Override
    public String toString() {
        return "AutowireRouteClass{" +
                "targetTypeName='" + targetTypeName + '\'' +
                ", value='" + value + '\'' +
                ", typeName=" + typeName +
                ", className=" + className +
                ", fields=" + autowireFields +
                '}';
    }
}