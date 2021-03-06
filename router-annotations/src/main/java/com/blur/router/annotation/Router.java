package com.blur.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangxiliang on 2017/12/7.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Router {
    String path();
}
