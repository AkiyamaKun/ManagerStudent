package com.example.admin.managerstundent.Activity;

import android.content.Intent;
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

import com.example.admin.managerstundent.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author: DangNHH
 * 19/05/2018
 * <p>
 * Main Activity Class
 */
public class MainActivity extends AppCompatActivity  {

    private ActionBarDrawerToggle mToggle;

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
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
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
                        Intent intent4 = new Intent(MainActivity.this, ManageStudentActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
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
        Intent intent = new Intent(this, ManageStudentActivity.class);
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
}
