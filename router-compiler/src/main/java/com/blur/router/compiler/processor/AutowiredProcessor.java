package com.blur.router.compiler.processor;

import com.blur.router.annotation.Autowired;
import com.blur.router.compiler.AutowireField;
import com.blur.router.compiler.AutowireRouteClass;
import com.blur.router.compiler.processor.AbstractAnnotationProcess;
import com.blur.router.compiler.utils.FieldTypeKind;
import com.blur.router.compiler.utils.ProcessorUtils;
import com.blur.router.compiler.utils.TargetTypeKind;
import com.squareup.javapoet.TypeName;

import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhangxiliang on 2018/1/4.
 */

public class AutowiredProcessor extends AbstractFieldProcessor {


    public AutowiredProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment, HashMap<String, AutowireRouteClass> autowireRouteClassHashMap) {

        printValue("AutowiredProcessor Process--->");

        Set<? extends Element> elementSet=roundEnvironment.getElementsAnnotatedWith(Autowired.class);
        if(elementSet!=null){
            for(Element element:elementSet){
                AutowireRouteClass autowireRouteClass=autowireRouteClassHashMap.get(element.getEnclosingElement().getSimpleName().toString());
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

                printValue("AutoWireProcessor Autowired--->"+autowireRouteClass.toString());

            }
        }
        return false;
    }
}
