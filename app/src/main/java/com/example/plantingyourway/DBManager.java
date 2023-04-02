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

//    public long insertServiceReminder(ReminderModel reminderModel) {
//        ContentValues contentValue = new ContentValues();
//        contentValue.put(DatabaseHelper.COL_REMINDER_ID, reminderModel.getReminderId());
//        contentValue.put(DatabaseHelper.COL_REMINDER_USER_ID, reminderModel.getUserId());
//        contentValue.put(DatabaseHelper.COL_REMINDER_PROVIDER_ID, reminderModel.getProivderId());
//        contentValue.put(DatabaseHelper.COL_SERVICE_DATE, reminderModel.getAppointmentDate());
//        contentValue.put(DatabaseHelper.COL_SERVICE_ADDRESS, reminderModel.getAppointmentAddress());
//
//        return database.insert(DatabaseHelper.TABLE_REMINDER, null, contentValue);
//    }

    public long insertServiceProvider(UserDetailsDataModel userDetailsDataModel) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COL_ID, userDetailsDataModel.getId());
        contentValue.put(DatabaseHelper.COL_USER_NAME, userDetailsDataModel.getUserName());
        contentValue.put(DatabaseHelper.COL_USER_ADDRESS, userDetailsDataModel.getUserAddress());
        contentValue.put(DatabaseHelper.COL_USER_CITY, userDetailsDataModel.getUserCity());
        contentValue.put(DatabaseHelper.COL_USER_PROVINCE, userDetailsDataModel.getUserProvince());
        contentValue.put(DatabaseHelper.COL_USER_EMAIL, userDetailsDataModel.getUserEmail());
        contentValue.put(DatabaseHelper.COL_USER_PASSWORD, userDetailsDataModel.getUserPassword());
        contentValue.put(DatabaseHelper.COL_USER_PHONE_NO, userDetailsDataModel.getUserPhone());
        return database.insert(DatabaseHelper.TABLE_USERS, null, contentValue);
    }

}
