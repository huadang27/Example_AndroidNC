package com.example.example_btl_androidnc.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.example_btl_androidnc.addItem.RegisterCourseActivity;
import com.example.example_btl_androidnc.model.Course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegisterCourseController {
    private RegisterCourseActivity registerCourseActivity;

    public RegisterCourseController(RegisterCourseActivity registerCourseActivity) {
        this.registerCourseActivity = registerCourseActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadCourseData(Course  course){
        registerCourseActivity.setName(course.getName());
        registerCourseActivity.setInforClass(course.getDescription());
        registerCourseActivity.setImage(course.getImage());
        registerCourseActivity.setPrice(course.getPrice());
//        registerCourseActivity.setTeacher(course.getTeacher());

        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            LocalDate startDate = LocalDate.parse(course.getPublishedAt(), inputFormat);
            LocalDate endDate = LocalDate.parse(course.getExpiredAt(), inputFormat);

            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            registerCourseActivity.setInforDateStart(startDate.format(outputFormat));
            registerCourseActivity.setInforDateEnd(endDate.format(outputFormat));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

}
