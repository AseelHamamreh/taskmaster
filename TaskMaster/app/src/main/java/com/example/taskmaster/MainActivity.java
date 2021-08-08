package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addingTask(View v){
        Intent i = new Intent(this, AddTaskPage.class);
        startActivity(i);
    }

    public void showTasks(View v){
        Intent i = new Intent(this, allTasksPage.class);
        startActivity(i);
    }
}