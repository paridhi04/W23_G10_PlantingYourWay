package com.example.plantingyourway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static final String TABLE_USERS = "USERS";

    // Table columns
    public static final String COL_ID = "id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_USER_ADDRESS = "user_address";
    public static final String COL_USER_CITY = "user_city";
    public static final String COL_USER_PROVINCE = "user_province";
    public static final String COL_USER_EMAIL = "user_email";
    public static final String COL_USER_PASSWORD = "user_password";
    public static final String COL_USER_PHONE_NO = "user_phone_no";


    // Creating table query
    private static final String CREATE_TABLE_USER = "create table " + TABLE_USERS + "(" + COL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USER_NAME + " TEXT NOT NULL, "
            + COL_USER_ADDRESS + " TEXT NOT NULL, "
            + COL_USER_CITY + " TEXT , "
            + COL_USER_PROVINCE + " TEXT , "
            + COL_USER_EMAIL + " TEXT , "
            + COL_USER_PASSWORD + " TEXT ,"
            + COL_USER_PHONE_NO + " TEXT"+
            ");";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }


}



