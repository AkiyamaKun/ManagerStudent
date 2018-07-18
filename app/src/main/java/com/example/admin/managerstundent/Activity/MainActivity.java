package com.example.admin.managerstundent.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.Adapter.ListClassAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        DBAdapter db = new DBAdapter(this);
        db.open();
        classes = db.findAllClass();
        if (classes.isEmpty()) {

                for (int i = 0; i < 4; i++) {
                    DateTime dt2 = dt.plusSeconds(4800);
                    //classes.add(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "   Mon-Wed-Fri"));
                    db.addClass(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "Mon-Wed-Fri"));
                    dt = dt2.plusSeconds(900);
            }
            for (int j = 0; j < 10; j++) {
                    Integer day = 19;
                DateTime begindate =DateTime.now().withTimeAtStartOfDay().plus(3600*5);
                Integer hourS = 6;
                for (int i = 0; i < 4; i++) {
                    Integer hourE = hourS+1;
                        DateTime start = begindate;
                        DateTime end = begindate.plus(4800);
                        Calendar beginTime = Calendar.getInstance();
                        //beginTime.setTimeInMillis(start.getMillis());
                        beginTime.set(2018,7,day++,hourS,0);
                        long startMillis = beginTime.getTimeInMillis();
                        Calendar endTime = Calendar.getInstance();
                    endTime.set(2018,7,day++,hourE,0);
                        //endTime.setTimeInMillis(end.getMillis());
                        long endMillis = endTime.getTimeInMillis();
                    ContentResolver cr = getContentResolver();
                        ContentValues values = new ContentValues();
                        values.put(CalendarContract.Events.DTSTART, startMillis);
                        values.put(CalendarContract.Events.DTEND, endMillis);
                        values.put(CalendarContract.Events.TITLE, subject[i % 4]);
                        values.put(CalendarContract.Events.DESCRIPTION, subject[i % 4]);
                        values.put(CalendarContract.Events.CALENDAR_ID, getCelendarId());
                        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Ho_Chi_Minh");
                        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
                        start = end.plus(900);
                        hourS+=2;
                }
                begindate = begindate.plus(3600*24);
            }
        }
        classes = db.findAllClass();
        db.close();
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, DateTime.now().getMillis());
        values.put(CalendarContract.Events.DTEND, DateTime.now().plus(4800).getMillis());
        values.put(CalendarContract.Events.TITLE, subject[1]);
        values.put(CalendarContract.Events.DESCRIPTION, subject[1]);
        values.put(CalendarContract.Events.CALENDAR_ID, getCelendarId());
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Ho_Chi_Minh");
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        long eventID = Long.parseLong(uri.getLastPathSegment());
        System.out.println("**************************"+uri.getPath()+"eventID"+eventID);
        ListClassAdapter adapter = new ListClassAdapter(classes, this);
        listView.setAdapter(adapter);
        updateCelendar();
        for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers) {
                    Log.d("WTFBRO", "********************provider: " + provider.authority);
                }
            }
        }

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        Intent intent2 = new Intent(MainActivity.this, TableActivity.class);
                        long calID = getCelendarId();
                        Log.d("ID", "***********************"+calID);
                        ContentValues values = new ContentValues();
// The new display name for the calendar
                        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "Minustiktok's Calendar");
                        values.put(CalendarContract.Calendars.CALENDAR_COLOR, getResources().getColor(R.color.colorPrimary));
                        Uri updateUri = ContentUris.withAppendedId(CalendarContract.Calendars.CONTENT_URI, calID);
                        int rows = getContentResolver().update(updateUri, values, null, null);

                        long startMillis = System.currentTimeMillis();
                        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                        builder.appendPath("time");
                        ContentUris.appendId(builder, startMillis);
                        intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
                        startActivity(intent);
                        //startActivity(intent2);
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
//        Intent intent = new Intent(this, ListStudentActivity.class);
//        switch(view.getId()) {
//            case R.id.card1:
//                intent.putExtra("subject", "Math 9");
//                intent.putExtra("time", "5:00 - 6:30 AM");
//                startActivity(intent);
//                break;
//            case R.id.card2:
//                intent.putExtra("subject", "Math 10");
//                intent.putExtra("time", "6:45 - 8:15 AM");
//                startActivity(intent);
//                break;
//            case R.id.card3:
//                intent.putExtra("subject", "Chemistry 10");
//                intent.putExtra("time", "8:30 - 10:00 AM");
//                startActivity(intent);
//                break;
//            case R.id.card4:
//                intent.putExtra("subject", "Physics 11");
//                intent.putExtra("time", "10:15 - 11:45 AM");
//                startActivity(intent);
//                break;
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


    public void updateCelendar() {
        ContentValues values = new ContentValues();
// The new display name for the calendar
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "MinusTiktok's Calendar");
        Uri updateUri = ContentUris.withAppendedId(CalendarContract.Calendars.CONTENT_URI, getCelendarId());
        int rows = getContentResolver().update(updateUri, values, null, null);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2011, 9, 23, 8, 0);
        beginTime.getFirstDayOfWeek();

    }
    public long getCelendarId() {
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"local", "local", "LOCAL"};
        @SuppressLint("MissingPermission")
        Cursor cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        long calID = 0;
        while (cur.moveToNext()) {
            String displayName = null;
            String accountName = null;
            String ownerName = null;
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            calID = cur.getLong(PROJECTION_ID_INDEX);

            Log.d("Account ", "***************************account: "+ displayName + " "+accountName + " " + ownerName + " " + calID+ " "+cur.getString(cur.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE)));
            // Get the field values
        }
        return calID;
    }
}
