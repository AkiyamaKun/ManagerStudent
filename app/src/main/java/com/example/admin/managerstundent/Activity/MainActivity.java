package com.example.admin.managerstundent.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.Adapter.ListClassAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author: DangNHH
 * 19/05/2018
 * <p>
 * Main Activity Class
 */
public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    private ListView listView;
    private TextView date;
    private String subject[] = {"Math 9", "Math 10", "Chemistry 10", "Physics 11"};
    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT,
            CalendarContract.Calendars.ACCOUNT_TYPE// 3
    };
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    private static final Integer[] colors = {3, 4, 5, 6};

    private Uri calUri;

    /**
     * Override On Create
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        bar.setSelectedItemId(R.id.nav_dashboard);
        listView = findViewById(R.id.list);
        date = findViewById(R.id.date);
        List<ClassDTO> classes = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm");
        DateTime dt = dtf.parseDateTime("5:00");

        long calID = getCelendarId();
        Uri updateUri = ContentUris.withAppendedId(CalendarContract.Calendars.CONTENT_URI, calID);
        calUri = updateUri;
        DBAdapter db = new DBAdapter(this);
        db.open();
        DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm");
        DateTime end2 = format.parseDateTime("20/07/2018 05:00");

        classes = db.findAllClass();
        if (classes.isEmpty()) {
            deleteEvents();
            for (int i = 0; i < 4; i++) {
                DateTime dt2 = dt.plusSeconds(4800);
                //classes.add(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "   Mon-Wed-Fri"));
                db.addClass(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "Mon-Wed-Fri"));
                dt = dt2.plusSeconds(900);
            }
            DateTime begindate = DateTime.now().withTimeAtStartOfDay().plusSeconds(3600 * 6);
            for (int j = 0; j < 15; j++) {
                DateTime start = begindate;
                for (int i = 0; i < 4; i++) {
                    DateTime end = start.plusSeconds(4800);
                    //beginTime.set(2018,7,day++,hourS,0);
                    //endTime.set(2018,7,day++,hourE,0);
                    Log.d("Time", "Start time: " + format.print(start) + " end time: " + format.print(end));
                    ContentResolver cr = getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put(CalendarContract.Events.DTSTART, start.getMillis());
                    values.put(CalendarContract.Events.DTEND, end.getMillis());
                    values.put(CalendarContract.Events.TITLE, subject[i % 4]);
                    values.put(CalendarContract.Events.DESCRIPTION, subject[i % 4]);
                    values.put(CalendarContract.Events.CALENDAR_ID, colors[i % 4]);
                    values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Ho_Chi_Minh");
                    @SuppressLint("MissingPermission") Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
                    start = end.plusSeconds(900);
                }
                begindate = begindate.plusSeconds(3600 * 24 * 2);
            }
        }
        classes = db.findAllClass();
        db.close();
        ListClassAdapter adapter = new ListClassAdapter(classes, this);
        listView.setAdapter(adapter);
        updateCelendar();

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(MainActivity.this, TableActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        long startMillis = System.currentTimeMillis();
                        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                        builder.appendPath("time");
                        ContentUris.appendId(builder, startMillis);
                        intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
                        startActivity(intent);
                        break;
                    case R.id.nav_studentmanagent:
                        Intent intent3 = new Intent(MainActivity.this, ListStudentActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_todolist:
                        Intent intent4 = new Intent(MainActivity.this, ListClassActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bar);
        bar.getMenu().getItem(0).setChecked(true);
        //Configuration Realm Default
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }

    /**
     * Excute when user click on button "Menu"
     *
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Excute when user click on button "Back" on the Phone
     */


    /**
     * Excute when user click on button "Add Student" on Dashboard
     *
     * @param view
     */
    public void addStudent(View view) {
        Intent intent = new Intent(this, ListClassActivity.class);
        startActivity(intent);
    }

    public void viewTimeTable(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }


    public void toStudentList(View view) {
        Intent intent = new Intent(this, ListStudentActivity.class);
        startActivity(intent);
    }

    public void toClass(View view) {
//        }
    }

    public void changeDate(View view) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd MMMM, yyyy");
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateTime dt = new DateTime(year, month, dayOfMonth, 0, 0, 0);
                date.setText(dtf.print(dt));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        dpd.show();
    }

    public void addClass(View view) {
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    public void deleteEvents() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr
                .query(CalendarContract.Events.CONTENT_URI,
                        new String[]{CalendarContract.Events._ID, CalendarContract.Events.CUSTOM_APP_PACKAGE},
                        null, null, null);
        cursor.moveToFirst();

        String idsToDelete = "";
        for (int i = 0; i < cursor.getCount(); i++) {
            // it might be also smart to check CALENDAR_ID here
            idsToDelete += String.format("_ID = %s OR ", cursor.getString(0));

            cursor.moveToNext();
        }

        if (idsToDelete.endsWith(" OR ")) {
            idsToDelete = idsToDelete.substring(0, idsToDelete.length() - 4);
        }

        cr.delete(CalendarContract.Events.CONTENT_URI, idsToDelete, null);

    }

    public void updateCelendar() {
    }

    public long getCelendarId() {
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"minustiktok@gmail.com", "com.google", "minustiktok@gmail.com"};
        @SuppressLint("MissingPermission")
        Cursor cur = cr.query(uri, EVENT_PROJECTION, null, null, null);

        long calID = 0;
        while (cur.moveToNext()) {
            String displayName = null;
            String accountName = null;
            String ownerName = null;
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            calID = cur.getLong(PROJECTION_ID_INDEX);

            Log.d("Account ", "***************************account: " + displayName + " " + accountName + " " + ownerName + " " + calID + " " + cur.getString(cur.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE)));
            // Get the field values
        }
        return calID;
    }
}
