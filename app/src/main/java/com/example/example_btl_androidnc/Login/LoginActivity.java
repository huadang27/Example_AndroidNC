package com.example.example_btl_androidnc.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.Adapter.ViewPagetAdapter;
import com.example.example_btl_androidnc.AddItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.Fragment.Admin_HomeFragment;
import com.example.example_btl_androidnc.MainActivity;
import com.example.example_btl_androidnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText edtemail, edtpassword;
    TextView forgot_password, tv_register;
    Button btnlogin;


    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        // kiem tra nguoi dung da dang nhap hay chua
        if (firebaseUser != null) {

            Intent i = new Intent(LoginActivity.this, SetAdmin_Activity.class);
            startActivity(i);
            finish();

            // email nguoi dung dang nhap
            Toast.makeText(LoginActivity.this, firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
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


        auth = FirebaseAuth.getInstance();
        // lấy thông tin người dùng khi đang đăng nhập
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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


                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)) {
                    Toast.makeText(LoginActivity.this, "không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu sai", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}