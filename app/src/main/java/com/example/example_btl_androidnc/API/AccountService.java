package com.example.example_btl_androidnc.API;

import com.example.example_btl_androidnc.Model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService{
        @POST("/api/auth/register")
        Call<Account> createAccount(@Body Account req);
}
