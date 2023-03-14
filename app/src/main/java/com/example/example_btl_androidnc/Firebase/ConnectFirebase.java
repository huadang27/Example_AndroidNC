package com.example.example_btl_androidnc.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConnectFirebase {


    // Student
    public static final DatabaseReference refStudent = FirebaseDatabase.getInstance().getReference("Students");


    //Teacher
    public static final DatabaseReference refTeacher = FirebaseDatabase.getInstance().getReference("Teachers");

    //Schedule
    public static final DatabaseReference refSchedule = FirebaseDatabase.getInstance().getReference("Schedules");
    //Storage image
    public static final StorageReference storageReference = FirebaseStorage.getInstance().getReference("uploads");
    // Account nguoi dung
    public static final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    ;


}
