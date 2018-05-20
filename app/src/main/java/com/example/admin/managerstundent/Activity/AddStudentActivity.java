package com.example.admin.managerstundent.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.managerstundent.Entity.Student;
import com.example.admin.managerstundent.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * Author: DangNHH
 * 19/05/2018
 *
 * Add Student Activity Class
 */
public class AddStudentActivity extends AppCompatActivity {
    private static final String TAG = AddStudentActivity.class.toString();
    //Realm use for database
    private Realm realm;

    //Bind all View
    @BindView(R.id.edit_test_name)
    EditText name;

    @BindView(R.id.edit_test_birthday)
    EditText birthday;

    @BindView(R.id.edit_test_grade)
    EditText grade;

    @BindView(R.id.chb_math)
    CheckBox math;

    @BindView(R.id.chb_physical)
    CheckBox physical;

    @BindView(R.id.chb_chemistry)
    CheckBox chemistry;

    @BindView(R.id.edit_test_name_parent)
    EditText nameParent;

    @BindView(R.id.edit_test_phone)
    EditText phone;

    /**
     * Override On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //bind the wiew using butterknife
        ButterKnife.bind(this);

        //init realm
        realm = Realm.getDefaultInstance();
    }

    /**
     * Excute event click on button Reset in activity add student
     * @param view
     */
    public void ClickOnReset(View view) {
        //Set All Attribute is null/blank
        name.setText(null);
        birthday.setText(null);
        grade.setText(null);
        math.setChecked(false);
        physical.setChecked(false);
        chemistry.setChecked(false);
        nameParent.setText(null);
        phone.setText(null);
    }

    /**
     * Excute event click on button Submit in activity add student
     * @param view
     */
    public void ClickOnSubmit(View view) {
        //Begin transaction
        realm.beginTransaction();

        //Get max index with primary key
        Number currentId = realm.where(Student.class).max("studentID");
        int nextId = 0;
        if(currentId == null){
            nextId = 1;
        }else{
            nextId = currentId.intValue() + 1;
        }

        Student student = realm.createObject(Student.class, nextId);
//        student.setStudentID(nextId);
        student.setName(name.getText().toString());
        student.setName_parent(nameParent.getText().toString());
        student.setPhone(Integer.parseInt(phone.getText().toString()));
        student.setChemistry(chemistry.isChecked());
        student.setMath(math.isChecked());
        student.setPhysical(physical.isChecked());
        student.setGrade(Integer.parseInt(grade.getText().toString()));

        //Cover String to Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(birthday.getText().toString());
            student.setBirthday(date);
        } catch (ParseException e) {
            student.setBirthday(null);
        }
        Log.d(TAG, String.format("ClickOnSubmit: %s",student.toString() ));

        //Commit transaction
        realm.commitTransaction();
        finish();
    }
}
