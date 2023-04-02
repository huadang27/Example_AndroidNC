package com.example.example_btl_androidnc.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.adapter.ListCourseAdapter;
import com.example.example_btl_androidnc.database.MySharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link List_CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List_CourseFragment extends Fragment {

    private MySharedPreferences mySharedPreferences;
    private RecyclerView mRecyclerView;
    private List<String> mList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public List_CourseFragment() {

    }


    public static List_CourseFragment newInstance(String param1, String param2) {
        List_CourseFragment fragment = new List_CourseFragment();
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
        Context context = requireActivity().getApplicationContext();
        mySharedPreferences = new MySharedPreferences(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_course, container, false);
        mList = new ArrayList<>();

        String courseId = mySharedPreferences.getCourseId();
        if (!courseId.isEmpty()) {
            mList = getListFromString(courseId);
        }

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ListCourseAdapter(getContext(),mList));
        return view;

    }

    public static List<String> getListFromString(String data) {
        data = data.replaceAll("\\[|\\]", ""); // Loại bỏ các ký tự [ và ]
        String[] array = data.split(", ");
        return Arrays.asList(array); // Chuyển mảng thành List
    }



}