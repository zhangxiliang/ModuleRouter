package com.blur.router.api;

import java.util.HashMap;

/**
 * Created by zhangxiliang on 2017/12/19.
 */

public class RouteManager {

    private HashMap<String,Class> classHashMap=new HashMap<>();

    public static RouteManager getInstance(){
        return InstanceHolder.routeManager;
    }


    public void addRoute(String path,Class clazz){
        classHashMap.put(path,clazz);
    }

    public Class getRoute(String path){
        return  classHashMap.get(path);
    }


     static  class  InstanceHolder{

         private static final RouteManager routeManager=new RouteManager();

    }

}
