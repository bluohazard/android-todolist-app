package com.bluohazard.todolistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bluohazard.todolistapplication.Adapter.ToDoListAdapter;
import com.bluohazard.todolistapplication.Database.DatabaseHandler;
import com.bluohazard.todolistapplication.Model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.OnDaftarClickListener {

    public DatabaseHandler db;
    public RecyclerView rv;
    public ToDoListAdapter toDoListAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<ToDoList> toDoLists = new ArrayList<>();

    public static final String Key_MainActivity = "Key_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rvList);
        db = new DatabaseHandler(this);

        toDoLists.addAll(db.getAllRecord());
        toDoListAdapter = new ToDoListAdapter(toDoLists);
        toDoListAdapter.setListener(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setAdapter(toDoListAdapter);
        rv.setLayoutManager(layoutManager);
    }

    public void onClickCreateTodoList(View view) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        ToDoList currentToDo = db.getTodo(position+1);

        int currentId = currentToDo.getId();
        String currentNamaKegiatan = currentToDo.getNamaKegiatan();
        String currentKeterangan = currentToDo.getKeterangan();
        String currentWaktu = currentToDo.getWaktu();

        i.putExtra(Key_MainActivity, new String[]{String.valueOf(currentId),currentNamaKegiatan,currentKeterangan,currentWaktu});
        startActivity(i);
    }
}
