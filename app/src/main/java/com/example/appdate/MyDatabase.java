package com.example.appdate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyDatabase extends SQLiteOpenHelper     {

    public static String DB_Name = "data.db";
    public static String DB_Path;
    public static Context dbcontext;

    public MyDatabase(Context context) {
        super(context, DB_Name, null, 1);
        this.dbcontext = context;
        DB_Path = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public void createDatabase() {
        try {
            File file = new File(DB_Path);
            if(!file.exists()){
                file.mkdir();
            }

            File database = new File(DB_Name);
            if(!database.exists()){
                InputStream inputStream = dbcontext.getAssets().open(DB_Name);
                OutputStream outputStream = new FileOutputStream(DB_Path + DB_Name);
                byte[] bytes = new byte[1024 * 4];

                while (inputStream.read(bytes) != -1){
                    outputStream.write(bytes);
                }
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
