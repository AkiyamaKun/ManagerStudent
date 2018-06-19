package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
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
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        //Configuration Realm Default
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_timetable) {

        } else if (id == R.id.nav_classmanagement) {

        } else if (id == R.id.nav_studentmanagent) {
            Intent intent = new Intent(this, ManageStudentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_todolist) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void toStudentList(View view) {
        Intent intent = new Intent(this, ListStudentActivity.class);
        startActivity(intent);
    }
}
