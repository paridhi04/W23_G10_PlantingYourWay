package com.example.plantingyourway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }


    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertServiceProvider(UserDetailsDataModel userDetailsDataModel) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COL_USER_NAME, userDetailsDataModel.getUserName());
        contentValue.put(DatabaseHelper.COL_USER_ADDRESS, userDetailsDataModel.getUserAddress());
        contentValue.put(DatabaseHelper.COL_USER_CITY, userDetailsDataModel.getUserCity());
        contentValue.put(DatabaseHelper.COL_USER_PROVINCE, userDetailsDataModel.getUserProvince());
        contentValue.put(DatabaseHelper.COL_USER_EMAIL, userDetailsDataModel.getUserEmail());
        contentValue.put(DatabaseHelper.COL_USER_PASSWORD, userDetailsDataModel.getUserPassword());
        contentValue.put(DatabaseHelper.COL_USER_PHONE_NO, userDetailsDataModel.getUserPhone());
        return database.insert(DatabaseHelper.TABLE_USERS, null, contentValue);
    }
    public Cursor fetchUserDetails(String email, String password) {
        String sqlString = "SELECT * FROM " + DatabaseHelper.TABLE_USERS + " WHERE " +
                DatabaseHelper.COL_USER_EMAIL + "='" + email + "' and " +
                DatabaseHelper.COL_USER_PASSWORD + "='" + password + "' and ";

        Cursor cursor = database.rawQuery(sqlString, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
