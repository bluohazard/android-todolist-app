package com.bluohazard.todolistapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bluohazard.todolistapplication.Model.ToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greggy Gianini Firmansyah on 7/29/2019.
 * Email : greggygf@gmail.com
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "TodoList";

    // table name
    private static final String TABLE_TODO = "todos";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA_KEGIATAN = "nama_kegiatan";
    private static final String KEY_KETERANGAN = "keterangan";
    private static final String KEY_WAKTU = "waktu";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMA_KEGIATAN + " TEXT," + KEY_KETERANGAN + " TEXT," + KEY_WAKTU + " TEXT" + ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public void addRecord(ToDoList toDoLists) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_KEGIATAN, toDoLists.getNamaKegiatan());
        values.put(KEY_KETERANGAN, toDoLists.getKeterangan());
        values.put(KEY_WAKTU, toDoLists.getWaktu());

        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public ToDoList getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TODO, new String[]{KEY_ID,
                        KEY_NAMA_KEGIATAN, KEY_KETERANGAN, KEY_WAKTU}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDoList toDoList = new ToDoList(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return toDoList;
    }

    // get All Record
    public List<ToDoList> getAllRecord() {
        List<ToDoList> contactList = new ArrayList<ToDoList>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ToDoList toDoList = new ToDoList(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                toDoList.setId(Integer.parseInt(cursor.getString(0)));
                toDoList.setNamaKegiatan(cursor.getString(1));
                toDoList.setKeterangan(cursor.getString(2));
                toDoList.setWaktu(cursor.getString(3));

                contactList.add(toDoList);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getUserModelCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        int count = cursor.getCount();
        // return count
        return count;
    }

    public int updateToDo(ToDoList toDoList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_KEGIATAN, toDoList.getNamaKegiatan());
        values.put(KEY_KETERANGAN, toDoList.getKeterangan());
        values.put(KEY_WAKTU, toDoList.getWaktu());

        // updating row
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[]{String.valueOf(toDoList.getId())});
    }
}
