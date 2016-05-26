package com.example.jason.robolectricsample.net;

import com.example.jason.robolectricsample.util.FileUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by jsson on 16/5/26.
 * 网络请求的拦截器,用于Mock响应数据
 */
public class MockInterceptor implements Interceptor{

    private final String responeJsonPath;

    public MockInterceptor(String responeJsonPath) {
        this.responeJsonPath = responeJsonPath;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String responeString = createResponseBody(chain);
        Response response = new Response.Builder().code(200)
                .message(responeString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responeString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
        return response;
    }

    /**
     * 读文件获取json字符串, 生成ResponseBody
     * @param chain
     * @return
     */
    private String createResponseBody(Chain chain) {
        String responseString = null;
        HttpUrl uri = chain.request().url();
        String path = uri.uri().getPath();
        if (path.matches("^(/users/)+[^/]*+(/repos)$")) {//匹配/users/{username}/repos
            responseString = getResponseString("users_repos.json");
        } else if (path.matches("^(/users/)+[^/]+(/following)$")) {//匹配/users/{username}/following
            responseString = getResponseString("users_following.json");
        } else if (path.matches("^(/users/)+[^/]*+$")) {//匹配/users/{username}
            responseString = getResponseString("users.json");
        }

        return responseString;
    }

    private String getResponseString(String fileName) {
        return FileUtil.readFile(responeJsonPath + fileName, "UTF-8").toString();
    }

}
