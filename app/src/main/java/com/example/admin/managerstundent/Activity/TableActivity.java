package com.example.admin.managerstundent.Activity;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.managerstundent.R;
import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private TimeTableView timeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
        initData();
    }

    private void initData(){
        timeTable = (TimeTableView)findViewById(R.id.timetabledummy);
        timeTable.setStartHour(0);
        timeTable.setShowHeader(true);
        timeTable.setTableMode(TimeTableView.TableMode.SHORT);
        DateTime now = DateTime.now();
        long mNow = now.withTimeAtStartOfDay().getMillis();
        ArrayList<TimeTableData> dataTimeTable = initDetailData();
        timeTable.setTimeTable(mNow, dataTimeTable);
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

