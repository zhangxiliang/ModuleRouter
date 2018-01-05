package com.blur.business.dance;

import android.content.Context;

import com.blur.router.annotation.IProvider;

/**
 * Created by zhangxiliang on 2017/12/21.
 */

public interface MusicProvider extends IProvider {

    public void sayHello(Context context, String text);
}
