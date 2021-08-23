package com.example.taskmaster.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.room.AppDataBase;
import com.example.taskmaster.R;
import com.example.taskmaster.room.Task;
import com.example.taskmaster.room.TaskDao;
import com.example.taskmaster.adapter.myAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskDao taskDao;
    private AppDataBase appDataBase;
    private Handler handler;

    public static final String TASK_TITLE = "taskTitle";
    public static final String TASK_BODY = "taskBody";
    public static final String TASK_STATUS = "taskStatus";

    private List<Task> taskList = new ArrayList<>();
    private List<com.amplifyframework.datastore.generated.model.Task> taskList2 = new ArrayList<>();
    private myAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Amplify configure:
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

        } catch(AmplifyException exception){
            Log.e("testConfig", "onCreate: Failed to initialize Amplify plugins => " + exception.toString());
        }

        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        return false;
                    }
                });


        gettingDataFromAPI();
        renderRecycledView();



//        creatingTeams();



    }

    @Override
    protected void onResume() {
        super.onResume();
        gettingDataFromAPI();
        renderRecycledView();


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
        //dataBase:
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries().build();
        taskDao = appDataBase.taskDao();
        taskList = taskDao.findAll();
        Collections.reverse(taskList);


        RecyclerView taskRecyclerView = findViewById(R.id.songList);


        Task task1 = new Task("Reading", "raed about Rooms in Android", "assigned");
        Task task2 = new Task("lab", "do lab #28", "complete");
        Task task3 = new Task("code challenge", "do code challenge #28", "in progress");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);



        myAdapter adapter = new myAdapter(this, taskList2);
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void creatingTeams(){

        Team team1 = Team.builder().name("Studying").build();
        Team team2 = Team.builder().name("Reading").build();
        Team team3 = Team.builder().name("Sport").build();

        Amplify.API.mutate(ModelMutation.create(team1),
                success -> Log.i("savedItem", "Saved item: " + success.getData().getName()),
                error -> Log.e("savedItem", "Could not save item to Api", error)
        );

        Amplify.API.mutate(ModelMutation.create(team2),
                success -> Log.i("savedItem", "Saved item: " + success.getData().getName()),
                error -> Log.e("savedItem", "Could not save item to Api", error)
        );

        Amplify.API.mutate(ModelMutation.create(team3),
                success -> Log.i("savedItem", "Saved item: " + success.getData().getName()),
                error -> Log.e("savedItem", "Could not save item to Api", error)
        );
    }


    public  void gettingDataFromAPI() {
        Amplify.API.query(ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                response -> {
                    for (com.amplifyframework.datastore.generated.model.Task task : response.getData()) {
                        taskList2.add(task);
                        Log.i("dataAPI", "Task from api: " + task.getTitle());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("dataAPI", "errrorrrr: => " + error.toString())
        );
        Collections.reverse(taskList2);
    }
}