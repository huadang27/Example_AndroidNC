package com.example.example_btl_androidnc.students.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class MySharedPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public static final String FAILED_LOGIN_ATTEMPTS = "FAILED_LOGIN_ATTEMPTS";
    private static final String PREF_NAME = "my_preferences";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "username";

    private static final String IMAGE = "image";
    private static final String KEY_COURSE_DATA = "courseData";
    private static final String KEY_NOTIFICATION = "notification";
    private static  final  String ROLE ="role";
    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public int getFailedLoginAttempts() {
        return sharedPreferences.getInt(FAILED_LOGIN_ATTEMPTS, 0);
    }
    public void saveFailedLoginAttempts(int attempts) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(FAILED_LOGIN_ATTEMPTS, attempts);
        editor.apply();
    }
    public void saveData(String token,String id, String email, String password, String username,String role,String image) {
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_ID,id);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_USERNAME, username);
        editor.putString(ROLE,role);
        editor.putString(IMAGE,image);

        editor.apply();
        Log.d("testtoken", "Lưu dữ liệu thành công");
        Log.d("testtoken", "token: "+getToken() +"\n" +"id: "+getName()
                +" Email: "+ getEmail()+
                " Password: " + getPassword() +
                " Username: " +getUsername() +
                "Role: "+ getRole()
        );

    }

    public void savePassword(String newPassword) {
        editor.putString(KEY_PASSWORD, newPassword);
        editor.apply();
        Log.d("testtoken", "Lưu mật khẩu thành công khi đổi mật khẩu");
    }

    public void saveToken(String notification) {
        editor.putString(KEY_NOTIFICATION, notification);
        editor.apply();
        Log.d("testtoken", "Lưu KeyAPI thành công");
    }

    public void saveAvatarUrl(String avatarUrl) {
        editor.putString(IMAGE, avatarUrl);
        editor.apply();
    }


    public void clearData() {
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_PASSWORD);
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_ID);
        editor.apply();
    }
    public HashMap<String, Integer> getCourseDataFromSharedPreferences() {
        Gson gson = new Gson();
        String jsonCourseData = sharedPreferences.getString(KEY_COURSE_DATA, null);
        Type type = new TypeToken<HashMap<String, Integer>>() {}.getType();
        HashMap<String, Integer> courseData = gson.fromJson(jsonCourseData, type);

        return courseData == null ? new HashMap<>() : courseData;
    }


    public void saveCourseDataToSharedPreferences(List<String> courseIds, List<Integer> statuses) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        HashMap<String, Integer> courseData = new HashMap<>();
        for (int i = 0; i < courseIds.size(); i++) {
            courseData.put(courseIds.get(i), statuses.get(i));
        }
        String jsonCourseData = gson.toJson(courseData);
        editor.putString(KEY_COURSE_DATA, jsonCourseData);
        editor.apply();
    }
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }
    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }
    public String getName(){
        return sharedPreferences.getString(KEY_ID,"");
    }

    public  String getKeyToken(){
        return sharedPreferences.getString(KEY_NOTIFICATION,"");
    }

    public String getImage() {
        return sharedPreferences.getString(IMAGE, "");
    }

    public String getRole() {
        return sharedPreferences.getString(ROLE, "");
    }




}
