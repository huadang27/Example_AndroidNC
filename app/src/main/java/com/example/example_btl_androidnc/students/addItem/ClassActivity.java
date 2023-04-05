package com.example.example_btl_androidnc.students.addItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example_btl_androidnc.students.model.Class;
import com.example.example_btl_androidnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassActivity extends AppCompatActivity {
    EditText edtMalop, edt_Tenlop, edt_Mamon, edt_Ma_gv, edt_Ngaybatdau, edt_Ngayketthuc, actw_Status, edt_Sobuoi;
    TextInputLayout til_Malop, til_Tenlop, til_Mamon, til_Ma_gv, til_Ngaybatdau, til_Ngayketthuc, til_Status, til_Sobuoi;
    Button bt_save;
    ImageView imgBack;
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getLinkViews();
        getControls();
        auth = FirebaseAuth.getInstance();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass(edtMalop.getText().toString().trim(),
                        edt_Tenlop.getText().toString().trim(),
                        edt_Mamon.getText().toString().trim(),
                        edt_Ma_gv.getText().toString().trim(),
                        edt_Ngaybatdau.getText().toString().trim(),
                        edt_Ngayketthuc.getText().toString().trim(),
                        actw_Status.getText().toString().trim(),
                        Integer.parseInt(edt_Sobuoi.getText().toString().trim())
                );
            }
        });
    }

    private void addClass(String Malop, String Tenlop, String Mamon, String Ma_gv, String Ngaybatdau, String Ngaykethuc, String status, int sobuoi) {
        // Tạo đối tượng Student với thông tin cung cấp
        Class aClass = new Class(Malop, Tenlop, Mamon, Ma_gv, Ngaybatdau, Ngaykethuc, status, sobuoi);

        // Tạo một đối tượng DatabaseReference để tham chiếu đến nút "Students" trong Firebase Realtime Database
        myRef = FirebaseDatabase.getInstance().getReference("Class");

        // Tạo một khóa tự động cho học sinh mới
        String key = myRef.push().getKey();

        // Đẩy thông tin học sinh mới lên Firebase Realtime Database
        myRef.child(key).setValue(aClass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Nếu đẩy thông tin thành công, hiển thị thông báo thành công
                            Toast.makeText(ClassActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // Nếu đẩy thông tin thất bại, hiển thị thông báo lỗi
                            Toast.makeText(ClassActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
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

    private void getLinkViews() {
        edtMalop = findViewById(R.id.edt_malop);
        edt_Tenlop = findViewById(R.id.edt_tenlop);
        edt_Mamon = findViewById(R.id.edt_mamon);
        edt_Ma_gv = findViewById(R.id.edt_ma_gv);
        edt_Ngaybatdau = findViewById(R.id.edt_ngaybatdau);
        edt_Ngayketthuc = findViewById(R.id.edt_ngayketthuc);
        actw_Status = findViewById(R.id.actw_status);
        edt_Sobuoi = findViewById(R.id.edt_sobuoi);

        til_Malop = findViewById(R.id.til_malop);
        til_Tenlop = findViewById(R.id.til_tenlop);
        til_Mamon = findViewById(R.id.til_mamon);
        til_Ma_gv = findViewById(R.id.til_ma_gv);
        til_Ngaybatdau = findViewById(R.id.til_ngaybatdau);
        til_Ngayketthuc = findViewById(R.id.til_ngayketthuc);
        til_Status = findViewById(R.id.til_status);
        til_Sobuoi = findViewById(R.id.til_sobuoi);

        bt_save = findViewById(R.id.bt_save);
        imgBack = findViewById(R.id.img_back);
    }
}