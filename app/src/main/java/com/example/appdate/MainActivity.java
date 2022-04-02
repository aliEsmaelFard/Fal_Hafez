package com.example.appdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton button;
    TextView textView, textView2;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        viewFinder();


    }


    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if (id == R.id.button)
        {
            button.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            aBoolean = true;

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, FalActivity.class);
                startActivity(intent);
                finish();
            }, 1200);
        }

        if (id == R.id.about_us_linearlayout)
        {
            Intent intent = new Intent(this , About_Us_Activty.class);
            startActivity(intent);
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
        }
    }

    @Override
    public void onBackPressed() {
        if (aBoolean)
        {
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void viewFinder() {
        button = findViewById(R.id.button);
        textView     = findViewById(R.id.textView);
        textView2    = findViewById(R.id.textView2);
        progressBar  = findViewById(R.id.progressbar);
        linearLayout = findViewById(R.id.about_us_linearlayout);

        button.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }
}