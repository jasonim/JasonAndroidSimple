package com.example.jason.robolectricsample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jason.robolectricsample.RebolectricApplication;

/**
 * Created by jsson on 16/5/26.
 */
public class AccountDBHelper extends SQLiteOpenHelper {

    private static AccountDBHelper mAccountDBHelper;
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "rebolectric.db";

    public AccountDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AccountDBHelper getInstance() {
        if (null == mAccountDBHelper) {
            mAccountDBHelper = new AccountDBHelper(RebolectricApplication.getInstance(), AccountDBHelper.DB_NAME, null, AccountDBHelper.DB_VERSION);
        }
        return mAccountDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("hujd", "onCreate");
        AccountTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
