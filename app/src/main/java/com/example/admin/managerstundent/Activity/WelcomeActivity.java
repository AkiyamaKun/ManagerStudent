package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.admin.managerstundent.R;

/**
 * Author: QuyetNP
 * 25/05/2018
 *
 * Welcome Screen of App
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final int SWITCH_SCREEN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SWITCH_SCREEN);
    }
}
