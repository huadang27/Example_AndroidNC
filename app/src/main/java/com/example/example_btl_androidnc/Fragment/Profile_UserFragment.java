package com.example.example_btl_androidnc.Fragment;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.example_btl_androidnc.AddItem.Edit_Profile;
import com.example.example_btl_androidnc.Authentication.LoginActivity;
import com.example.example_btl_androidnc.R;

public class Profile_UserFragment extends Fragment {
    Context context = getContext();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button Bt_sign_out;
private TextView tv_Edit;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile__user, container, false);

        View view = inflater.inflate(R.layout.fragment_profile__user, container, false);

        Bt_sign_out = view.findViewById(R.id.bt_sign_out);
        tv_Edit = view.findViewById(R.id.tv_edit_profile);
        Bt_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
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
    public void logout() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
        // Chuyển đến màn hình đăng nhập
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}