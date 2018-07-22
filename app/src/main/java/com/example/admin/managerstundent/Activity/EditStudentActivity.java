package com.example.admin.managerstundent.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.managerstundent.Adapter.DBAdapter;
import com.example.admin.managerstundent.DTO.ClassDTO;
import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.CircleTransform;
import com.example.admin.managerstundent.Ultils.DocumentHelper;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class EditStudentActivity extends AppCompatActivity {

    private Uri returnUri;
    private String userAvatar = null;
    private Bitmap bitmap, rotateBitmap;
    private ImageView pickImage;
    private ExifInterface exif;
    private File chosenFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        ImageView img = findViewById(R.id.img);
        Random r = new Random();
        String url = "https://picsum.photos/250/250/?image=" + r.nextInt(200);
        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);
        ((TextView) findViewById(R.id.edit_test_name)).setText(getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.edit_test_phone)).setText(getIntent().getStringExtra("phone"));
        ((TextView) findViewById(R.id.edit_test_birthday)).setText(getIntent().getStringExtra("birthday"));
        ((TextView) findViewById(R.id.edit_test_name_parent)).setText(getIntent().getStringExtra("gender"));
        ((TextView) findViewById(R.id.edit_test_grade)).setText(getIntent().getStringExtra("class"));
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

    public void clickToCancel(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    public void onChooseImage(View view) {
        userAvatar = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getFilePath();
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    int orientation = 6;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        pickImage = findViewById(R.id.img);
                        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
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
                        pickImage.setImageBitmap(rotateBitmap);
                    } catch (Exception e) {
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            returnUri = data.getData();
//            getFilePath();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
//                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
//
//                rotateBitmap = null;
//                switch (orientation) {
//
//                    case ExifInterface.ORIENTATION_ROTATE_90:
//                        rotateBitmap = rotateImage(bitmap, 90);
//                        break;
//
//                    case ExifInterface.ORIENTATION_ROTATE_180:
//                        rotateBitmap = rotateImage(bitmap, 180);
//                        break;
//
//                    case ExifInterface.ORIENTATION_ROTATE_270:
//                        rotateBitmap = rotateImage(bitmap, 270);
//                        break;
//
//                    case ExifInterface.ORIENTATION_NORMAL:
//                    default:
//                        rotateBitmap = bitmap;
//                }
//                pickImage = findViewById(R.id.img);
//                pickImage.setImageBitmap(rotateBitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private void getFilePath() {
        String filePath = DocumentHelper.getPath(this, this.imageUri);
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

    public void chooseClass(View view) {
        ClassChooserFragment fragment = new ClassChooserFragment();
        fragment.show(getSupportFragmentManager(),"choose classes");
    }
}
