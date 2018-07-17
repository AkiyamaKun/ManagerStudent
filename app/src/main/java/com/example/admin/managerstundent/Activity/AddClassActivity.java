package com.example.admin.managerstundent.Activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.CommonAction;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.R;

import java.util.Calendar;

public class AddClassActivity extends AppCompatActivity implements CommonAction {

    private EditText name, subject, time, time1, days;
    private TextView ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        name = findViewById(R.id.edit_name);
        subject = findViewById(R.id.edit_subject);
        time = findViewById(R.id.edit_time);
        time1 = findViewById(R.id.edit_time1);
        days = findViewById(R.id.edit_days);
        ampm = findViewById(R.id.ampm);
        final Calendar cal = Calendar.getInstance();
//        time.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                changeDate(v);
//            }
//        });
//        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                changeDate(v);
//            }
//        });
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeDate(v);
//            }
//        });
//        time1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimePickerDialog dialog = new TimePickerDialog(AddClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        String suffix = "AM";
//                        if(hourOfDay >= 12) {
//                            suffix = "PM";
//                            hourOfDay-=12;
//                        }
//                        time1.setText(hourOfDay+":"+minute+suffix);
//                    }
//                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);
//            }
//        });
    }

    public void addClass(View view) {
        ClassDTO dto = new ClassDTO(name.getText().toString(), subject.getText().toString(), time.getText().toString() + " - " + time1.getText().toString() + " " + ampm.getText().toString(), days.getText().toString());
        DBAdapter db = new DBAdapter(this);
        db.open();
        db.addClass(dto);
        db.close();
        Intent intent = new Intent(this, ListClassActivity.class);
        startActivity(intent);
        finish();
    }

    public void changeDate(View view) {
        final View txt = view;
        final Calendar cal = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(AddClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String suffix = "AM";
                if (hourOfDay >= 12) {
                    suffix = "PM";
                    hourOfDay -= 12;
                    ampm.setText(suffix);
                }
                if (txt.getId() == R.id.edit_time) {
                    time.setText(hourOfDay + ":" + minute);
                } else if (txt.getId() == R.id.edit_time1) {
                    time1.setText(hourOfDay + ":" + minute);
                }
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
        dialog.show();
    }

    public void clickToCancel(View view) {
        Intent intent = new Intent(this, ListClassActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void changeWeekdays(String weekdays) {
        if(weekdays.length()>3) {
            days.setText(weekdays.substring(1,weekdays.length()));
        }
    }

    public void changeWeekdays(View view) {
        WeekdaysChooserFragment fragment = new WeekdaysChooserFragment();
        fragment.show(getSupportFragmentManager(),"Weekdays");
    }
}
