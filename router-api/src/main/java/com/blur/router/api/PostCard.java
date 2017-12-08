package com.blur.router.api;

import android.content.Intent;

/**
 * Created by zhangxiliang on 2017/12/8.
 */

public class PostCard {




    static class Builder{
        Intent intent;


        public  Builder(){
            intent=new Intent();
        }

        public Builder putExtra(String name,String value){
            intent.putExtra(name,value);
            return this;
        }


        public Builder putExtra(String name,boolean value){
            intent.putExtra(name,value);
            return this;
        }





    }

}
