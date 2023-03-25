package com.example.example_btl_androidnc.Login;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.example_btl_androidnc.AddItem.SetAdmin_Activity;
import com.example.example_btl_androidnc.Fragment.Admin_HomeFragment;
import com.example.example_btl_androidnc.R;

import java.util.Timer;
import java.util.TimerTask;

public class SilentLoginActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silent_login);
        startApplication();
    }

    private void startApplication() {
        Timer RunSplash = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SilentLoginActivity.this, SetAdmin_Activity.class));
                        Toast.makeText(SilentLoginActivity.this, "Chào mừng bạn trở lại !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        };
        RunSplash.schedule(timerTask, SPLASH_TIME_OUT);
    }
}