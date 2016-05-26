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

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jsson on 16/5/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MockGithubServiceTest {
    private static  final String JSON_ROOT_PATH = "/json/";
    private GithubService githubService;
    private String jsonFullPath;

    @Before
    public void setUp() throws URISyntaxException{
        ShadowLog.stream = System.out;
        jsonFullPath = getClass().getResource(JSON_ROOT_PATH).toURI().getPath();
        //定义Http Client, 并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor(jsonFullPath))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        githubService = retrofit.create(GithubService.class);
    }

    @Test
    public void testMockPublicRepositories() throws IOException {
        Response<List<Repository>> response = githubService.publicRepositories("jasonim").execute();
        assertEquals(response.body().get(0).name, "Android-CleanArchitecture");
    }

    @Test
    public void testMockFollowUser() throws IOException {
        Response<List<User>> response = githubService.followingUser("jasonim").execute();
        assertEquals(response.body().get(0).login, "baohaojun");

    }

    @Test
    public void testMockUser() throws IOException {
        Response<User> userResponse = githubService.user("jasonim").execute();
        assertEquals(userResponse.body().login, "jasonim");
    }

}
