package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.students.adapter.ScheduleAdapter;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.UserCourse;
import com.example.example_btl_androidnc.students.model.Users;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.MainActivity;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView dateEditText,nameCourse;
    ImageButton imageButton;

    private Calendar startDate;
    private Calendar endDate;

    ScheduleAdapter adapter;
    private List<Schedule> schedules;
    String address;
    String courseId;

    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        dateEditText = findViewById(R.id.textView5);
        nameCourse = findViewById(R.id.nameCourse);
        imageButton = findViewById(R.id.idStudentList);
        mySharedPreferences = new MySharedPreferences(ScheduleList.this);
        getDate();
        Intent intent = getIntent();
        String courseId = intent.getStringExtra("courseId");
         address  = intent.getStringExtra("address");
        String nameCourses = intent.getStringExtra("nameCourse");


       String role =  mySharedPreferences.getRole();
        if (role.equals("ROLE_USER")) {
            imageButton.setVisibility(View.GONE);
        } else if (role.equals("ROLE_TEACHER")) {
            imageButton.setVisibility(View.VISIBLE);
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScheduleList.this, StudentList.class);
                i.putExtra("courseId", courseId);
                startActivity(i);
            }
        });
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



        nameCourse.setText("Lịch của môn học: "+nameCourses);

        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Schedule>> call = getAPI_service.getListScheduleByCourse(courseId);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                Log.d("demo123", response.body().toString());
                if (response.isSuccessful()) {
                    schedules = response.body();
                    Collections.sort(schedules, new ScheduleDayComparator());
                    adapter = new ScheduleAdapter(ScheduleList.this,schedules,address,courseId);
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
                                updateDateEditText(selectedDate);
                                filterSchedulesByDate(selectedDate);
                            }
                        }, mYear, mMonth, mDay);

        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }

    private void filterSchedulesByDate(Calendar selectedDate) {
        List<Schedule> filteredSchedules = new ArrayList<>();

        for (Schedule schedule : schedules) {
            String dateString = convertDateFormat(schedule.getDay());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                Date date = sdf.parse(dateString);
                Calendar scheduleDate = Calendar.getInstance();
                scheduleDate.setTime(date);

                if (scheduleDate.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                        && scheduleDate.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH)
                        && scheduleDate.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH)) {
                    filteredSchedules.add(schedule);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Sắp xếp danh sách lịch theo ngày trước khi đưa vào adapter
        Collections.sort(filteredSchedules, new ScheduleDayComparator());
        adapter = new ScheduleAdapter(ScheduleList.this, filteredSchedules, address,courseId);
        recyclerView.setAdapter(adapter);
    }




    public void getDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(now.getTime());
        dateEditText.setText(currentDate);


    }

    public class ScheduleDayComparator implements Comparator<Schedule> {
        @Override
        public int compare(Schedule s1, Schedule s2) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date1 = sdf.parse(s1.getDay());
                Date date2 = sdf.parse(s2.getDay());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }



    // lọc theo ngày chọn
    private List<Schedule> filterSchedulesByDate(List<Schedule> schedules, String selectedDate) {
        List<Schedule> filteredSchedules = new ArrayList<>();

        for (Schedule schedule : schedules) {
            if (convertDateFormat(schedule.getDay()).equals(selectedDate)) {
                filteredSchedules.add(schedule);
            }
        }
        return filteredSchedules;
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


    private void updateDateEditText(Calendar selectedDate) {
        dateEditText.setText("Ngày: " + selectedDate.get(Calendar.DAY_OF_MONTH) + "/" + (selectedDate.get(Calendar.MONTH) + 1) + "/" + selectedDate.get(Calendar.YEAR));
    }


}