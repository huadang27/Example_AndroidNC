package com.example.example_btl_androidnc.students.api;

import android.content.Context;

import com.example.example_btl_androidnc.students.network.AuthInterceptor;
import com.example.example_btl_androidnc.students.network.TokenAuthenticator;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://192.168.3.199:8082/";
//192.168.3.199
//    10.0.2.2

    public static final String BASE_IMG = "http://192.168.3.199:8082";

    private static Retrofit retrofit = null;

//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit;
    }


    public static Retrofit getClient(String authToken) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new TokenInterceptor(authToken));
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getInstance(Context context, String baseUrl, String authToken) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
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
