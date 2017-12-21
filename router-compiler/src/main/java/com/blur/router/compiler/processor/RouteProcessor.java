package com.blur.router.compiler;

import com.blur.router.annotation.Router;
import com.blur.router.annotation.utils.Constant;
import com.blur.router.compiler.utils.RouteJavaFileUtils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import static com.blur.router.annotation.utils.Constant.KEY_MODULE_NAME;


public class RouteProcessor extends AbstractAnnotationProcess{





    public RouteProcessor(ProcessingEnvironment processingEnvironment){

        super(processingEnvironment);
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        printValue("begin process router");
        ArrayList<AutowireRouteClass> list = new ArrayList<>();
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(Router.class);
        for (Element element : routeElements) {
            //printElement(element, Route.class);
            list.add(AutowireRouteClass.createWhenApplyClass(element));
        }

        if (!list.isEmpty()) {
            try {
                ClassName className = ClassName.get(Constant.ROUTE_INIT_CLASS_PACKAGE,
                        Constant.ROUTE_INIT_MODULE_CLASS_PREFIX + moduleName);
                RouteJavaFileUtils.preJavaFileByList(className, list).writeTo(filter);
                printValue("MRoute Generated Java File -->" + className);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return true;
    }




}
