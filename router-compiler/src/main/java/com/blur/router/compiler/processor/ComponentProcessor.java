package com.blur.router.compiler.processor;

import com.blur.router.annotation.Components;
import com.blur.router.annotation.utils.Constant;
import com.blur.router.compiler.utils.RouteJavaFileUtils;
import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhangxiliang on 2017/12/18.
 */

public class ComponentProcessor extends AbstractAnnotationProcess {


    public ComponentProcessor(ProcessingEnvironment processingEnvironment) {
        super(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        List<String> modules = new ArrayList<>();
        Set<? extends Element> ComponentsElements = roundEnvironment.getElementsAnnotatedWith(Components.class);
        for (Element element : ComponentsElements) {
            String[] moduleNames = element.getAnnotation(Components.class).value();
            modules.addAll(Arrays.asList(moduleNames));
        }

        if (!modules.isEmpty()) {
            try {
                ClassName className = ClassName.get(Constant.ROUTE_INIT_CLASS_PACKAGE, Constant.ROUTE_INIT_CLASS);
                RouteJavaFileUtils.preJavaFileByModuleNames(className, modules).writeTo(filter);
                printValue("MRoute Generated Java File -->" + className);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
