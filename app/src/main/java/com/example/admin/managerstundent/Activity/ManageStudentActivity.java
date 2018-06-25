package com.example.admin.managerstundent.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.managerstundent.Entity.Student;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class ManageStudentActivity extends AppCompatActivity {

    private static final String[] DATA_HEADER = {"ID", "Student Name", "BirthDay", "Sex"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        TableView tableView = (TableView) findViewById(R.id.tableView);
        tableView.setColumnCount(4);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(1, 2);
        columnModel.setColumnWeight(2, 2);
        tableView.setColumnModel(columnModel);
        int colorEvenRows = getResources().getColor(R.color.color_table_2_light);
        int colorOddRows = getResources().getColor(R.color.colorPrimaryDark);
        tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        tableView.setHeaderElevation(10);
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "Student " + i, new Date(), (i % 2 == 0) ? "Female" : "Male"));
        }
        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, DATA_HEADER));
        tableView.setDataAdapter(studentAdapter);
        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        bar.setSelectedItemId(R.id.nav_todolist);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(ManageStudentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        Intent intent2 = new Intent(ManageStudentActivity.this, TableActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_studentmanagent:
                        Intent intent3 = new Intent(ManageStudentActivity.this, ListStudentActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_todolist:
                        Intent intent4 = new Intent(ManageStudentActivity.this, ManageStudentActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    public class StudentAdapter extends TableDataAdapter<Student> {

        public StudentAdapter(Context context, List<Student> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            Student student = getRowData(rowIndex);
            View renderedView = null;

            switch (columnIndex) {
                case 0:
                    TextView textView = new TextView(getContext());
                    textView.setText(student.getStudentID().toString());
                    renderedView = textView;
                    break;
                case 1:
                    TextView textView1 = new TextView(getContext());
                    textView1.setText(student.getName());
                    renderedView = textView1;
                    break;
                case 2:
                    TextView textView2 = new TextView(getContext());
                    textView2.setText(student.getBirthday().toString());
                    renderedView = textView2;
                    break;
                case 3:
                    TextView textView3 = new TextView(getContext());
                    textView3.setText(student.getSex());
                    renderedView = textView3;
                    break;
            }

            return renderedView;
        }

    }

}
