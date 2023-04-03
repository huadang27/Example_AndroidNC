package com.example.example_btl_androidnc.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.addItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.api.GetAPI_Service;
import com.example.example_btl_androidnc.api.RetrofitClient;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.database.MySharedPreferences;
import com.example.example_btl_androidnc.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// private SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
public class LoginActivity extends AppCompatActivity {
    public EditText edtemail, edtpassword;
    public TextView forgot_password, tv_register;
    Button btnlogin;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onStart() {
        super.onStart();
        checkSavedCredentials();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_password);
        tv_register = findViewById(R.id.tv_register);
        forgot_password = findViewById(R.id.forgot_password);
        btnlogin = findViewById(R.id.btn_login);
        mySharedPreferences = new MySharedPreferences(LoginActivity.this);


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

    public void login(String email, String password) {
        GetAPI_Service authService = RetrofitClient.getInstance(LoginActivity.this, RetrofitClient.BASE_URL, "").create(GetAPI_Service.class);
        Users users = new Users(email, password);

        Call<Users> call = authService.login(users);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users jwtResponse = response.body();
                    Log.d("testtoken",jwtResponse.toString());
                    if (jwtResponse != null) {
                        mySharedPreferences.saveData(jwtResponse.getAccessToken(), jwtResponse.getId(), jwtResponse.getEmail(), password, jwtResponse.getName(),jwtResponse.getCourseLists().toString());
                        Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Không thể nhận được thông tin đăng nhập, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra thông tin đăng nhập và thử lại!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Kết nối thất bại, vui lòng kiểm tra kết nối và thử lại!" + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("testtoken",t.toString());
            }
        });
    }

    public boolean validateInput(String email, String password) {
        boolean isValid = true;

        if (TextUtils.isEmpty(email)) {
            edtemail.setError("Email không được để trống.");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            edtpassword.setError("Mật khẩu không được để trống.");
            isValid = false;
        }

        return isValid;
    }

        public void checkSavedCredentials() {

        String email = mySharedPreferences.getEmail();
        String password = mySharedPreferences.getPassword();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            login(email, password);
            Log.d("testtoken"," token đã bị thay đổi khi đăng nhập lần sau: ");
        }
    }



}