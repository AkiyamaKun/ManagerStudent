package com.example.admin.managerstundent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.managerstundent.Activity.StudentChooseFragment;
import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.CircleTransform;
import com.polyak.iconswitch.IconSwitch;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentChooserAdapter extends BaseAdapter {

    private List<StudentDTO> dtos;
    private Context mContext;

    public StudentChooserAdapter(List<StudentDTO> dtos, Context mContext) {
        this.dtos = dtos;
        this.mContext = mContext;
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
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.student_chooser_item, parent, false);
        }
        View view = convertView.findViewById(R.id.item);
        StudentDTO dto = dtos.get(position);
        TextView id = convertView.findViewById(R.id.txtIDStudent);
        TextView name = convertView.findViewById(R.id.txtName);
        ImageView img = convertView.findViewById(R.id.img);
        final CheckBox chk = convertView.findViewById(R.id.chk);
        Picasso.with(mContext)
                .load(dto.getUrl())
                .transform(new CircleTransform())
                .into(img);
        id.setText(dto.getId().toString());
        name.setText(dto.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk.isChecked()) {
                    StudentChooseFragment.num --;
                    chk.setChecked(false);
                } else {
                    StudentChooseFragment.num ++;
                    chk.setChecked(true);
                }

            }
        });
        return convertView;
    }

    public List<StudentDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<StudentDTO> dtos) {
        this.dtos = dtos;
    }
}
