package com.blur.router.api;

import android.app.Application;
import android.content.Context;

import com.blur.router.annotation.utils.Constant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by zhangxiliang on 2017/12/7.
 */

public final class MRouter {


    private volatile static MRouter instance = null;


    public  static void init(Application application) {

        try {
            Class clazz = Class.forName(Constant.ROUTE_INIT_CLASS_PACKAGE + ".RouterInit");
            Method method = clazz.getDeclaredMethod(Constant.ROUTE_INIT_CLASS_METHOD);
            method.invoke(clazz.newInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static MRouter getInstance() {
        if (instance == null) {
            synchronized (MRouter.class) {
                if (instance == null) {
                    instance = new MRouter();
                }
            }
        }
        return instance;
    }


    public PostCard build(String path) {
        PostCard postCard = new PostCard(path);
        return postCard;
    }


    public void inject(Object target){

        String objName=target.getClass().getName();
        String autoCreateClass=objName+Constant.ROUTE_INIT_MODULE_CLASS_AUTOWIRE_SUFFIX;

        Class clazz = null;
        try {
            clazz = Class.forName(autoCreateClass);
            clazz.getDeclaredConstructor(target.getClass()).newInstance(target);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
