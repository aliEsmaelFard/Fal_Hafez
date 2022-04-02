package com.example.appdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imageView = findViewById(R.id.icon_splash);
        imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_animation));

        TextView textView = findViewById(R.id.splash_text);
        textView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_animation));

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run()
            {
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
                finish();
            }
        }, 2000);


    }

    @Override
    public void onBackPressed()
    {
    }
}