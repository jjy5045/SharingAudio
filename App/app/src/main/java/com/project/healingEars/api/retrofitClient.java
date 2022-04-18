package com.project.healingEars.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.healingEars.api.interceptor.AddCookiesInterceptor;
import com.project.healingEars.api.interceptor.ReceivedCookiesInterceptor;
import com.project.healingEars.global;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {
    // 가상머신에서는 개인 서버에 접속할 때 ip주소를 10.0.2.2로 설정


    public static Retrofit getInstance() {

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(global.baseURL);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.client(okHttpClient);

        Retrofit retrofit = builder.build();

        return retrofit;
    }
}