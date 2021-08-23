package com.example.taskmaster.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.example.taskmaster.R;
import com.example.taskmaster.adapter.Task;
import com.example.taskmaster.adapter.myAdapter;
import com.example.taskmaster.roomDB.AppDataBase;
import com.example.taskmaster.roomDB.TaskDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskDao taskDao;
    private AppDataBase appDataBase;

    public static final String TASK_TITLE = "taskTitle";
    public static final String TASK_BODY = "taskBody";
    public static final String TASK_STATUS = "taskStatus";

    private List<Task> taskList = new ArrayList<>();
    List<com.amplifyframework.datastore.generated.model.Task> taskListAmp = new ArrayList<>();
    private myAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

        } catch(AmplifyException exception){
            Log.e("testConfig", "onCreate: Failed to initialize Amplify plugins => " + exception.toString());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = findViewById(R.id.textView11);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String myName = sp.getString("myName","");
        textView.setText(myName+"'s tasks");

        getTaskDataFromAPI();
        renderRecycledView();
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
        Intent intent= new Intent(this , TaskDetailsPage.class);
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

    public void renderRecycledView(){
//        //dataBase:
//        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries().build();
//        taskDao = appDataBase.taskDao();
//        taskList = taskDao.findAll();
//        Collections.reverse(taskList);
//
//
        RecyclerView taskRecyclerView = findViewById(R.id.songList);
//
//
//        Task task1 = new Task("Reading", "raed about Rooms in Android", "assigned");
//        Task task2 = new Task("lab", "do lab #28", "complete");
//        Task task3 = new Task("code challenge", "do code challenge #28", "in progress");
//        taskList.add(task1);
//        taskList.add(task2);
//        taskList.add(task3);
//
        myAdapter adapter = new myAdapter(this, taskListAmp);
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void getTaskDataFromAPI() {

        Amplify.DataStore.query(
                com.amplifyframework.datastore.generated.model.Task.class,
                items -> {
                    while (items.hasNext()) {
                        com.amplifyframework.datastore.generated.model.Task item = items.next();
                        taskListAmp.add(item);
                        Collections.reverse(taskListAmp);
                        Log.i("Amplify", "Id " + item.getTitle());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

}