package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.admin.managerstundent.R;

/**
 * Author: QuyetNP
 * 25/05/2018
 *
 * A login screen that offers login via user/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void checkLogin(View view) {
        EditText editUser = (EditText) findViewById(R.id.email);
        EditText editPass = (EditText) findViewById(R.id.password);
        if(editUser.getText().toString().equals("admin") && editPass.getText().toString().equals("123")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

