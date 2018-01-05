package com.blur.router.compiler;

import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.BindView;
import com.blur.router.annotation.Components;
import com.blur.router.annotation.Router;
import com.blur.router.compiler.processor.AbstractAnnotationProcess;
import com.blur.router.compiler.processor.AutowiredProcessor;
import com.blur.router.compiler.processor.BindViewProcessor;
import com.blur.router.compiler.processor.ComponentProcessor;
import com.blur.router.compiler.processor.RouteProcessor;
import com.blur.router.compiler.processor.TargetFieldProcessor;
import com.google.auto.service.AutoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import static com.blur.router.annotation.utils.Constant.KEY_MODULE_NAME;

/**
 * Created by zhangxiliang on 2017/12/18.
 */

@AutoService(Processor.class)
@SupportedOptions(KEY_MODULE_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ProcessorFactory extends AbstractProcessor{

    private List<AbstractAnnotationProcess> annotationProcessList =new ArrayList<>();


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> stringSet=new HashSet<>();
        stringSet.add(Router.class.getCanonicalName());
        stringSet.add(Components.class.getCanonicalName());
        stringSet.add(Autowired.class.getCanonicalName());
        stringSet.add(BindView.class.getCanonicalName());


        return stringSet;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        annotationProcessList.add(new RouteProcessor(processingEnvironment));
        annotationProcessList.add(new ComponentProcessor(processingEnvironment));
        annotationProcessList.add(new TargetFieldProcessor(processingEnvironment));


    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for(AbstractAnnotationProcess annotationProcess:annotationProcessList){
            annotationProcess.process(set,roundEnvironment);
        }
        return true;
    }



}
