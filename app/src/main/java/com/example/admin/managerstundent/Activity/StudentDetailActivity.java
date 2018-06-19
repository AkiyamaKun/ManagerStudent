package com.example.admin.managerstundent.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.managerstundent.R;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        ((TextView) findViewById(R.id.txtID)).setText(getIntent().getStringExtra("id"));
        ((TextView) findViewById(R.id.txtName)).setText(getIntent().getStringExtra("name"));
    }
}
