package com.example.example_btl_androidnc.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MySharedPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String PREF_NAME = "my_preferences";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_COURSEID = "courseId";
    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(String token,String id, String email, String password, String username,String courseId) {
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_ID,id);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_COURSEID,courseId);
        editor.apply();
        Log.d("testtoken", "Lưu dữ liệu thành công");
        Log.d("testtoken", "token: "+getToken() +"\n" +"id: "+getName()
                +"Email: "+ getEmail()+
                " Password: " + getPassword() +
                " Username: " +getUsername() +
                " CourseId: " + getCourseId()
        );

    }
    public void clearData() {
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_PASSWORD);
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_ID);
        editor.remove(KEY_COURSEID);
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
    public String getCourseId(){return  sharedPreferences.getString(KEY_COURSEID,"");}
}
