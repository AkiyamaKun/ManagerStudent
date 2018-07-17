package com.example.admin.managerstundent.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.admin.managerstundent.Adapter.ClassDetailAdapter;
import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.BottomNavigationViewHelper;

import java.util.List;

public class ListClassActivity extends AppCompatActivity {
    private AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
        bar.setSelectedItemId(R.id.nav_todolist);
        DBAdapter db = new DBAdapter(this);
        db.open();
        final List<ClassDTO> dtos = db.findAllClass();
        final ClassDetailAdapter adapter = new ClassDetailAdapter(dtos, this);
        SwipeMenuListView listView = findViewById(R.id.list);
        alertBuilder = new AlertDialog.Builder(this);
        listView.setAdapter(adapter);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(ListClassActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_timetable:
                        Intent intent2 = new Intent(ListClassActivity.this, TableActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_studentmanagent:
                        Intent intent3 = new Intent(ListClassActivity.this, ListStudentActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_todolist:
                        Intent intent4 = new Intent(ListClassActivity.this, ListClassActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bar);
        bar.getMenu().getItem(3).setChecked(true);
        db.close();
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassDTO dto = dtos.get(position);
                Intent intent = new Intent(ListClassActivity.this, ListStudentActivity.class);
                intent.putExtra("subject", dto.getClassName());
                intent.putExtra("time", dto.getTime());
                startActivity(intent);
            }
        });
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
    }

    public void addClass(View view) {
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }
}
