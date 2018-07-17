package com.example.admin.managerstundent.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.managerstundent.Adapter.StudentChooserAdapter;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class StudentChooseFragment extends DialogFragment {

    String lastName[] = {"Tran", "Le", "Nguyen"};
    String middleName[] = {"Thi", "Van", "Quoc", "Ngoc"};
    String firstName[] = {"Phuong", "Anh", "Luong", "Nam", "Triet"};
    String subject[] = {"Math 9", "Math 10", "Chemistry 10", "Physics 11"};
    private List<StudentDTO> dtos;
    public static Integer num = 0;
    StudentChooserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_choose, container, false);
        getDialog().setTitle("Choose student");
        dtos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            String classname = subject[(i + 2) % 4];
            if(i%5==1) {
                classname +=", " +  subject[(i + 3) % 4];
            }
            dtos.add(new StudentDTO(i, "https://picsum.photos/60/60/?image=" + (i * 50 + 2), lastName[i % 3] + " " + middleName[i % 4] + " " + firstName[i % 5],
                    new Random().nextInt(3) + 15, classname, (i % 4 == 0) ? false : true));
        }
        adapter = new StudentChooserAdapter(dtos, getContext());
        ListView list = rootView.findViewById(R.id.listChoose);
        list.setAdapter(adapter);
        num = 0;
        FancyButton fbAdd = rootView.findViewById(R.id.btnAdd);
        FancyButton fbCancel = rootView.findViewById(R.id.btnCancel);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                num++;
            }
        });
        fbCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentChooseFragment.this.dismiss();
            }
        });
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListStudentActivity) StudentChooseFragment.this.getActivity()).onComplete(num);
                StudentChooseFragment.this.dismiss();
            }
        });
        return rootView;
    }

    public static interface OnCompleteListener {
        public abstract void onComplete(Integer numOfStu);
    }

    private OnCompleteListener mListener;

}
