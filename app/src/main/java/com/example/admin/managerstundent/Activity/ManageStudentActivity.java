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

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.Entity.Student;
import com.example.admin.managerstundent.Entity.StudyClass;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class ManageStudentActivity extends AppCompatActivity {

    private static final String[] DATA_HEADER = {"Class", "Subject", "Time", "Days"};
    String subject[] = {"Math 9", "Math 10", "Chemistry 10", "Physics 11"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        TableView tableView = (TableView) findViewById(R.id.tableView);
        tableView.setColumnCount(4);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        columnModel.getColumnWidth(3,2);
        columnModel.getColumnWidth(4,2);
        tableView.setColumnModel(columnModel);
        int colorEvenRows = getResources().getColor(R.color.color_table_2_light);
        int colorOddRows = getResources().getColor(R.color.color_table_6_light);
        tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        tableView.setHeaderBackground(R.color.color_table_6);
        tableView.setHeaderElevation(10);
        List<ClassDTO> classes = new ArrayList<>();
        DBAdapter db = new DBAdapter(this);
        db.open();
        classes = db.findAllClass();
        db.close();
        ClassAdapter classAdapter = new ClassAdapter(this, classes);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, DATA_HEADER));
        tableView.setDataAdapter(classAdapter);
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
                        Intent intent4 = new Intent(ManageStudentActivity.this, ListClassActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bar);
        bar.getMenu().getItem(3).setChecked(true);
        tableView.addDataClickListener(new ClassClickListener());
    }

    public void addClass(View view) {
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }

    public class ClassAdapter extends TableDataAdapter<ClassDTO> {

        public ClassAdapter(Context context, List<ClassDTO> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            ClassDTO classDTO = getRowData(rowIndex);
            View renderedView = null;

            switch (columnIndex) {
                case 0:
                    TextView textView = new TextView(getContext());
                    textView.setText(classDTO.getClassName());
                    renderedView = textView;
                    break;
                case 1:
                    TextView textView1 = new TextView(getContext());
                    textView1.setText(classDTO.getSubject());
                    renderedView = textView1;
                    break;
                case 2:
                    TextView textView2 = new TextView(getContext());
                    textView2.setText(classDTO.getTime());
                    renderedView = textView2;
                    break;
                case 3:
                    TextView textView3 = new TextView(getContext());
                    textView3.setText(classDTO.getWeeksdays());
                    renderedView = textView3;
                    break;
            }

            return renderedView;
        }

    }

    private class ClassClickListener implements TableDataClickListener<ClassDTO> {
        @Override
        public void onDataClicked(int rowIndex, ClassDTO clickedClass) {
            Intent intent = new Intent(ManageStudentActivity.this, ListStudentActivity.class);
            intent.putExtra("subject", clickedClass.getClassName());
            intent.putExtra("time", clickedClass.getTime());
            startActivity(intent);
        }
    }
}
