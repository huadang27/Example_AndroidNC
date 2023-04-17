package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.students.adapter.ScheduleAdapter;
import com.example.example_btl_androidnc.students.model.UserCourse;
import com.example.example_btl_androidnc.students.model.Users;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.MainActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView dateEditText,nameCourse;

    private Calendar startDate;
    private Calendar endDate;

    ScheduleAdapter adapter;
    private List<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        dateEditText = findViewById(R.id.textView5);
        nameCourse = findViewById(R.id.nameCourse);
        getDate();
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
        String address  = intent.getStringExtra("address");
        String nameCourses = intent.getStringExtra("nameCourse");

        nameCourse.setText("Lịch của môn học: "+nameCourses);

        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Schedule>> call = getAPI_service.getListScheduleByCourse(courseId);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                Log.d("demo123", response.body().toString());
                if (response.isSuccessful()) {
                    schedules = response.body();
                    adapter = new ScheduleAdapter(ScheduleList.this,schedules,address);
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(ScheduleList.this, "Lỗi khi lấy danh sách sinh viên từ server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                Log.d("demo123", t.toString());
            }
        });


    }

    private void showDatePickerDialog() {
        final Calendar now = Calendar.getInstance();
        int mYear = now.get(Calendar.YEAR);
        int mMonth = now.get(Calendar.MONTH);
        int mDay = now.get(Calendar.DAY_OF_MONTH);

        com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog =
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, monthOfYear, dayOfMonth);

                                if (startDate == null) {
                                    startDate = selectedDate;
                                } else if (endDate == null) {
                                    if (selectedDate.compareTo(startDate) < 0) {
                                        endDate = startDate;
                                        startDate = selectedDate;
                                    } else {
                                        endDate = selectedDate;
                                    }
                                } else {
                                    if (selectedDate.compareTo(endDate) < 0) {
                                        startDate = selectedDate;
                                    } else {
                                        endDate = selectedDate;
                                    }
                                }
                                updateDateEditText();
                                handleSelectedDates();
                            }
                        }, mYear, mMonth, mDay);

        if (startDate != null && endDate != null) {
            Calendar[] highlightedDays = {startDate, endDate};
            datePickerDialog.setHighlightedDays(highlightedDays);
        }

        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }


    public void getDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(now.getTime());
        dateEditText.setText(currentDate);
    }

    private void handleSelectedDates() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (startDate != null && endDate != null) {
            String start = sdf.format(startDate.getTime());
            String end = sdf.format(endDate.getTime());
            Log.d("Schedules", "Start Date: " + start + ", End Date: " + end);
            // Lọc và tải danh sách các lịch trình dựa trên startDate và endDate
        } else if (startDate != null) {
            String start = sdf.format(startDate.getTime());
            Log.d("Schedules", "Start Date: " + start);
            // Tải danh sách các lịch trình dựa trên startDate
        } else {
            Log.d("Schedules", "Loading default schedules");
            // Tải danh sách các lịch trình mặc định (nếu cần)
        }
    }
    private void updateDateEditText() {
        if (startDate != null && endDate != null) {
            dateEditText.setText("Từ: " + startDate.get(Calendar.DAY_OF_MONTH) + "/" + (startDate.get(Calendar.MONTH) + 1) + "/" + startDate.get(Calendar.YEAR)
                    + " - Đến: " + endDate.get(Calendar.DAY_OF_MONTH) + "/" + (endDate.get(Calendar.MONTH) + 1) + "/" + endDate.get(Calendar.YEAR));
        } else if (startDate != null) {
            dateEditText.setText("Từ: " + startDate.get(Calendar.DAY_OF_MONTH) + "/" + (startDate.get(Calendar.MONTH) + 1) + "/" + startDate.get(Calendar.YEAR));
        } else {
            // You can handle the case when both startDate and endDate are null here, if needed.
        }
    }


    //        // Hiển thị ngày hiện tại trong EditText
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        Calendar calendar = Calendar.getInstance();
//        String currentDate = dateFormat.format(calendar.getTime());
//        dateEditText.setText(currentDate);
//
//        dateEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int mYear = calendar.get(Calendar.YEAR);
//                int mMonth = calendar.get(Calendar.MONTH);
//                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleList.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                dateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//            }
//        });

}