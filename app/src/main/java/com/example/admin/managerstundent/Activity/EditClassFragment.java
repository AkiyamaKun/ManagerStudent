package com.example.admin.managerstundent.Activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.managerstundent.CommonAction;
import com.example.admin.managerstundent.R;

import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

public class EditClassFragment extends DialogFragment implements CommonAction {
    private EditText name, subject, time, time1, days;
    private FancyButton fbEdit,fbCancel;
    private TextView ampm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_class, container, false);
        getDialog().setTitle("Edit class");
        TextView txtTYitle = rootView.findViewById(R.id.txtTitleStudent);
        txtTYitle.setText("Edit class");
        name = rootView.findViewById(R.id.edit_name);
        name.setText("Math 10");
        subject = rootView.findViewById(R.id.edit_subject);
        subject.setText("Math 10");
        time = rootView.findViewById(R.id.edit_time);
        time.setText("1:30");
        time1 = rootView.findViewById(R.id.edit_time1);
        time1.setText("3:00");
        days = rootView.findViewById(R.id.edit_days);
        days.setText("Mon-Wed-Fri");
        ampm = rootView.findViewById(R.id.ampm);
        fbEdit = rootView.findViewById(R.id.btnReset);
        fbEdit.setText("Edit");
        fbCancel = rootView.findViewById(R.id.btnCancel);
        fbCancel.setText("Cancel");
        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWeekdays(v);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDate(v);
            }
        });
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDate(v);
            }
        });
        fbEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToCancel(v);
            }
        });
        fbCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToCancel(v);
            }
        });
        final Calendar cal = Calendar.getInstance();

        return rootView;
    }

    public void changeDate(View view) {
        final View txt = view;
        final Calendar cal = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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
        EditClassFragment.this.dismiss();
    }


    @Override
    public void changeWeekdays(String weekdays) {
        if(weekdays.length()>3) {
            days.setText(weekdays.substring(1,weekdays.length()));
        }
    }

    public void changeWeekdays(View view) {
        WeekdaysChooserFragment fragment = new WeekdaysChooserFragment();
        fragment.show(getActivity().getSupportFragmentManager(),"Weekdays");
    }
}
