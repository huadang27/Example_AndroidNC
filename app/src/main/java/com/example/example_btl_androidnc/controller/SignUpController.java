package com.example.example_btl_androidnc.controller;

import com.example.example_btl_androidnc.api.GetAPI_Service;
import com.example.example_btl_androidnc.api.RetrofitClient;
import com.example.example_btl_androidnc.authentication.SignUpActivity;
import com.example.example_btl_androidnc.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpController {
    private SignUpActivity signUpActivity;

    public SignUpController(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }
    public void register(String name, String email, String password) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);

        Call<Users> call = getAPI_service.createAccount(new Users("", "", email, "", name, password, "", "", ""));

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    signUpActivity.showSuccessMessage("Đăng kí thành công");
                    signUpActivity.navigateToLogin();
                } else {
                    signUpActivity.showErrorMessage("Đăng kí không thành công");
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                signUpActivity.showErrorMessage("Lỗi kết nối. Vui lòng thử lại.");
            }
        });
    }

}
