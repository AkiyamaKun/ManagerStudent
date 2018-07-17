package com.example.admin.managerstundent.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.admin.managerstundent.DTO.ClassDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    private SQLiteDatabase db;
    private Context context;
    private DBHelper dbHelper;
    private static final String DB_NAME = "class.db";
    private static final Integer DB_VERSION = 1;

    private static final String TABLE_CLASS = "StudyClass";
    private static final String COL_CLASS_ID = "id";
    private static final String COL_CLASS_NAME = "name";
    private static final String COL_CLASS_SUBJECT = "subject";
    private static final String COL_CLASS_TIME = "time";
    private static final String COL_CLASS_WEEKDAYS = "weekdays";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_CLASS + "(" +
            COL_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CLASS_NAME + " TEXT," +
            COL_CLASS_SUBJECT + " TEXT," +
            COL_CLASS_TIME + " TEXT," +
            COL_CLASS_WEEKDAYS + " TEXT" +
            ");";
    public static final String SQL_DROP = "Drop table if exists " + TABLE_CLASS;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(checkDBExist()) {
                db.execSQL(SQL_CREATE);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DROP);
            onCreate(db);
        }
    }

    public DBAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<ClassDTO> findAllClass() {
        Cursor cursor = db.query(TABLE_CLASS, new String[]{COL_CLASS_ID, COL_CLASS_NAME, COL_CLASS_SUBJECT, COL_CLASS_TIME, COL_CLASS_WEEKDAYS},
                null, null, null, null, null);
        return cursorToList(cursor);
    }

    public List<ClassDTO> findOne(String name) {
        Cursor cursor = db.query(TABLE_CLASS, new String[]{COL_CLASS_ID, COL_CLASS_NAME, COL_CLASS_SUBJECT, COL_CLASS_TIME, COL_CLASS_WEEKDAYS},
                COL_CLASS_NAME + " = ?", new String[]{name}, null, null, null);
        return cursorToList(cursor);
    }

    public List<ClassDTO> cursorToList(Cursor cursor) {
        List<ClassDTO> dtos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COL_CLASS_NAME));
                String subject = cursor.getString(cursor.getColumnIndex(COL_CLASS_SUBJECT));
                String time = cursor.getString(cursor.getColumnIndex(COL_CLASS_TIME));
                String weekdays = cursor.getString(cursor.getColumnIndex(COL_CLASS_WEEKDAYS));
                ClassDTO dto = new ClassDTO(name, subject, time, weekdays);
                dtos.add(dto);
            } while (cursor.moveToNext());
        }
        return dtos;
    }

    public boolean addClass(ClassDTO dto) {
        ContentValues values = new ContentValues();
        values.put(COL_CLASS_NAME, dto.getClassName());
        values.put(COL_CLASS_SUBJECT, dto.getSubject());
        values.put(COL_CLASS_TIME, dto.getTime());
        values.put(COL_CLASS_WEEKDAYS, dto.getWeeksdays());
        Long numOfAdded = db.insert(TABLE_CLASS, null, values);
        return numOfAdded > 0;
    }

    public boolean updateClass(ClassDTO dto) {
        ContentValues values = new ContentValues();
        values.put(COL_CLASS_NAME, dto.getClassName());
        values.put(COL_CLASS_SUBJECT, dto.getSubject());
        values.put(COL_CLASS_TIME, dto.getTime());
        values.put(COL_CLASS_WEEKDAYS, dto.getWeeksdays());
        int numOfUpdated = db.update(TABLE_CLASS, values, COL_CLASS_NAME + " = ? ", new String[]{dto.getClassName()});
        return numOfUpdated > 0;
    }

    public boolean deleteClass(String name) {
        return (db.delete(TABLE_CLASS, COL_CLASS_NAME + " = ?", new String[]{name}) > 0);
    }


    private boolean checkDBExist() {
        return context.getDatabasePath(DB_NAME).exists();
    }
}
