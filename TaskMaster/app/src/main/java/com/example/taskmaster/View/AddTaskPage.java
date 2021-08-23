package com.example.taskmaster.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.example.taskmaster.R;
import com.example.taskmaster.adapter.Task;
import com.example.taskmaster.roomDB.AppDataBase;
import com.example.taskmaster.roomDB.TaskDao;

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

                com.amplifyframework.datastore.generated.model.Task taskItem = com.amplifyframework.datastore.generated.model.Task.builder()
                        .title(title).description(body).status(status)
                        .build();

                Amplify.DataStore.save(taskItem,
                        success -> Log.i("savedItem", "Saved item: " + success.item().getTitle()),
                        error -> Log.e("savedItem", "Could not save item to DataStore", error)
                );


                Toast.makeText(AddTaskPage.this,"Task added!", Toast.LENGTH_LONG).show();
            }
        });
    }

}