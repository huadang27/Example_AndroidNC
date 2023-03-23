package com.example.example_btl_androidnc.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.API.GetAPI_Service;
import com.example.example_btl_androidnc.API.RetrofitClient;
import com.example.example_btl_androidnc.Model.Account;
import com.example.example_btl_androidnc.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    Button bt_Signup, tv_Signup;
    ImageView img_Back;
    EditText edt_Name, edt_Email, edt_Password;
//    FirebaseAuth auth;
//    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        img_Back = findViewById(R.id.img_back);
        edt_Name = findViewById(R.id.edt_Name);
        edt_Email = findViewById(R.id.edt_Email);
        edt_Password = findViewById(R.id.edt_Password);
        bt_Signup = findViewById(R.id.bt_Signup);


//        //firebase auth
//        auth = FirebaseAuth.getInstance();


        bt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);

                Call<Account> call = getAPI_service.createAccount(new Account("","",edt_Email.getText().toString(),"",edt_Name.getText().toString(), edt_Password.getText().toString(),"",""));

                call.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if (response.isSuccessful()) {
                            Account account = response.body();
                            Toast.makeText(SignUpActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                            // Xử lý kết quả trả về
                        } else {
                            // Xử lý lỗi
                            System.out.println("khong chay");
                            Toast.makeText(SignUpActivity.this, "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Log.d("g", t.toString());
                    }


                });
            }
        });
        getControls();
    }
//
//    private void RegisterNow(final String username, String email, String password) {
//
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//
//                    // Hàm này sẽ trả về thông tin người dùng hiện tại đăng nhập vào hệ thống
//
//                    FirebaseUser firebaseUser = auth.getCurrentUser();
//
//
////                            user ID của người dùng hiện tại, được lấy từ firebaseUser bằng hàm getUid().
//                    String userid = firebaseUser.getUid();
//                    // khởi tạo myRef de them vao firebase
//
//                    myRef = FirebaseDatabase.getInstance().getReference("Account").child(userid);
//
////                            myRef = FirebaseDatabase.getInstance().getReference("accounts");
////                            String accountId = myRef.push().getKey();
//
//                    Account account = new Account(username, email, password, "", "");
//                    Student student = new Student(email, username, "default");
//
////                            myRef.child(accountId).setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    if (task.isSuccessful()){
////                                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
////                                        startActivity(i);
////                                        finish();
////                                    }
////
////                                }
////                            });
//
//
//                    Log.d("TAG", "tài khoản: " + email + "Password: " + password);
//                    // up dữ liệu lên firebase theo hasmap
//                    refStudent.child(userid).setValue(student);
//                    myRef.setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if (task.isSuccessful()) {
//                                Intent i = new Intent(SignUpActivity.this, SetAdmin_Activity.class);
//                                startActivity(i);
//                                finish();
//                                Log.d("test1", "Dang kí thanh cong");
//                            }
//
//                        }
//
//                    });
//
//
//                } else {
//                    Toast.makeText(SignUpActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//    }

    private void getControls() {
        // nút thoát
        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
