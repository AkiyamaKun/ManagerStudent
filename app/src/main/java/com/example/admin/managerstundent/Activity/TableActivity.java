package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.managerstundent.R;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
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
                        Intent intent4 = new Intent(TableActivity.this, ManageStudentActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void initData(){
        final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
        timeTable.setStartHour(0);
        timeTable.setShowHeader(true);
        timeTable.setTableMode(TimeTableView.TableMode.SHORT);
        DateTime now = DateTime.now();
        long mNow = now.withTimeAtStartOfDay().getMillis();
        ArrayList<TimeTableData> dataTimeTable = getSamples(mNow, mHeaders, mTitles);
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
        TypedArray colors_table_light = getResources().obtainTypedArray(R.array.colors_table_light);

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
        values.add(new TimeData(0, "Japanese", R.color.color_table_1_light, getMillis("2018-05-27 04:00:00"), getMillis("2018-05-27 05:00:00")));
        values.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2018-05-27 07:00:00"), getMillis("2018-05-27 08:00:00")));

        ArrayList<TimeData> values2 = new ArrayList<>();
        values2.add(new TimeData(0, "Japanese", R.color.color_table_1_light, getMillis("2018-05-27 03:00:00"), getMillis("2018-05-27 06:00:00")));
        values2.add(new TimeData(1, "English", R.color.color_table_2_light, getMillis("2018-05-27 07:30:00"), getMillis("2018-05-27 08:30:00")));
        values2.add(new TimeData(2, "Math", R.color.color_table_3_light, getMillis("2018-05-27 11:40:00"), getMillis("2018-05-27 11:45:00")));
        values2.add(new TimeData(4, "Physics", R.color.color_table_5_light, getMillis("2018-05-27 20:00:00"), getMillis("2018-05-27 21:30:00")));
        values2.add(new TimeData(5, "Chemistry", R.color.color_table_6_light, getMillis("2018-05-27 21:31:00"), getMillis("2018-05-27 22:45:00")));
        values2.add(new TimeData(6, "Biology", R.color.color_table_7_light, getMillis("2018-05-27 23:00:00"), getMillis("2018-05-28 02:30:00")));

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

