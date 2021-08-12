package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskPage extends AppCompatActivity {
    private TaskDao taskDao;
    private AppDataBase appDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Task");
        setContentView(R.layout.activity_add_task_page);

        //dataBase:
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries().build();
        taskDao = appDataBase.taskDao();


        Button addButton = findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText titleInput = findViewById(R.id.myTitle);
                TextView bodyInput =  findViewById(R.id.myDescription);
                TextView statusInput = findViewById(R.id.myStatus);
                String title = titleInput.getText().toString();
                String body = bodyInput.getText().toString();
                String status = statusInput.getText().toString();

                Task task = new Task(title, body, status);
                taskDao.insertOne(task);


                Toast.makeText(AddTaskPage.this,"Task added!", Toast.LENGTH_LONG).show();



            }
        });
    }

}