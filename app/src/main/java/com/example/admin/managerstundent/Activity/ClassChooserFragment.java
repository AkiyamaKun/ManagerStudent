package com.example.admin.managerstundent.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.managerstundent.Adapter.ClassChooserAdapter;
import com.example.admin.managerstundent.Adapter.WeekdaysAdapter;
import com.example.admin.managerstundent.CommonAction;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class ClassChooserFragment extends DialogFragment {
    private static final String weekdays[] = {"Math 9","Math 10","Chemistry 11","Physics 11"};
    public static String days = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_choose, container, false);
        getDialog().setTitle("Choose classes");
        final List<String> dtos = new ArrayList<>();
        for (int i = 0; i < weekdays.length; i++) {
            dtos.add(weekdays[i]);
        }
        days = "";
        ClassChooserAdapter adapter = new ClassChooserAdapter(dtos, getContext());
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
                ClassChooserFragment.this.dismiss();
            }
        });
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fragment days"+days);
                try {
                    ((CommonAction) ClassChooserFragment.this.getActivity()).changeWeekdays(days);
                } catch (Exception e) {

                } finally {
                    ClassChooserFragment.this.dismiss();
                }
            }
        });
        return rootView;
    }

}
