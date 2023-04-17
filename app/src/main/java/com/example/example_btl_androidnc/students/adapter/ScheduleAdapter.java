package com.example.example_btl_androidnc.students.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private Context context;
    private List<Schedule> schedules;
    private String address;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public ScheduleAdapter(Context context, List<Schedule> schedules, String address) {
        this.context = context;
        this.schedules = schedules;
        this.address = address;

    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int i) {

        Schedule schedule = schedules.get(i);


        if (schedule.getStatus() == 0) {
            holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_red);
            holder.tvHienLich.setText("Được nghỉ");

        } else {
            holder.tvHienLich.setBackgroundResource(R.drawable.custom_button_green);
            holder.tvHienLich.setText("Đi học");
        }
        holder.tvBuoiHoc.setText(String.valueOf(i + 1));
        holder.tvThu.setText(schedule.getDayOfWeek());
        holder.tvNgay.setText(schedule.getDay());
        holder.tvGio.setText(schedule.getDuration());
        holder.tvDiaChi.setText(address);

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit is Clicked" +schedule.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Deleted is Clicked"+ schedule.getDay(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

     class ScheduleViewHolder extends RecyclerView.ViewHolder {

        TextView tvBuoiHoc, tvThu, tvNgay, tvGio, tvDiaChi,txtDelete,txtEdit;
        TextView tvHienLich;
        LinearLayout item;



        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBuoiHoc = itemView.findViewById(R.id.tvBuoiHoc);
            tvThu = itemView.findViewById(R.id.tvThu);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvGio = itemView.findViewById(R.id.tvGio);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            item = itemView.findViewById(R.id.item);
            tvHienLich = itemView.findViewById(R.id.txtHienThi);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtEdit = itemView.findViewById(R.id.txtEdit);
            // Handling the click events on the txtviews



        }
    }
}
