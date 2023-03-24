package com.example.example_btl_androidnc.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.API.GetAPI_Service;
import com.example.example_btl_androidnc.API.RetrofitClient;
import com.example.example_btl_androidnc.AddItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.Model.Users;

import com.example.example_btl_androidnc.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// private SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
public class LoginActivity extends AppCompatActivity {
    EditText edtemail, edtpassword;
    TextView forgot_password, tv_register;
    Button btnlogin;


//    FirebaseAuth auth;
//    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        // Lấy thông tin tài khoản đã lưu trữ trong SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        // Nếu đã lưu thông tin tài khoản, gửi yêu cầu đăng nhập đến máy chủ
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            login(email, password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        // Lấy thông tin tài khoản đã lưu trữ trong SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String email = sharedPreferences.getString("email", "");
//        String password = sharedPreferences.getString("password", "");
//
//        // Nếu đã lưu thông tin tài khoản, gửi yêu cầu đăng nhập đến máy chủ
//        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
//            login(email, password);
//        }

        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        tv_register = findViewById(R.id.tv_register);
        forgot_password = findViewById(R.id.forgot_password);
        btnlogin = findViewById(R.id.btn_login);


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                startActivity(i);

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = edtemail.getText().toString();
                String pass_text = edtpassword.getText().toString();
                if (validateInput(email_text, pass_text)) {
                    login(email_text, pass_text);
                }

            }
        });

    }

    private void login(String edtemail, String edtpassword) {
        GetAPI_Service authService = RetrofitClient.getInstance(LoginActivity.this, RetrofitClient.BASE_URL, "").create(GetAPI_Service.class);
        Users users = new Users(edtemail,edtpassword);

        Call<Users> call = authService.login(users);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users jwtResponse = response.body();
                    if (jwtResponse != null) {
                        // Lưu token vào SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("authToken", jwtResponse.getAccessToken());
                        editor.putString("refreshToken", jwtResponse.getRefreshToken());
                        editor.putString("email", edtemail);
                        editor.putString("password", edtpassword);
                        editor.apply();
                        Log.d("test",editor.toString() +"____");

                        // Chuyển đến màn hình chính sau khi đăng nhập thành công
                        Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Không thể nhận được thông tin đăng nhập, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo lỗi đăng nhập
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra thông tin đăng nhập và thử lại!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Kết nối thất bại, vui lòng kiểm tra kết nối và thử lại!" +
                                t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("test", t +"onFailure 111");
            }
        });
    }

    private boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            edtemail.setError("Email không được để trống.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            edtpassword.setError("Mật khẩu không được để trống.");
            return false;
        }

        return true;
    }
}