package com.example.seungkyu.dreamtodream;

/**
 * Created by junyepoh on 2016. 11. 2..
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.TimerTask;
import java.util.Timer;
import android.os.Handler;
/**
 * Created by junyepoh on 2016. 10. 10..
 */
public class SplashPage extends AppCompatActivity {
    private long splashDelay = 2000;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);


        setContentView(R.layout.activity_spalsh_page);
        Handler hd =new Handler();
        hd.postDelayed(new Runnable(){
            public void run(){
                finish();
            }
        },3000);


    }

}