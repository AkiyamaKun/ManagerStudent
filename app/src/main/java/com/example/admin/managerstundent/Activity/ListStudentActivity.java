package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.admin.managerstundent.Adapter.StudentAdapter;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity {
    SwipeMenuListView listView;
    StudentAdapter adapter;
    List<StudentDTO> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        listView = findViewById(R.id.listView);

        dtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dtos.add(new StudentDTO(i,"https://picsum.photos/70/70/?image="+(i*50+6), ((i % 2 == 0) ? "Male Student" : "Female Student") + " " + i,
                    (i%3==1)? 9 : 11, (i%3==2)? "10A" : "11B"));
        }
        adapter = new StudentAdapter(dtos, ListStudentActivity.this);
        adapter.setDtos(dtos);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("EDIT");
                // set item title fontsize
                openItem.setTitleSize(20);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setTitle("DELETE");
                // set item title fontsize
                deleteItem.setTitleSize(20);
                // set item title font color
                deleteItem.setTitleColor(Color.RED);
                // add to menu
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
}
