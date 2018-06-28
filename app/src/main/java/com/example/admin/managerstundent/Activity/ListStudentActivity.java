package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.admin.managerstundent.Adapter.StudentAdapter;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity implements Filter.FilterListener {
    SwipeMenuListView listView;
    StudentAdapter adapter;
    SearchView searchView;
    List<StudentDTO> dtos;
    String lastName[] = {"Tran", "Le", "Nguyen"};
    String middleName[] = {"Thi", "Van", "Quoc", "Ngoc"};
    String firstName[] = {"Phuong", "Anh", "Luong", "Nam", "Triet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.seek_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((Filterable)adapter).getFilter().filter(searchView.getQuery(), ListStudentActivity.this);
                return false;
            }
        });

        dtos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            dtos.add(new StudentDTO(i,"https://picsum.photos/70/70/?image="+(i*50+1), lastName[i%3] + " " + middleName[i%4] + " " + firstName[i%5],
                    (i%3==1)? 9 : 11, (i%3==2)? "10A" : "11B"));
        }
        adapter = new StudentAdapter(dtos, ListStudentActivity.this);
        adapter.setDtos(dtos);
        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        bar.setSelectedItemId(R.id.nav_studentmanagent);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(ListStudentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        Intent intent2 = new Intent(ListStudentActivity.this, TableActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_studentmanagent:
                        Intent intent3 = new Intent(ListStudentActivity.this, ListStudentActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_todolist:
                        Intent intent4 = new Intent(ListStudentActivity.this, ManageStudentActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.WHITE));
                openItem.setIcon(R.drawable.ic_edit);
                openItem.setWidth(90);
                menu.addMenuItem(openItem);
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.WHITE));
                deleteItem.setIcon(R.drawable.ic_delete);
                deleteItem.setWidth(90);
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent intent = new Intent(ListStudentActivity.this, StudentDetailActivity.class);
                        intent.putExtra("id", dtos.get(position).getId().toString());
                        intent.putExtra("name", dtos.get(position).getName().toString());
                        startActivity(intent);
                        break;
                    case 1:
                        dtos.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }

    public void addStudent(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFilterComplete(int count) {

    }
}
