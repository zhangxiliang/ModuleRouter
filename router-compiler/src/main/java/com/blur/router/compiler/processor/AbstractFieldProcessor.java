package com.blur.router.compiler.processor;

import com.blur.router.annotation.Autowired;

import com.blur.router.compiler.AutowireField;
import com.blur.router.compiler.AutowireRouteClass;
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

public abstract class AbstractFieldProcessor extends AbstractAnnotationProcess {



    public AbstractFieldProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        printValue("AnnotationFieldProcessor Process--->");
        HashMap<String,AutowireRouteClass> autowireRouteClassHashMap=new LinkedHashMap<>();

        //处理AutoWired BindView 注解
        process(set, roundEnvironment,autowireRouteClassHashMap);

        //生成注入文件
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


    public abstract boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment, HashMap<String, AutowireRouteClass> autowireRouteClassHashMap) ;


    }
