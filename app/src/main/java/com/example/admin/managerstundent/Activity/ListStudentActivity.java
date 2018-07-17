package com.example.admin.managerstundent.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.admin.managerstundent.Adapter.StudentAdapter;
import com.example.admin.managerstundent.Adapter.StudentChooserAdapter;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class ListStudentActivity extends AppCompatActivity implements Filter.FilterListener, StudentChooseFragment.OnCompleteListener {
    SwipeMenuListView listView;
    StudentAdapter adapter;
    SearchView searchView;
    List<StudentDTO> dtos;
    private AlertDialog.Builder alertBuilder;
    String lastName[] = {"Tran", "Le", "Nguyen"};
    String middleName[] = {"Thi", "Van", "Quoc", "Ngoc"};
    String firstName[] = {"Phuong", "Anh", "Luong", "Nam", "Triet"};
    String subject[] = {"Math 9", "Math 10", "Chemistry 10", "Physics 11"};
    private boolean up = false;
    String className = null;
    FancyButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.seek_bar);
        TextView txt = findViewById(R.id.subject);
        fb= findViewById(R.id.btnShow);
        FloatingSearchView fsearchView = findViewById(R.id.floating_search_view);
        fsearchView.setDimBackground(false);
        fsearchView.clearSearchFocus();
        fsearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.name:
                        if(className == null) {
                            Intent intent = new Intent(ListStudentActivity.this, AddStudentActivity.class);
                            startActivity(intent);
                        } else {
                            StudentChooseFragment fragment = new StudentChooseFragment();
                            fragment.show(getSupportFragmentManager(), "Choose student!");
                        }
                        break;
                }
            }
        });
        className = getIntent().getStringExtra("subject");

        if (className != null) {
            ((TextView) findViewById(R.id.txt)).setText("Class: " + className.substring(0, className.indexOf(" ")));
            ((TextView) findViewById(R.id.time)).setText("Time: " + getIntent().getStringExtra("time"));
            txt.setText(txt.getText()+ className);
        }
        fsearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                ((Filterable) adapter).getFilter().filter(newQuery, ListStudentActivity.this);
            }
        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ((Filterable) adapter).getFilter().filter(searchView.getQuery(), ListStudentActivity.this);
//                return false;
//            }
//        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });
        searchView.clearFocus();
        alertBuilder = new AlertDialog.Builder(this);
        findViewById(R.id.txtHold).setVisibility(View.GONE);
        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        dtos = new ArrayList<>();
        if (className == null) {
            findViewById(R.id.btnShow).setVisibility(View.GONE);
            bar.setSelectedItemId(R.id.nav_studentmanagent);
            for (int i = 0; i < 15; i++) {
                String classstudy = subject[(i + 2) % 4];
//                if(i%5==1) {
//                    classstudy +=", " +  subject[(i + 3) % 4];
//                }
                dtos.add(new StudentDTO(i, "https://picsum.photos/60/60/?image=" + (i * 50 + 2), lastName[i % 3] + " " + middleName[i % 4] + " " + firstName[i % 5],
                        new Random().nextInt(3) + 15, classstudy, (i % 4 == 0) ? false : true));
            }
        } else {
            findViewById(R.id.btnShow).setVisibility(View.VISIBLE);
            bar.setVisibility(View.GONE);
            for (int i = 0; i < 5; i++) {
                dtos.add(new StudentDTO(i, "https://picsum.photos/60/60/?image=" + (i * 50 + 2), lastName[i % 3] + " " + middleName[i % 4] + " " + firstName[i % 5],
                        new Random().nextInt(3) + 15, className, (i % 4 == 0) ? false : true));
            }
        }

        adapter = new StudentAdapter(dtos, ListStudentActivity.this);
        adapter.setDtos(dtos);
        adapter.notifyDataSetChanged();


        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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
                        Intent intent4 = new Intent(ListStudentActivity.this, ListClassActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bar);
        bar.getMenu().getItem(2).setChecked(true);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
//                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
//                openItem.setBackground(new ColorDrawable(Color.WHITE));
//                openItem.setIcon(R.drawable.ic_edit);
//                openItem.setWidth(90);
//                menu.addMenuItem(openItem);
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
                final int positiondelete = position;
                switch (index) {
//                    case 0:
//                        Intent intent = new Intent(ListStudentActivity.this, EditStudentActivity.class);
//                        intent.putExtra("id", dtos.get(position).getId().toString());
//                        intent.putExtra("name", dtos.get(position).getName().toString());
//                        startActivity(intent);
//                        break;
                    case 0:
                        alertBuilder.setTitle("Delete Student")
                                .setIcon(R.drawable.ic_delete)
                                .setMessage("Are You Sure ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dtos.remove(positiondelete);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .show();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentDTO dto = adapter.getDtos().get(position);
                Intent intent = new Intent(ListStudentActivity.this, StudentDetailActivity.class);
                intent.putExtra("name", dto.getName());
                intent.putExtra("paid", dto.isPaid());
                intent.putExtra("class", dto.getGrade());
                startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus(v);
            }
        });
        fb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeStatus(v);
                return false;
            }
        });
    }

    public void addStudent(View view) {

    }

    @Override
    public void onFilterComplete(int count) {

    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            ((Filterable) adapter).getFilter().filter(" ", ListStudentActivity.this);
            searchView.clearFocus();
        } else {
            super.onBackPressed();
        }
    }

    public void changeStatus(View view) {
        if(up) {
            up = false;
            findViewById(R.id.txtHold).setVisibility(View.GONE);
            fb.setIconResource("\uf078");
        } else {
            up = true;
            findViewById(R.id.txtHold).setVisibility(View.VISIBLE);
            fb.setIconResource("\uf077");
        }
    }

    @Override
    public void onComplete(Integer numOfStu) {
        for (int i = 0; i < numOfStu; i++) {
            String classstudy = subject[(i + 2) % 4];
            if(i%5==1) {
                classstudy +=", " +  subject[(i + 3) % 4];
            }
            dtos.add(new StudentDTO(i, "https://picsum.photos/60/60/?image=" + (i * 50 + 2), lastName[i % 3] + " " + middleName[i % 4] + " " + firstName[i % 5],
                    new Random().nextInt(3) + 15, classstudy, (i % 4 == 0) ? false : true));
        }
        adapter.notifyDataSetChanged();
    }


    public void editClass(View view) {
        EditClassFragment fragment = new EditClassFragment();
        fragment.show(getSupportFragmentManager(),"edit");
    }
}
