package com.example.appdate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.Random;

public class FalActivity extends AppCompatActivity {

    TextView textViewFal , textViewTafsir;
    ScrollView scrollView;
    MaterialCardView cardView;
    LinearLayout linearLayout;
    ImageButton copy_IB, reply_IB, share_IB;
    String mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fal);

        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewFinder();
        selectPoem();

        //hide|show cardView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) ->
            {
                if ( scrollY > oldScrollY)
                      viewGoneAnimator(cardView);
                else
                      viewVisibleAnimator(cardView);
            });
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCardVisable = cardView.isShown();

                if (!isCardVisable)
                {
                    viewVisibleAnimator(cardView);
                }
                else if (isCardVisable)
                {
                    viewGoneAnimator(cardView);
                }
            }
        });

        //replay action
        reply_IB.setOnClickListener(view ->
        {
            selectPoem();
        });

        //copy action
        copy_IB.setOnClickListener(view ->
        {
            copyPoem();
        });

        //share action
        share_IB.setOnClickListener(view ->
        {
            sharePoem();
        });


    }

    public void selectPoem()
    {
        MyDatabase myDatabase = new MyDatabase(this);
        myDatabase.createDatabase();

        Random random = new Random();
        int number = random.nextInt((400-1) +1+1);

        SQLiteDatabase sqL = myDatabase.getWritableDatabase();
        Cursor cursor = sqL.rawQuery("SELECT * FROM ashar WHERE ID = " + number , null);

        if(cursor.moveToNext()){
            textViewFal.setText(cursor.getString(cursor.getColumnIndex("sher")));
            textViewTafsir.setText(cursor.getString(cursor.getColumnIndex("mani")));
        }
        cursor.close();
        sqL.close();
    }

    private void copyPoem()
    {
        String poem = textViewFal.getText().toString();
        String defenition = textViewTafsir.getText().toString();
        mainText = poem +"\n" +  "تفسیر:" + "\n" + defenition;

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("FAL" , mainText);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(this, "فال کپی شد", Toast.LENGTH_SHORT).show();
    }

    private void sharePoem()
    {
        /*Create an ACTION_SEND Intent*/
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        /*This will be the actual content you wish you share.*/
        String shareBody = mainText;
        /*The type of the content is text, obviously.*/
        intent.setType("text/plain");
        /*Applying information Subject and Body.*/
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "فال حافظ");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        /*Fire!*/
        startActivity(Intent.createChooser(intent, "فال حافظ"));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FalActivity.this , MainActivity.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
        finish();

    }
    private void viewGoneAnimator(final View view) {

        view.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });

    }

    private void viewVisibleAnimator(final View view) {

        view.animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void viewFinder()
    {
    textViewFal    = findViewById(R.id.FalTXT);
    textViewTafsir = findViewById(R.id.TafsirTXT);
    scrollView     = findViewById(R.id.scrollView);
    cardView       = findViewById(R.id.cardView);
    linearLayout   = findViewById(R.id.linearLayoutScrollabe);
    copy_IB        = findViewById(R.id.copy_IB);
    reply_IB       = findViewById(R.id.reply_IB);
    share_IB       = findViewById(R.id.share_IB);
    }
}