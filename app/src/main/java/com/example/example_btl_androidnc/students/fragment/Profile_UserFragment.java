package com.example.example_btl_androidnc.students.fragment;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.databinding.FragmentProfileUserBinding;
import com.example.example_btl_androidnc.students.addItem.Change_PassWord;
import com.example.example_btl_androidnc.students.addItem.Edit_Profile;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.authentication.LoginActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.ImageHelper;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_UserFragment extends Fragment {
    private MySharedPreferences mySharedPreferences;
    FragmentProfileUserBinding binding;

    private Users users;

    public Profile_UserFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getUserProfile();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySharedPreferences.clearData();
                Log.d("testtoken", " đăng xuất đã xóa token");
                startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                getActivity().getSupportFragmentManager().beginTransaction().remove(Profile_UserFragment.this).commit();

            }
        });
        binding.tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), Edit_Profile.class);
                i.putExtra("user", users);
                startActivityForResult(i, 1);


            }
        });
        binding.btInforChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Change_PassWord.class);
                //Intent intent = new Intent(LoginActivity.this, SetAdmin_Activity.class);
                startActivityForResult(i, 1);
            }
        });
        return view;
    }


    public void getUserProfile() {
        MySharedPreferences mySharedPreferences1 = new MySharedPreferences(getContext());

        String token = mySharedPreferences1.getToken();
        GetAPI_Service getAPI_service = RetrofitClient.getInstance(getContext(), RetrofitClient.BASE_URL, token).create(GetAPI_Service.class);
        ;
        Call<Users> call = getAPI_service.getUserProfile("Bearer " + token);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    users = response.body();


                    binding.nameUser.setText(users.getName());
                    binding.btInforMail.setText(users.getEmail());
                    binding.btInforAddress.setText(users.getAddress());
                    binding.btInforPhone.setText(users.getPhone());
                    if (users.getImage() != null) {
                        Glide.with(binding.imgUserPhoto)
                                .load(BASE_IMG + users.getImage())
                                .into(binding.imgUserPhoto);
                    }

                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(getContext(), "Không thể lấy thông tin người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Xử lý khi có lỗi
//                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireActivity().getApplicationContext();
        mySharedPreferences = new MySharedPreferences(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            getUserProfile();
        }
    }
}