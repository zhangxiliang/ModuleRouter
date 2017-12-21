package com.blur.router.compiler;

import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.Router;
import com.blur.router.compiler.utils.FieldTypeKind;
import com.blur.router.compiler.utils.ProcessorUtils;
import com.blur.router.compiler.utils.TargetTypeKind;
import com.squareup.javapoet.TypeName;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhangxiliang on 2017/12/19.
 */

public class AutoWireProcessor extends AbstractAnnotationProcess {


    public AutoWireProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        HashMap<String,AutowireRouteClass> autowireRouteClassHashMap=new LinkedHashMap<>();

        //获取所有的AutoWired标签元素
        Set<? extends Element> elementSet=roundEnvironment.getElementsAnnotatedWith(Autowired.class);
        if(elementSet!=null){
            for(Element element:elementSet){
                AutowireRouteClass autowireRouteClass=autowireRouteClassHashMap.get(element.getSimpleName());
                if(autowireRouteClass==null){
                    autowireRouteClass=AutowireRouteClass.createWhenApplyField(element);
                    autowireRouteClassHashMap.put(element.getEnclosingElement().getSimpleName().toString(),autowireRouteClass);
                }

                String annotationValue = element.getAnnotation(Autowired.class).name();
                String fieldName = element.getSimpleName().toString();
                TypeName fieldType = TypeName.get(element.asType());
                FieldTypeKind kind = ProcessorUtils.getElementType(element, types, elements);

                TargetTypeKind targetTypeKind = ProcessorUtils.getTargetTypeKind(elements, types, (TypeElement) element.getEnclosingElement());
                String assignStatement = ProcessorUtils.getAssignStatementByTypeKind(kind, targetTypeKind);
                AutowireField autoFiled = AutowireField.create(fieldName, fieldType, annotationValue, assignStatement, kind);
                autowireRouteClass.addAnnotationField(autoFiled);

                printValue("AutoWireProcessor Autowired--->"+autoFiled.toString());


            }
        }


        for (Map.Entry<String, AutowireRouteClass> entry : autowireRouteClassHashMap.entrySet()) {
            try {
                AutowireRouteClass autowireClass = entry.getValue();
                autowireClass.preJavaFile().writeTo(filter);
                ProcessorUtils.printMessage(messager, null, "MRoute Generated Java File -->" + autowireClass.getClassName()+",size="+autowireRouteClassHashMap.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
