package com.blur.router.compiler.processor;

import com.blur.router.compiler.AutowireRouteClass;

import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhangxiliang on 2018/1/5.
 */

public class TargetFieldProcessor extends AbstractFieldProcessor {
    AutowiredProcessor autowiredProcessor;
    BindViewProcessor bindViewProcessor;

    public TargetFieldProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
        autowiredProcessor=new AutowiredProcessor(processingEnvironment);
        bindViewProcessor=new BindViewProcessor(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment, HashMap<String, AutowireRouteClass> autowireRouteClassHashMap) {
        autowiredProcessor.process(set, roundEnvironment, autowireRouteClassHashMap);
        bindViewProcessor.process(set, roundEnvironment, autowireRouteClassHashMap);
        return false;
    }
}
