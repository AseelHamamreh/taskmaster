package com.example.taskmaster.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.room.AppDataBase;
import com.example.taskmaster.R;
import com.example.taskmaster.room.Task;
import com.example.taskmaster.room.TaskDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddTaskPage extends AppCompatActivity {
    private TaskDao taskDao;
    private AppDataBase appDataBase;
    List <Team> teams = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Task");
        setContentView(R.layout.activity_add_task_page);

        getTeamsFromApiByName();

        //dataBase:
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries().build();
        taskDao = appDataBase.taskDao();


        Button addButton = findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                EditText titleInput = findViewById(R.id.myTitle);
                TextView bodyInput =  findViewById(R.id.myDescription);
                TextView statusInput = findViewById(R.id.myStatus);
                String title = titleInput.getText().toString();
                String body = bodyInput.getText().toString();
                String status = statusInput.getText().toString();

                RadioGroup radioGroup;
                RadioButton radioButton;
                radioGroup = findViewById(R.id.radioGroup1);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String teamName = radioButton.getText().toString();


                Task task = new Task(title, body, status);
                taskDao.insertOne(task);

                Team team = teams.stream()
                        .filter(team1 -> team1.getName()
                                .equals(teamName))
                        .collect(Collectors.toList()).get(0);
                Log.i("getId", team.getId());



                com.amplifyframework.datastore.generated.model.Task myTask = com.amplifyframework.datastore.generated.model.Task
                        .builder().team(team).title(title).body(body).state(status).build();

                Amplify.API.mutate(ModelMutation.create(myTask),
                        success -> Log.i("savedItem", "Saved item: " + success.getData().getTeam()),
                        error -> Log.e("savedItem", "Could not save item to DataStore", error)
                );

                Toast.makeText(AddTaskPage.this,"Task added!", Toast.LENGTH_LONG).show();
            }
        });
    }


    private List<Team> getTeamsFromApiByName(){

        Amplify.API.query(ModelQuery.list(Team.class) ,
                response -> {
                    for (Team team : response.getData()){
                        Log.i("teams", "succeed to getTeamFromApiByName: Team Name --> "+ team.getName());
                        teams.add(team) ;
                    }
                } ,
                failure -> Log.i("teams", "failed to getTeamFromApiByName: Team Name -->" + failure.toString())
        );
        return teams ;
    }


}