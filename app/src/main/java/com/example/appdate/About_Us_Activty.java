package com.example.appdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.card.MaterialCardView;

public class About_Us_Activty extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us__activty);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageButton imageButton = findViewById(R.id.back_image);
        imageButton.setOnClickListener(v -> onBackPressed());

        MaterialCardView cardView = findViewById(R.id.about_us_card);
        cardView.setOnClickListener(view ->
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] strTo = { "aliesmaelfard@gmail.com" };
            intent.putExtra(Intent.EXTRA_EMAIL, strTo);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Body");
            intent.setType("message/rfc822");
            intent.setPackage("com.google.android.gm");
            startActivity(intent);
        });
    }
}