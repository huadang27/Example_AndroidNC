package com.example.example_btl_androidnc.fragment;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.addItem.Edit_Profile;
import com.example.example_btl_androidnc.addItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.api.GetAPI_Service;
import com.example.example_btl_androidnc.api.RetrofitClient;
import com.example.example_btl_androidnc.authentication.LoginActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.database.MySharedPreferences;
import com.example.example_btl_androidnc.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Profile_UserFragment extends Fragment {
    private MySharedPreferences mySharedPreferences;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button Bt_sign_out,Bt_infor_mail;
    private TextView tv_Edit, Name_user;
    private Context context;
    public Profile_UserFragment() {
        // Required empty public constructor
    }

    public static Profile_UserFragment newInstance(String param1, String param2) {
        Profile_UserFragment fragment = new Profile_UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getUserProfile();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile__user, container, false);

        View view = inflater.inflate(R.layout.fragment_profile__user, container, false);

        Bt_sign_out = view.findViewById(R.id.bt_sign_out);
        tv_Edit = view.findViewById(R.id.tv_edit_profile);
        Name_user = view.findViewById(R.id.name_user);
        Bt_infor_mail = view.findViewById(R.id.bt_infor_mail);
        Bt_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySharedPreferences.clearData();
                Log.d("testtoken", " đăng xuất đã xóa token");
                startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        tv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Edit_Profile.class);
                //Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                startActivity(i);
            }
        });
        return view;
    }


    public void getUserProfile() {
MySharedPreferences mySharedPreferences1 = new MySharedPreferences(getContext());
     //   SharedPreferences prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token =mySharedPreferences1.getToken();
        GetAPI_Service getAPI_service = RetrofitClient.getInstance(getContext(), RetrofitClient.BASE_URL, token).create(GetAPI_Service.class);;
        Call<Users> call = getAPI_service.getUserProfile("Bearer " + token);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users users = response.body();
                    // Hiển thị thông tin của người dùng lên giao diện

                    Name_user.setText(users.getName());
                    Bt_infor_mail.setText(users.getEmail());
                    // Lấy các thông tin khác và hiển thị lên giao diện
                    // ...
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(getContext(), "Không thể lấy thông tin người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Xử lý khi có lỗi
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireActivity().getApplicationContext();
        mySharedPreferences = new MySharedPreferences(context);
    }
}