package com.example.example_btl_androidnc.AddItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example_btl_androidnc.Model.Student;
import com.example.example_btl_androidnc.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentActivity extends AppCompatActivity {
    private EditText mStudentIdEditText;
    private EditText mNameEditText;
    private EditText mDateOfBirthEditText;
    private EditText mAddressEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mClassIdEditText;
    Button btnAddStudent;
    DatabaseReference refStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mStudentIdEditText = findViewById(R.id.student_id_edittext);
        mNameEditText = findViewById(R.id.name_edittext);
        mDateOfBirthEditText = findViewById(R.id.date_of_birth_edittext);
        mAddressEditText = findViewById(R.id.address_edittext);
        mPhoneEditText = findViewById(R.id.phone_edittext);
        mEmailEditText = findViewById(R.id.email_edittext);
        mClassIdEditText = findViewById(R.id.class_id_edittext);
        btnAddStudent = findViewById(R.id.btn_Add_Student);

        refStudent = FirebaseDatabase.getInstance().getReference("Students");

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mNameEditText.getText().toString().trim();
                String dateOfBirth = mDateOfBirthEditText.getText().toString().trim();
                String address = mAddressEditText.getText().toString().trim();
                String phone = mPhoneEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();
                String classId = mClassIdEditText.getText().toString().trim();

                String studentId = refStudent.push().getKey();
                Student student = new Student(studentId, name, dateOfBirth, address, phone, email, classId, "", "");
                refStudent.child(studentId).setValue(student);
            }
        });


    }
}