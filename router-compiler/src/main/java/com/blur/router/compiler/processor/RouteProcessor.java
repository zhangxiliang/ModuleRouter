package com.blur.router.compiler.processor;

import com.blur.router.annotation.Router;
import com.blur.router.annotation.utils.Constant;
import com.blur.router.compiler.AutowireRouteClass;
import com.blur.router.compiler.utils.RouteJavaFileUtils;
import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


public class RouteProcessor extends AbstractAnnotationProcess {





    public RouteProcessor(ProcessingEnvironment processingEnvironment){

        super(processingEnvironment);
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        ArrayList<AutowireRouteClass> list = new ArrayList<>();
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(Router.class);
        for (Element element : routeElements) {
            //printElement(element, Route.class);
            printValue("begin process router 2"+element.asType().toString());
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
