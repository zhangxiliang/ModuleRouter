package com.blur.router.api;

import android.support.v4.util.LruCache;

/**
 * Created by zhangxiliang on 2017/12/8.
 */

public class LruClassCache {

    private LruCache<String,Class> classLruCache;



    public static LruClassCache getInstance(){
        return IntanceHolder.instance;
    }



    private LruClassCache(){
        classLruCache=new LruCache<String,Class>(80);

    }

    public void register(Object thiz){


        if(thiz!=null){
            String className=thiz.getClass().getName();
            classLruCache.put(className,thiz.getClass());
        }
    }



    static  class IntanceHolder{

        private final static LruClassCache instance=new LruClassCache();
    }

}
