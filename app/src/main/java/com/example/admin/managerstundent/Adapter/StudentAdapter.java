package com.example.admin.managerstundent.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.managerstundent.DTO.StudentDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.CircleTransform;
import com.polyak.iconswitch.IconSwitch;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter implements Filterable {
    private List<StudentDTO> dtos;
    private List<StudentDTO> original;
    private Context mContext;

    public StudentAdapter(List<StudentDTO> dtos, Context mContext) {
        this.dtos = dtos;
        this.original = dtos;
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
            convertView = inflater.inflate(R.layout.student_list_item, parent, false);
        }
        StudentDTO dto = dtos.get(position);
        TextView id = convertView.findViewById(R.id.txtIDStudent);
        TextView name = convertView.findViewById(R.id.txtName);
        TextView age = convertView.findViewById(R.id.txtAge);
        TextView grade = convertView.findViewById(R.id.txtGrade);
        ImageView img = convertView.findViewById(R.id.img);
        IconSwitch iconSwich = convertView.findViewById(R.id.icon_switch);
        if(dto.isPaid()) {
            iconSwich.setChecked(IconSwitch.Checked.LEFT);
        } else {
            iconSwich.setChecked(IconSwitch.Checked.RIGHT);
        }
        Picasso.with(mContext)
                .load(dto.getUrl())
                .transform(new CircleTransform())
                .into(img);
        id.setText(dto.getId().toString());
        name.setText(dto.getName());
        age.setText("Age: " + dto.getAge().toString());
        grade.setText("Class: " + dto.getGrade());
        return convertView;
    }

    public List<StudentDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<StudentDTO> dtos) {
        this.original = dtos;
        this.dtos = dtos;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (!TextUtils.isEmpty(constraint)) {

                    // Retrieve the autocomplete results.
                    List<StudentDTO> searchData = new ArrayList<>();

                    for (StudentDTO dto : original) {
                        if (dto.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            searchData.add(dto);
                        }
                    }

                    // Assign the data to the FilterResults
                    filterResults.values = searchData;
                    filterResults.count = searchData.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    dtos = (List<StudentDTO>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }
}
