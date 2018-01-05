package com.blur.music;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blur.business.dance.MusicProvider;
import com.blur.router.annotation.IProvider;
import com.blur.router.annotation.Router;

/**
 * Created by zhangxiliang on 2017/12/21.
 */

@Router(path = "/music/musicprovider")
public class MusicProviderImpl  implements MusicProvider{

    @Override
    public void sayHello(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
