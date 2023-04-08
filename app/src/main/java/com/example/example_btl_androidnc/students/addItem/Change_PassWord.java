package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.ChangePass;
import com.example.example_btl_androidnc.students.model.RefreshTokenRequest;
import com.example.example_btl_androidnc.students.model.Users;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Change_PassWord extends AppCompatActivity {
private ImageView imgBack;
private TextInputLayout oldpassword,newpassword,retypepassword;
private Button Btn_update_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);

        imgBack = findViewById(R.id.img_back);
        oldpassword = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword);
        retypepassword = findViewById(R.id.retypepassword);
        Btn_update_pass = findViewById(R.id.btn_update_pass);
        getControls();
        Btn_update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldpassword.getEditText().getText().toString();
                String newPassword = newpassword.getEditText().getText().toString();
                String confirmPassword = retypepassword.getEditText().getText().toString();
                onChangePasswordClicked(oldPassword,newPassword,confirmPassword);
            }
        });
    }

    public void onChangePasswordClicked(String oldPassword,String newPassword,String confirmPassword ) {


        MySharedPreferences mySharedPreferences1 = new MySharedPreferences(Change_PassWord.this);
        //   SharedPreferences prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token =mySharedPreferences1.getToken();
        ChangePass changePass = new ChangePass(oldPassword,newPassword,confirmPassword);
        GetAPI_Service getAPI_service = RetrofitClient.getInstance(Change_PassWord.this, RetrofitClient.BASE_URL, token).create(GetAPI_Service.class);;
        Call<ChangePass> call = getAPI_service.changePassword("Bearer " + token,changePass);

        call.enqueue(new Callback<ChangePass>() {
            @Override
            public void onResponse(Call<ChangePass> call, Response<ChangePass> response) {
                if (response.isSuccessful()) {

                    // Hiển thị thông báo đổi mật khẩu thành công
                    Toast.makeText(Change_PassWord.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo đổi mật khẩu không thành công
                    Toast.makeText(Change_PassWord.this, "Password change failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePass> call, Throwable t) {
                Toast.makeText(Change_PassWord.this, "Failed to call API", Toast.LENGTH_SHORT).show();
                Log.d("test", " Đăng kí không thành công \n " + t);
                Log.d("test", call.toString());
               // Toast.makeText(Change_PassWord.this, "Failed to call API", Toast.LENGTH_SHORT).show();
            }

//            @Override
//            public void onFailure(Call<ChangePass> call, Throwable t) {
//                // Hiển thị thông báo lỗi gọi API
//                Toast.makeText(Change_PassWord.this, "Failed to call API", Toast.LENGTH_SHORT).show();
//            }
        });
    }




    private void getControls() {
        // nút thoát
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}