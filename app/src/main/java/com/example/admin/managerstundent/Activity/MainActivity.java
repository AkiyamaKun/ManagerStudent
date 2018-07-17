package com.example.admin.managerstundent.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        if(classes.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                DateTime dt2 = dt.plusSeconds(4800);
                //classes.add(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "   Mon-Wed-Fri"));
                db.addClass(new ClassDTO(subject[i % 4], subject[i % 4], dtf.print(dt) + " - " + dtf.print(dt2) + " AM", "Mon-Wed-Fri"));
                dt = dt2.plusSeconds(900);
            }
        }
        classes = db.findAllClass();
        db.close();
        ListClassAdapter adapter = new ListClassAdapter(classes, this);
        listView.setAdapter(adapter);
//        Picasso.with(MainActivity.this)
//                .load("http://img.youtube.com/vi/32sYGCOYJUM/0.jpg")
//                .placeholder(R.drawable.ic_launcher)
//                .into((ImageView) findViewById(R.id.img));
        //RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        // rippleBackground.startRippleAnimation();
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
                        startActivity(intent2);
                        finish();
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
}
