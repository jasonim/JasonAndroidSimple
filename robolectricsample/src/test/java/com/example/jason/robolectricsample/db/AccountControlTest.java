package com.example.jason.robolectricsample.db;

import com.example.jason.robolectricsample.BuildConfig;
import com.example.jason.robolectricsample.util.AccountUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jsson on 16/5/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AccountControlTest {
    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
    }
    @After
    public void tearDown(){
        //执行每个test时,实例对象要重置为null, 否则出现异常
        AccountUtil.resetSingleton(AccountDBHelper.class, "mAccountDBHelper");
    }

    @Test
    public void testSave(){
        Account account = AccountUtil.createAccount("1");
        long result = AccountControl.save(account);
        assertTrue(result > 0);
    }

    @Test
    public void testUpdate(){
        Account account = AccountUtil.createAccount("2");
        AccountControl.save(account);

        account.name = "jasonim";
        int result = AccountControl.update(account);
        assertEquals(result, 1);

        Account newAccount = AccountControl.get("2");
        assertEquals(newAccount.name, "jasonim");
    }

    @Test
    public void testQuery(){

    }

}
