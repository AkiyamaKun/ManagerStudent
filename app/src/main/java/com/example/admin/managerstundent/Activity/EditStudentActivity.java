package com.example.admin.managerstundent.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.managerstundent.R;
import com.example.admin.managerstundent.Ultils.CircleTransform;
import com.example.admin.managerstundent.Ultils.DocumentHelper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
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

}
