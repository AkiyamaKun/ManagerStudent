package com.example.admin.managerstundent.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.managerstundent.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        ((TextView) findViewById(R.id.txtID)).setText(getIntent().getStringExtra("id"));
        ((TextView) findViewById(R.id.txtName)).setText(getIntent().getStringExtra("name"));
        ImageView img = findViewById(R.id.img);
        Random r = new Random();
        String url = "https://picsum.photos/250/250/?image=" +r.nextInt(200);
        Picasso.with(getApplicationContext()).load(url).into(img);
    }
}
