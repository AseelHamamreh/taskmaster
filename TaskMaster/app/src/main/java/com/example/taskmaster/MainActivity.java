package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView11);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String myName = sp.getString("myName","");
        textView.setText(myName+"'s tasks");
    }

    public void addingTask(View v){
        Intent i = new Intent(this, AddTaskPage.class);
        startActivity(i);
    }

    public void showTasks(View v){
        Intent i = new Intent(this, allTasksPage.class);
//        String message = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
//        i.putExtra("my", message);
        startActivity(i);
    }

    public void handleSettings(View view){
        Intent intent = new Intent(this, settingsPage.class);
        startActivity(intent);
    }

    public void studyingBtn(View view){
        String title = ((Button) findViewById(R.id.Studying)).getText().toString();
        Intent intent= new Intent(this ,TaskDetailsPage.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void readingBtn(View view){
        String title = ((Button) findViewById(R.id.Reading)).getText().toString();
        Intent intent= new Intent(this ,TaskDetailsPage.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void sportBtn(View view){
        String title = ((Button) findViewById(R.id.Sport)).getText().toString();
        Intent intent= new Intent(this ,TaskDetailsPage.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}