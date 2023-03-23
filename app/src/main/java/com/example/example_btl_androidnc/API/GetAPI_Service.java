package com.example.example_btl_androidnc.API;

import com.example.example_btl_androidnc.Model.Account;
import com.example.example_btl_androidnc.Model.Course;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetAPI_Service {
    //hiển thị lớp học ở trang chủ
    @GET("api/admin/course")
    Call<List<Course>> getCourse();

// Đăng kí tài khoản
    @POST("/api/auth/register")
    Call<Account> createAccount(@Body Account req);

    // đăng nhập
    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<User> login(
            @Field("email") String username,
            @Field("password") String password
    );
}
