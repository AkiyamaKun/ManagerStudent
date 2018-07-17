package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;
import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;
import com.sdsmdg.tastytoast.TastyToast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private TimeTableView timeTable;

    private List<String> mTitles = Arrays.asList("Japanese", "English", "Math", "Physics", "Chemistry", "Biology");
    private List<String> mHeaders = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences seision = this.getSharedPreferences("Login", 0);
        SharedPreferences.Editor editor= seision.edit();
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
        timeTable.setStartHour(6);
        initData();
        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        bar.setSelectedItemId(R.id.nav_timetable);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(TableActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        Intent intent2 = new Intent(TableActivity.this, TableActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_studentmanagent:
                        Intent intent3 = new Intent(TableActivity.this, ListStudentActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_todolist:
                        Intent intent4 = new Intent(TableActivity.this, ListClassActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bar);
        bar.getMenu().getItem(1).setChecked(true);
    }

    private void initData(){
        final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
        timeTable.setStartHour(0);
        timeTable.setShowHeader(true);
        timeTable.setTableMode(TimeTableView.TableMode.SHORT);
        DateTime now = DateTime.now();
        long mNow = getMillis("2018-06-29 03:00:00");
       // ArrayList<TimeTableData> dataTimeTable = getSamples(mNow, mHeaders, mTitles);
        ArrayList<TimeTableData> dataTimeTable = initDetailData();
        timeTable.setTimeTable(mNow, dataTimeTable);
        timeTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
            @Override
            public void onTimeItemClick(View view, int position, TimeGridData item) {
                TimeData time = item.getTime();
                TastyToast.makeText(TableActivity.this, time.getTitle() + ", " + sdf.format(new DateTime(time.getStartMills()).toDate()) +
                        " - " + sdf.format(new DateTime(time.getStopMills()).toDate()), TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
//                Toast.makeText(TableActivity.this, time.getTitle() + ", " + sdf.format(new DateTime(time.getStartMills()).toDate()) +
//                        " ~ " + sdf.format(new DateTime(time.getStopMills()).toDate()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<TimeTableData> getSamples(long date, List<String> headers, List<String> titles){
        TypedArray colors_table = getResources().obtainTypedArray(R.array.colors_table);

        ArrayList<TimeTableData> tables = new ArrayList<>();
        for(int i=0; i<headers.size(); i++){
            ArrayList<TimeData> values = new ArrayList<>();
            DateTime start = new DateTime(date);
            DateTime end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            for(int j=0; j<titles.size(); j++){
                int color = colors_table.getResourceId(j, 0);
                int textColor = R.color.black;
                TimeData timeData = new TimeData(j, titles.get(j), color, textColor, start.getMillis(), end.getMillis());
                values.add(timeData);

                start = end.plusMinutes((int)((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            }

            tables.add(new TimeTableData(headers.get(i), values));
        }
        return tables;
    }



    private ArrayList<TimeTableData> initDetailData(){
        ArrayList<TimeTableData> dataGrid = new ArrayList<>();
        ArrayList<TimeData> values = new ArrayList<>();
        values.add(new TimeData(0, "Japanese", R.color.color_table_1_light, getMillis("2018-06-29 11:00:00"), getMillis("2018-06-29 13:00:00")));
        values.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2018-06-29 07:00:00"), getMillis("2018-06-29 09:00:00")));

        ArrayList<TimeData> values2 = new ArrayList<>();
        values.add(new TimeData(0, "Japanese", R.color.color_table_1_light, getMillis("2018-06-29 13:30:00"), getMillis("2018-06-29 15:00:00")));
        values2.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2018-06-29 07:30:00"), getMillis("2018-06-29 09:30:00")));
        values2.add(new TimeData(2, "Math 9", R.color.color_table_3_light, getMillis("2018-06-29 11:40:00"), getMillis("2018-06-29 13:45:00")));
        values2.add(new TimeData(4, "Physics 10", R.color.color_table_5_light, getMillis("2018-06-29 14:00:00"), getMillis("2018-06-29 15:30:00")));
        values.add(new TimeData(5, "Chemistry 11", R.color.color_table_6_light, getMillis("2018-06-29 16:30:00"), getMillis("2018-06-29 18:00:00")));
        values.add(new TimeData(6, "Biology 11", R.color.color_table_7_light, getMillis("2018-06-29 13:30:00"), getMillis("2018-06-29 15:45:00")));

        ArrayList<TimeTableData> tables = new ArrayList<>();
        tables.add(new TimeTableData("Sun", values));
        tables.add(new TimeTableData("Mon", values2));
        tables.add(new TimeTableData("Tue", values));
        tables.add(new TimeTableData("Wed", values2));
        tables.add(new TimeTableData("Thu", values));
        tables.add(new TimeTableData("Fri", values2));
        tables.add(new TimeTableData("Sat", values));
        dataGrid.addAll(tables);
        return dataGrid;
    }
    private long getMillis(String day){
        DateTime date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(day);
        return date.getMillis();
    }

}

