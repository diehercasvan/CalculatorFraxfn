package com.edibca.fraxfnGTRD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.edibca.fraxfn.R;


/**
 * Created by Diego Casallas  on 25/06/2015.
 */
public class Splash_Start extends Activity {

    private Animation animation;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_start);
        animation= AnimationUtils.loadAnimation(this, R.anim.frame_animation);
        imageView=(ImageView)findViewById(R.id.img_start);
        imageView.startAnimation(animation);
        launchThread();
    }
    public  void launchThread(){
        Thread thread;
        try
        {
            thread=new Thread(){
                public void run(){
                    try {

                        sleep(2500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Intent intent=new Intent(Splash_Start.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            thread.start();
        }
        catch(Exception e)
        {

            Log.i("Error Start", "Start Activity");
        }
    }
}
