package com.example.jason.robolectricsample.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.jason.robolectricsample.BuildConfig;
import com.example.jason.robolectricsample.db.AccountDBHelper;
import com.example.jason.robolectricsample.db.AccountTable;
import com.example.jason.robolectricsample.util.AccountUtil;
import com.googlecode.eyesfree.utils.ContrastUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;

/**
 * Created by jsson on 16/5/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ContentProviderTest {

    private static final String AUTHORITY = "com.example.jason.robolectricsample.AccountProvider";
    private AccountProvider accountProvider;
    private ContentResolver contentResolver;
    private ShadowContentResolver shadowContentResoler;
    private Uri uri_personal_info = Uri.parse("content://" + AUTHORITY + "/" + AccountTable.TABLE_NAME);


    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        accountProvider = new AccountProvider();
        contentResolver = RuntimeEnvironment.application.getContentResolver();

        shadowContentResoler =  Shadows.shadowOf(contentResolver);

        accountProvider.onCreate();

        ShadowContentResolver.registerProvider(AUTHORITY, accountProvider);

    }
    @After
    public void tearDown() {
        AccountUtil.resetSingleton(AccountDBHelper.class, "mAccountDBHelper");
    }

    @Test
    public void testQuery(){
        ContentValues contentValues1 = AccountUtil.getContentValues("1");
        ContentValues contentValues2 = AccountUtil.getContentValues("2");

        shadowContentResoler.insert(uri_personal_info, contentValues1);
        shadowContentResoler.insert(uri_personal_info, contentValues2);

        //查询所有数据
        Cursor query = shadowContentResoler.query(uri_personal_info, null, null, null, null);
        assertEquals(query.getCount(), 2);

        Uri uri = ContentUris.withAppendedId(uri_personal_info, 2);
        Cursor query1 = shadowContentResoler.query(uri, null, null, null, null);
        assertEquals(query1.getCount(), 1);


    }
}
