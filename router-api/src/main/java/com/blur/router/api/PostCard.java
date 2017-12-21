package com.blur.router.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by zhangxiliang on 2017/12/8.
 */

public class PostCard {


    private String url;
    Intent intent;

    public PostCard(String url) {
        this.url = url;
        intent=new Intent();
    }





    public PostCard putBoolean(String key, boolean value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, boolean[] value) {
        intent.putExtra(key,value);
        return this;
    }


    public PostCard putExtra(String key, byte value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, byte[] value) {
        intent.putExtra(key,value);
        return this;
    }


    public PostCard putExtra(String key, char value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, char[] value) {
        intent.putExtra(key,value);
        return this;
    }


    public PostCard putExtra(String key, short value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, short[] values) {
        intent.putExtra(key,values);

        return this;
    }


    public PostCard putExtra(String key, float value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, float[] value) {
        intent.putExtra(key,value);
        return this;
    }


    public PostCard putExtra(String key, int value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, int[] value) {

        intent.putExtra(key,value);
        return this;
    }



    public PostCard putExtra(String key, double value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, double[] value) {

        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, long value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putExtra(String key, long[] value) {
        intent.putExtra(key,value);
        return this;
    }

    public PostCard putString(String key, String value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, String[] value) {
        intent.putExtra(key,value);

        return this;
    }



    public PostCard putExtra(String key, Parcelable value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, Parcelable[] value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, ArrayList<? extends Parcelable> value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtra(String key, Serializable value) {
        intent.putExtra(key,value);

        return this;
    }

    public PostCard putExtras(String key,Bundle extras) {
        intent.putExtra(key,extras);
        return this;
    }

    public void navigation(Context context){
        Class clazz=RouteManager.getInstance().getRoute(url);
        if(clazz!=null){
            intent.setClass(context.getApplicationContext(),clazz);
            context.startActivity(intent);
        }

    }



}
