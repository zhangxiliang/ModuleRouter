package com.blur.router.compiler;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import static com.blur.router.annotation.utils.Constant.KEY_MODULE_NAME;

/**
 * Created by zhangxiliang on 2017/12/19.
 */

public abstract class AbstractAnnotationProcess {
    protected Filer filter;
    protected Messager messager;
    protected String moduleName;
    protected Types types;
    protected Elements elements;


    public AbstractAnnotationProcess(ProcessingEnvironment processingEnvironment){
        filter = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        types=processingEnvironment.getTypeUtils();
        elements = processingEnvironment.getElementUtils();      // Get class meta.

        printValue("MRoute init-->" + this);
        processingEnvironment.getOptions();
        Map<String, String> options = processingEnvironment.getOptions();
        if (options != null && !options.isEmpty()) {
            moduleName = options.get(KEY_MODULE_NAME);
            if (moduleName != null && moduleName.length() > 0) {
                moduleName = moduleName.replaceAll("[^0-9a-zA-Z_]+", "");
            }
            printValue("moduleName-->" + moduleName);
        }

    }




    public abstract boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment);



    protected void printValue(String obj) {
        messager.printMessage(Diagnostic.Kind.NOTE, obj);
    }



}
