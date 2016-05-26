package com.example.jason.robolectricsample.net;

import android.util.Log;

import com.example.jason.robolectricsample.BuildConfig;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jsson on 16/5/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GithubServiceTest {
    private GithubService githubService;

    @Before
    public void setUp() throws URISyntaxException{
        ShadowLog.stream = System.out;
        githubService = GithubService.Factory.create();

    }
    @Test
    public void testPublicRepositories() throws IOException{
        Call<List<Repository>> call = githubService.publicRepositories("geniusmart");
        Response<List<Repository>> execute = call.execute();
        List<Repository> body = execute.body();
        Log.i("hujd", new Gson().toJson(body));
        assertTrue(body.size() > 0);
        assertNotNull(body.get(0).name);

        User user = githubService.user("jasonim").execute().body();
        Log.i("hujd", new Gson().toJson(user));
        assertNotNull(user);
    }
}
