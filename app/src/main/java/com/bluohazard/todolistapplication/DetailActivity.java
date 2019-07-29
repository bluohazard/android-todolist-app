package com.bluohazard.todolistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluohazard.todolistapplication.Database.DatabaseHandler;
import com.bluohazard.todolistapplication.Model.ToDoList;

public class DetailActivity extends AppCompatActivity {

    EditText editTextNamaKegiatan, editTextKeterangan, editTextWaktu;
    Button buttonTambah, buttonUbah, buttonSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextNamaKegiatan = findViewById(R.id.edtTxt_namaKegiatan);
        editTextKeterangan = findViewById(R.id.edtTxt_keterangan);
        editTextWaktu = findViewById(R.id.edtTxt_waktu);
        buttonTambah = findViewById(R.id.btnTambah);
        buttonUbah = findViewById(R.id.btnUbah);
        buttonSelesai = findViewById(R.id.btnSelesai);

        buttonUbah.setVisibility(View.GONE);
        buttonSelesai.setEnabled(false);

        String[] stringArray = getIntent().getStringArrayExtra(MainActivity.Key_MainActivity);

        if (stringArray != null) {
            buttonTambah.setVisibility(View.GONE);
            buttonUbah.setVisibility(View.VISIBLE);
            buttonSelesai.setEnabled(true);

            editTextNamaKegiatan.setText(stringArray[1]);
            editTextKeterangan.setText(stringArray[2]);
            editTextWaktu.setText(stringArray[3]);
        } else {
            buttonUbah.setVisibility(View.GONE);
        }
    }

    public void onClickBackMainMenu(View view) {
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void onClickTambahData(View view) {
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        ToDoList toDoList = new ToDoList();

        if (TextUtils.isEmpty(editTextNamaKegiatan.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Nama kegiatannya apa?", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(editTextKeterangan.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "keterangannya...", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(editTextWaktu.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "jam berapa hayo?", Toast.LENGTH_LONG).show();
        } else {
            toDoList.setNamaKegiatan("" + editTextNamaKegiatan.getText().toString().trim());
            toDoList.setKeterangan("" + editTextKeterangan.getText().toString().trim());
            toDoList.setWaktu("" + editTextWaktu.getText().toString().trim());
            db.addRecord(toDoList);

            Toast.makeText(view.getContext(), "Berhasil menambahkan pengingat baru..", Toast.LENGTH_LONG).show();
            Intent i = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    public void onClickSelesai(View view) {
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        ToDoList toDoList = new ToDoList();

        String[] stringArray = getIntent().getStringArrayExtra(MainActivity.Key_MainActivity);

        toDoList.setId(Integer.parseInt(stringArray[0]));
        toDoList.setNamaKegiatan(stringArray[1]);
        toDoList.setKeterangan(stringArray[2]);
        toDoList.setWaktu(stringArray[3]);
        db.deleteModel(toDoList);

        Toast.makeText(view.getContext(), "Pengingat berhasil dihapus..", Toast.LENGTH_LONG).show();
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void onClickUbahData(View view) {
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        ToDoList toDoList = new ToDoList();

        String[] stringArray = getIntent().getStringArrayExtra(MainActivity.Key_MainActivity);

        toDoList.setId(Integer.parseInt(stringArray[0]));
        toDoList.setNamaKegiatan("" + editTextNamaKegiatan.getText().toString().trim());
        toDoList.setKeterangan("" + editTextKeterangan.getText().toString().trim());
        toDoList.setWaktu("" + editTextWaktu.getText().toString().trim());
        db.updateToDo(toDoList);

        Toast.makeText(view.getContext(), "Pengingat berhasil diubah..", Toast.LENGTH_LONG).show();
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(i);
    }
}
