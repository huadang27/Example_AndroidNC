package com.example.example_btl_androidnc.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.Controller.SignUpController;
import com.example.example_btl_androidnc.R;

public class SignUpActivity extends AppCompatActivity {
    Button bt_Signup;
    ImageView img_Back;
    EditText edt_Name, edt_Email, edt_Password;

    private SignUpController signUpController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpController = new SignUpController(this);

        img_Back = findViewById(R.id.img_back);
        edt_Name = findViewById(R.id.edt_Name);
        edt_Email = findViewById(R.id.edt_Email);
        edt_Password = findViewById(R.id.edt_Password);
        bt_Signup = findViewById(R.id.bt_Signup);

        bt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_Name.getText().toString();
                String email = edt_Email.getText().toString();
                String password = edt_Password.getText().toString();
                signUpController.register(name, email, password);
            }
        });

        getControls();
    }

    private void getControls() {
        // nút thoát
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void navigateToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}