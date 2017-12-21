package com.blur.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.Provider;
import com.blur.router.annotation.Router;
import com.blur.router.api.MRouter;

/**
 * Created by zhangxiliang on 2017/12/19.
 */

@Router(path="/b/activity")
public class BActivity extends FragmentActivity {

    @Autowired(name="age")
    String age;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MRouter.getInstance().inject(this);
        setContentView(R.layout.b_activity);
        Toast.makeText(this,age,Toast.LENGTH_SHORT).show();
    }
}
