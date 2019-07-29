package com.bluohazard.todolistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluohazard.todolistapplication.Database.DatabaseHandler;
import com.bluohazard.todolistapplication.Model.ToDoList;

public class DetailActivity extends AppCompatActivity {

    EditText editTextNamaKegiatan, editTextKeterangan, editTextWaktu;
    Button buttonSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextNamaKegiatan = findViewById(R.id.edtTxt_namaKegiatan);
        editTextKeterangan = findViewById(R.id.edtTxt_keterangan);
        editTextWaktu = findViewById(R.id.edtTxt_waktu);
        buttonSelesai = findViewById(R.id.btnSelesai);
    }

    public void onClickBackMainMenu(View view) {
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void onClickTambahData(View view) {
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        ToDoList toDoList = new ToDoList();

        toDoList.setNamaKegiatan("" + editTextNamaKegiatan.getText().toString().trim());
        toDoList.setKeterangan("" + editTextKeterangan.getText().toString().trim());
        toDoList.setWaktu("" + editTextWaktu.getText().toString().trim());
        db.addRecord(toDoList);

        buttonSelesai.setEnabled(false);
        Toast.makeText(view.getContext(), "Berhasil menambahkan pengingat baru..", Toast.LENGTH_LONG).show();
        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(i);
    }
}
