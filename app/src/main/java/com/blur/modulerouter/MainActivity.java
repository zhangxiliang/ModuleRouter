package com.blur.modulerouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blur.business.dance.MusicProvider;
import com.blur.router.annotation.Autowired;
import com.blur.router.annotation.Router;
import com.blur.router.api.MRouter;

@Router(path = "/main/activity/")
public class MainActivity extends AppCompatActivity {


    @Autowired(name = "/music/musicprovider")
    MusicProvider musicProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MRouter.getInstance().inject(this);
        musicProvider.sayHello("woshinini");
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MRouter.getInstance().build("/b/activity").putExtra("age","120").navigation(MainActivity.this);


            }
        });


    }



}
