package com.example.admin.managerstundent.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.Entity.Student;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.DocumentHelper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private Uri returnUri;
    private String userAvatar = null;
    private Bitmap bitmap, rotateBitmap;
    private ImageView pickImage;
    private ExifInterface exif;
    private File chosenFile;

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

        //Bind the wiew using butterknife
        ButterKnife.bind(this);

        //init realm
        realm = Realm.getDefaultInstance();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> dataSrc = new ArrayList<>();
        DBAdapter adapter = new DBAdapter(this);
        adapter.open();
        List<ClassDTO> dtos = adapter.findAllClass();
        for (ClassDTO dto: dtos) {
            dataSrc.add(dto.getClassName());
        }
        adapter.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataSrc);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
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
        Intent intent = new Intent(this, ListStudentActivity.class);
        startActivity(intent);
        finish();
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
        student.setPhone(phone.getText().toString());
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

    public void clickToCancel(View view) {
        Intent intent = new Intent(this, ListStudentActivity.class);
        startActivity(intent);
        finish();
    }
    public void onChooseImage(View view) {
        userAvatar = null;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            returnUri = data.getData();
            getFilePath();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

                rotateBitmap = null;
                switch (orientation) {

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotateBitmap = rotateImage(bitmap, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotateBitmap = rotateImage(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotateBitmap = rotateImage(bitmap, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        rotateBitmap = bitmap;
                }
                pickImage = findViewById(R.id.img);
                pickImage.setImageBitmap(rotateBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private void getFilePath() {
        String filePath = DocumentHelper.getPath(this, this.returnUri);
        if (filePath == null || filePath.isEmpty()) return;
        chosenFile = new File(filePath);
        try {
            exif = new ExifInterface(String.valueOf(chosenFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeDate(View view) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateTime dt = new DateTime(year, month, dayOfMonth, 0, 0, 0);
                ((EditText) findViewById(R.id.edit_test_birthday)).setText(dtf.print(dt));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        dpd.show();
    }
}
