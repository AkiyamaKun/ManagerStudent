package com.example.admin.managerstundent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.managerstundent.Activity.AddClassActivity;
import com.example.admin.managerstundent.Activity.StudentChooseFragment;
import com.example.admin.managerstundent.Activity.WeekdaysChooserFragment;
import com.example.admin.managerstundent.R;

import java.util.List;

import static com.example.admin.managerstundent.Activity.WeekdaysChooserFragment.days;

public class WeekdaysAdapter extends BaseAdapter {
    private List<String> weekdays;
    private Context mContext;

    public WeekdaysAdapter(List<String> dtos, Context mContext) {
        this.weekdays = dtos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return weekdays.size();
    }

    @Override
    public Object getItem(int position) {
        return weekdays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return weekdays.indexOf(weekdays.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.weekdays_chooser, parent, false);
        }
        final String dto = weekdays.get(position);
        ((TextView) convertView.findViewById(R.id.txt)).setText(dto);
        final CheckBox chk = convertView.findViewById(R.id.chk);
        View view = convertView.findViewById(R.id.item);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk.isChecked()) {
                    days +=days.substring(0,days.length()-3);
                    chk.setChecked(false);
                } else {
                    days +="-" +dto.substring(0,3);
                    System.out.println("adapter days"+days);
                    chk.setChecked(true);
                }

            }
        });
        return convertView;
    }
}
