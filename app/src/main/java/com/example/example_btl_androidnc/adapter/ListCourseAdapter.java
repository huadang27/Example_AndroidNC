package com.example.example_btl_androidnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.addItem.StudentList;

import java.util.List;

public class ListCourseAdapter extends RecyclerView.Adapter<ListCourseAdapter.ListViewHolder> {

    private List<String> mList;
    private Context context;


    public ListCourseAdapter(Context context, List<String> mList) {
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
        String item = mList.get(position);
        holder.textView.setText(item);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, StudentList.class);
                i.putExtra("courseId",item);
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
