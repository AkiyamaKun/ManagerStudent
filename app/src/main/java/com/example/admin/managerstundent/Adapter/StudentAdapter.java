package com.example.admin.managerstundent.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<StudentDTO> dtos;

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
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.student_list_item, parent, false);
        }
        StudentDTO dto = dtos.get(position);
        TextView id = convertView.findViewById(R.id.txtIDStudent);
        TextView name = convertView.findViewById(R.id.txtName);
        id.setText(dto.getId().toString());
        name.setText(dto.getName());
        return convertView;
    }

    public List<StudentDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<StudentDTO> dtos) {
        this.dtos = dtos;
    }
}
