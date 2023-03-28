package com.example.example_btl_androidnc.api;

import android.content.Context;

import com.example.example_btl_androidnc.network.AuthInterceptor;
import com.example.example_btl_androidnc.network.TokenAuthenticator;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://192.168.0.102:8082/";

    public static final String BASE_IMG = "http://192.168.0.102:8082";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getInstance(Context context, String baseUrl, String authToken) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(authToken))
                .authenticator(new TokenAuthenticator(context))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
