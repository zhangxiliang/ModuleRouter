package com.blur.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.blur.router.annotation.Router;
import com.blur.router.api.MRouter;

@Router(path = "/music")
public class MainActivity extends AppCompatActivity {

    String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
