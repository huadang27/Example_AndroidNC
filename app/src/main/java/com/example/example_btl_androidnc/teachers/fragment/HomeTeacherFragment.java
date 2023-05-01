package com.example.example_btl_androidnc.teachers.fragment;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTeacherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTeacherFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
private TextView name_teacher;
    private ImageView img_teacher_photo;
    private Users users;
    public HomeTeacherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeTeacherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTeacherFragment newInstance(String param1, String param2) {
        HomeTeacherFragment fragment = new HomeTeacherFragment();
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
      //  return inflater.inflate(R.layout.fragment_home_teacher_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_home_teacher_fragment, container, false);
        img_teacher_photo = view.findViewById(R.id.img_teacher_photo);
        name_teacher = view.findViewById(R.id.name_teacher);
        return view;
    }

    public void getUserProfile() {
        MySharedPreferences mySharedPreferences1 = new MySharedPreferences(getContext());

        String token = mySharedPreferences1.getToken();
        GetAPI_Service getAPI_service = RetrofitClient.getInstance(getContext(), RetrofitClient.BASE_URL, token).create(GetAPI_Service.class);;
        Call<Users> call = getAPI_service.getUserProfile("Bearer " + token);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    name_teacher.setText(users.getName());
                    if (users.getImage()!=null){
                        Glide.with(img_teacher_photo.getContext())
                                .load(BASE_IMG + users.getImage())
                                .into(img_teacher_photo);
                    }

                } else {

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
}