package com.example.example_btl_androidnc.students.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.StudentList;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.students.model.courseLists;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCourseAdapter extends RecyclerView.Adapter<ListCourseAdapter.ListViewHolder> {

    private List<courseLists> mList;
    private Context context;


    public ListCourseAdapter(Context context, List<courseLists> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tets, parent, false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        courseLists item = mList.get(position);
//        holder.textView.setText("ID: " + item.getCourse_id()+ ", Status: " + item.getStatus());
            Log.d("tetsh", item.getCourse_id());
        GetAPI_Service courseApi = RetrofitClient.getClient().create(GetAPI_Service.class);

        Call<Course> call = courseApi.getCourseById(item.getCourse_id());
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    Log.d("test", response.toString());
                    // Xử lý dữ liệu trả về từ Spring Boot
                    Course course = response.body();
                    holder.textView.setText("ID: " + course.getId()+ ", Status: " + course.getStatus());
                } else {
                    // Xử lý lỗi nếu có
                    Log.e("ListCourseAdapter", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                // Xử lý lỗi nếu có
                Log.e("ListCourseAdapter", "Error: " + t.getMessage());
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, StudentList.class);
                i.putExtra("courseId", item.getCourse_id());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
