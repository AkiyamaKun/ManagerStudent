package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        ((TextView) findViewById(R.id.txtUsername)).setText(getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.txtClass)).setText(getIntent().getStringExtra("class"));
        ImageView img = findViewById(R.id.imageViewUser);
        ImageView imgPaid = findViewById(R.id.imgPaid);
        ImageView imgNotPaid = findViewById(R.id.imgNotPaid);
        boolean paid = getIntent().getBooleanExtra("paid", true);
        if (paid) {
            imgPaid.setVisibility(View.VISIBLE);
        } else {
            imgNotPaid.setVisibility(View.VISIBLE);
        }
        Random r = new Random();
        String url = "https://picsum.photos/250/250/?image=" + r.nextInt(200);
        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);
    }

    public void editStudent(View view) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        intent.putExtra("name", ((TextView) findViewById(R.id.txtUsername)).getText());
        intent.putExtra("phone", ((TextView) findViewById(R.id.txtPhone)).getText());
        intent.putExtra("birthday",((TextView) findViewById(R.id.txtBirthday)).getText());
        intent.putExtra("gender", ((TextView) findViewById(R.id.txtGender)).getText());
        intent.putExtra("class", ((TextView) findViewById(R.id.txtClass)).getText());
        startActivity(intent);
    }
}
