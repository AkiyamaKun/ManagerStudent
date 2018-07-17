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

public class ClassDetailAdapter extends BaseAdapter {

    private List<ClassDTO> dtos;
    private Context context;

    public ClassDetailAdapter(List<ClassDTO> dtos, Context context) {
        this.dtos = dtos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dtos.indexOf(dtos.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.detail_class_item, parent, false);
        }
        final ClassDTO dto = dtos.get(position);
        ((TextView)convertView.findViewById(R.id.name)).setText(dto.getClassName());
        ((TextView)convertView.findViewById(R.id.subject)).setText(dto.getSubject());
        ((TextView)convertView.findViewById(R.id.time)).setText(dto.getTime());
        ((TextView)convertView.findViewById(R.id.weekdays)).setText(dto.getWeeksdays());
        CardView card = convertView.findViewById(R.id.card);
        return convertView;
    }
}
