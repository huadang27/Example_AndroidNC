package com.example.example_btl_androidnc.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.adapter.ListCourseAdapter;
import com.example.example_btl_androidnc.database.MySharedPreferences;
import com.example.example_btl_androidnc.model.courseLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link List_CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List_CourseFragment extends Fragment {

    private MySharedPreferences mySharedPreferences;
    private RecyclerView mRecyclerView;
    private List<courseLists> mList;

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
        Log.d("tets", courseId);
        if (!courseId.isEmpty()) {
            mList = getCourseListInfoFromString(courseId);
        }

        // Lọc danh sách dựa trên status
        List<courseLists> filteredList = new ArrayList<>();
        for (courseLists item : mList) {
            if (item.getStatus() == 1) {
                filteredList.add(item);
            }
        }

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ListCourseAdapter(getContext(), filteredList));
        return view;

    }

    public static List<courseLists> getCourseListInfoFromString(String data) {
        String regex = "courseLists\\{course_id='(.*?)', enroll_date='(.*?)', status=(\\d)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<courseLists> courseListInfos = new ArrayList<>();
        while (matcher.find()) {
            String id = matcher.group(1);
            int status = Integer.parseInt(matcher.group(3));
            courseListInfos.add(new courseLists(id, status));
        }
        return courseListInfos;
    }


}