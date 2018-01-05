package com.blur.router.compiler.processor;

import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.BindView;
import com.blur.router.compiler.AutowireField;
import com.blur.router.compiler.AutowireRouteClass;
import com.blur.router.compiler.BindViewField;
import com.blur.router.compiler.utils.FieldTypeKind;
import com.blur.router.compiler.utils.ProcessorUtils;
import com.blur.router.compiler.utils.TargetTypeKind;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhangxiliang on 2018/1/3.
 */

public class BindViewProcessor extends AbstractFieldProcessor {


    public BindViewProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
    }



    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment, HashMap<String, AutowireRouteClass> autowireRouteClassHashMap) {
        printValue("BindViewProcessor process --->");

        Set<? extends Element> elementSet=roundEnvironment.getElementsAnnotatedWith(BindView.class);

        for(Element element:elementSet){
            AutowireRouteClass autowireRouteClass=autowireRouteClassHashMap.get(element.getEnclosingElement().getSimpleName().toString());
            if(autowireRouteClass==null){
                autowireRouteClass=AutowireRouteClass.createWhenApplyField(element);
                autowireRouteClassHashMap.put(element.getEnclosingElement().getSimpleName().toString(),autowireRouteClass);
            }

            int annotationValue = element.getAnnotation(BindView.class).value();
            String fieldName = element.getSimpleName().toString();
            TypeName fieldType = TypeName.get(element.asType());
            FieldTypeKind kind = ProcessorUtils.getElementType(element, types, elements);

            //TargetTypeKind targetTypeKind = ProcessorUtils.getTargetTypeKind(elements, types, (TypeElement) element.getEnclosingElement());
            String assignStatement ="findViewById($L)";
            BindViewField bindViewField = new BindViewField(fieldName, fieldType, annotationValue, assignStatement, kind);

            autowireRouteClass.addAnnotationField(bindViewField);

        }
        return false;
    }



}
