package com.blur.router.api.core;

import com.blur.library.Router;

import java.lang.annotation.Annotation;

/**
 * Created by zhangxiliang on 2017/12/8.
 */

public class BaseAnnotationProcess implements AnnotationProcess {


    @Override
    public InvokeElement getInvokeElement(Class clazz) {

        Annotation annotation=clazz.getAnnotation(Router.class);

        if(annotation!=null){

            Router router=(Router)annotation ;
            String path=router.path();
            return new InvokeElement(path,false);

        }

        return null;
    }
}
