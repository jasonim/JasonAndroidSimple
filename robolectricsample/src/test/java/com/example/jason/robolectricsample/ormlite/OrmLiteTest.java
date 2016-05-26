package com.example.jason.robolectricsample.ormlite;

import com.example.jason.robolectricsample.BuildConfig;
import com.j256.ormlite.dao.Dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jsson on 16/5/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class OrmLiteTest {
    DatabaseHelper helper;
    Dao<SimpleData, Integer> dao;

    @Before
    public void setUp() throws SQLException {
        helper = DatabaseHelper.getHelper();
        dao = helper.getDao();
    }

    @After
    public void tearDown(){
        DatabaseHelper.releaseHelper();
    }

    @Test
    public void testSave() throws SQLException{
        long millis = System.currentTimeMillis();
        dao.create(new SimpleData(millis));
        dao.create(new SimpleData(millis + 1));
        dao.create(new SimpleData(millis + 2));

        assertEquals(dao.countOf(), 3);

        List<SimpleData> simpleDatas = dao.queryForAll();
        assertEquals(simpleDatas.get(0).millis, millis);

    }

    @Test
    public void testQueryForId() throws SQLException {
        long millis = System.currentTimeMillis();
        SimpleData simpleData = dao.createIfNotExists(new SimpleData(millis));
        assertNotNull(dao.queryForId(simpleData.id));
    }
}
