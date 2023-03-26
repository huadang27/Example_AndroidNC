package com.example.example_btl_androidnc.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.example_btl_androidnc.API.GetAPI_Service;
import com.example.example_btl_androidnc.API.RetrofitClient;
import com.example.example_btl_androidnc.AddItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.Authentication.LoginActivity;
import com.example.example_btl_androidnc.Model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {

    private LoginActivity loginActivity;
    private SharedPreferences sharedPreferences;

    public LoginController() {
    }

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        sharedPreferences = loginActivity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void login(String email, String password) {
        GetAPI_Service authService = RetrofitClient.getInstance(loginActivity, RetrofitClient.BASE_URL, "").create(GetAPI_Service.class);
        Users users = new Users(email, password);

        Call<Users> call = authService.login(users);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users jwtResponse = response.body();
                    if (jwtResponse != null) {
                        saveUserCredentials(jwtResponse, email, password);
                        Intent intent = new Intent(loginActivity, SetAdmin_Activity.class);
                        loginActivity.startActivity(intent);
                        loginActivity.finish();
                    } else {
                        Toast.makeText(loginActivity, "Không thể nhận được thông tin đăng nhập, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginActivity, "Đăng nhập thất bại, vui lòng kiểm tra thông tin đăng nhập và thử lại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(loginActivity, "Kết nối thất bại, vui lòng kiểm tra kết nối và thử lại!" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserCredentials(Users jwtResponse, String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", jwtResponse.getAccessToken());
        editor.putString("refreshToken", jwtResponse.getRefreshToken());
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public boolean validateInput(String email, String password) {
        boolean isValid = true;

        if (TextUtils.isEmpty(email)) {
            loginActivity.edtemail.setError("Email không được để trống.");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            loginActivity.edtpassword.setError("Mật khẩu không được để trống.");
            isValid = false;
        }

        return isValid;
    }

    public void checkSavedCredentials() {
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            login(email, password);
        }
    }
}