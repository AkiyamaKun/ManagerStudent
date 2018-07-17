package com.example.admin.managerstundent.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.managerstundent.Adapter.StudentChooserAdapter;
import com.example.admin.managerstundent.Adapter.WeekdaysAdapter;
import com.example.admin.managerstundent.CommonAction;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class WeekdaysChooserFragment extends DialogFragment {
    private static final String weekdays[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public static String days = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_choose, container, false);
        getDialog().setTitle("Choose weekdays");
        final List<String> dtos = new ArrayList<>();
        for (int i = 0; i < weekdays.length; i++) {
           dtos.add(weekdays[i]);
        }
        days = "";
        WeekdaysAdapter adapter = new WeekdaysAdapter(dtos, getContext());
        ListView list = rootView.findViewById(R.id.listChoose);
        list.setAdapter(adapter);
        FancyButton fbAdd = rootView.findViewById(R.id.btnAdd);
        FancyButton fbCancel = rootView.findViewById(R.id.btnCancel);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                days += dtos.get(position).substring(0,3);
            }
        });
        fbCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekdaysChooserFragment.this.dismiss();
            }
        });
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fragment days"+days);
                try {
                    ((CommonAction) WeekdaysChooserFragment.this.getActivity()).changeWeekdays(days);
                } catch (Exception e) {

                } finally {
                    WeekdaysChooserFragment.this.dismiss();
                }
            }
        });
        return rootView;
    }


}
