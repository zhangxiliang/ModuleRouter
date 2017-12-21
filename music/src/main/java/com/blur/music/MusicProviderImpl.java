package com.blur.music;

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


    public void sayHello(String text){

        Log.e("MusicProvider","hello jack");

    }

}
