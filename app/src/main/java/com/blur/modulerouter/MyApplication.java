package com.blur.modulerouter;

import android.app.Application;

import com.blur.router.RouterInit;
import com.blur.router.annotation.Components;
import com.blur.router.api.MRouter;

/**
 * Created by zhangxiliang on 2017/12/18.
 */
@Components(value={"app","music"})
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MRouter.init(this);
    }
}
