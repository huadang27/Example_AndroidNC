package com.example.example_btl_androidnc.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.example_btl_androidnc.AddItem.ClassActivity;
import com.example.example_btl_androidnc.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin_HomeFragment extends Fragment {


    TextView textViewDateTime;
    Button btCreate_Class;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_home, container, false);



        return view;
    }

//    private void getControls() {
//        // chuyển sang acivity tính tương
//        btCreate_Class.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), ClassActivity.class));
//            }
//        });
//    }

    //lấy ngày thực tại trong activitu trang chủ
//    private void getDay() {
//        textViewDateTime = view.findViewById(R.id.textViewDateTime);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("'Ngày' dd 'Tháng' MM 'Năm' yyyy");
//        String currentDateTime = dateFormat.format(new Date());
//        textViewDateTime.setText(currentDateTime);
//
//    }
//
//    private void getLinkViews() {
//        btCreate_Class = view.findViewById(R.id.bt_create_class);
//
//    }
}
