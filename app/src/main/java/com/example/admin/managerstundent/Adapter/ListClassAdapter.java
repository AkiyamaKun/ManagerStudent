package com.example.admin.managerstundent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.managerstundent.Activity.ListStudentActivity;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.R;

import java.util.List;

public class ListClassAdapter extends BaseAdapter {

    private List<ClassDTO> classDTOS;
    private Context context;

    public ListClassAdapter(List<ClassDTO> classDTOS, Context context) {
        this.classDTOS = classDTOS;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return classDTOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return classDTOS.indexOf(classDTOS.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.class_item, parent, false);
        }
        final ClassDTO dto = classDTOS.get(position);
        ((TextView)convertView.findViewById(R.id.subject)).setText(dto.getClassName());
        ((TextView)convertView.findViewById(R.id.time)).setText(dto.getTime());
        CardView card = convertView.findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListStudentActivity.class);
                intent.putExtra("subject", dto.getClassName());
                intent.putExtra("time", dto.getTime());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
