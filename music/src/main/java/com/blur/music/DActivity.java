package com.blur.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.blur.router.annotation.Components;
import com.blur.router.annotation.Router;

/**
 * Created by zhangxiliang on 2017/12/19.
 */
@Router(path = "/d/activity/")
public class DActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
