package com.example.admin.managerstundent.Activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.alamkanak.weekview.WeekViewLoader;
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
import java.util.Calendar;
import java.util.List;

public class TableActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener{

    private TimeTableView timeTable;
    private WeekView mWeekView;

    private List<String> mTitles = Arrays.asList("Japanese", "English", "Math", "Physics", "Chemistry", "Biology");
    private List<String> mHeaders = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    private String subject[] = {"Math 9", "Math 10", "Chemistry 10", "Physics 11"};
    private Integer colors[] ={R.color.color_table_1_light,R.color.color_table_2_light,R.color.color_table_3_light,R.color.color_table_4_light};
    final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences seision = this.getSharedPreferences("Login", 0);
        SharedPreferences.Editor editor= seision.edit();
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setShowNowLine(true);
        mWeekView.setEventTextSize(20);

        mWeekView.notifyDatasetChanged();
// Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this);

// The week view has infinite scrolling horizontally. We have to provide the events of a
// month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

// Set long press listener for events.
        mWeekView.setEventLongPressListener(this);
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

    private List<WeekViewEvent> getEvents() {
        List<WeekViewEvent> events = new ArrayList<>();
        //Calendar start = Calendar.getInstance();
        //Calendar end = Calendar.getInstance();
        //start.set(2018,7,24,5,30);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm");
        DateTime begindate = DateTime.now().withTimeAtStartOfDay().plusSeconds(3600 * 6);
        for (int j = 0; j < 15; j++) {
            DateTime start = begindate;
            for (int i = 0; i < 4; i++) {
                Calendar startTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
                DateTime end = start.plusSeconds(4800);
                startTime.setTimeInMillis(start.getMillis());
                endTime.setTimeInMillis(end.getMillis());
                Log.d("date","start: " + dtf.print(startTime.getTimeInMillis())+ " End: "+dtf.print(endTime.getTimeInMillis()));
                WeekViewEvent event = new WeekViewEvent(5*i+1,subject[i % 4], startTime,endTime);
                event.setLocation("\n"+subject[i % 4]);
                event.setColor(getResources().getColor(colors[i%4]));
                events.add(event);
                start = end.plusSeconds(900);
            }
            begindate = begindate.plusSeconds(3600 * 24 * 2);
        }
        return events;
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

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = getEvents();
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.set(2018,7,22,3,30);
        endTime.set(2018,7,22,9,30);
        WeekViewEvent event = new WeekViewEvent(999,subject[0], startTime,endTime);
        events.add(event);
        Calendar calendar = Calendar.getInstance();
        if (newMonth == calendar.get(Calendar.MONTH) || newMonth == calendar.get(Calendar.MONTH) + 1 )
            return events;
        else {
            return new ArrayList<WeekViewEvent>();
        }
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Intent intent = new Intent(this, ListStudentActivity.class);
        intent.putExtra("subject", event.getName());
        intent.putExtra("time", sdf.format(new DateTime(event.getStartTime().getTimeInMillis()).toDate()) +
                " - " + sdf.format(new DateTime(event.getEndTime().getTimeInMillis()).toDate()));
        startActivity(intent);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        TastyToast.makeText(TableActivity.this, event.getName() + ", " + sdf.format(new DateTime(event.getStartTime().getTimeInMillis()).toDate()) +
                " - " + sdf.format(new DateTime(event.getEndTime().getTimeInMillis()).toDate()), TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
    }
}

