package com.example.example_btl_androidnc.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.example_btl_androidnc.AddItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.Controller.LoginController;

import com.example.example_btl_androidnc.R;

// private SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
public class LoginActivity extends AppCompatActivity {
    public EditText edtemail, edtpassword;
    public TextView forgot_password, tv_register;
    Button btnlogin;
    LoginController loginController;

    @Override
    protected void onStart() {
        super.onStart();
//        loginController.checkSavedCredentials();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController = new LoginController(this);
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
                if (loginController.validateInput(email_text, pass_text)) {
                    loginController.login(email_text, pass_text);
                }

            }
        });

    }


}