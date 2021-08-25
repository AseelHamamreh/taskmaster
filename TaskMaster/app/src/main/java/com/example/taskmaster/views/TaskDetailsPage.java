package com.example.taskmaster.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.example.taskmaster.R;

import java.io.File;
import java.net.URL;


public class TaskDetailsPage extends AppCompatActivity {
    private URL url =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Task Details");
        setContentView(R.layout.activity_task_details_page);



        Intent i =getIntent();
        String title = i.getStringExtra("title");
        String Description = i.getStringExtra("description");
        String status = i.getStringExtra("status");
        ((TextView)findViewById(R.id.showTitle)).setText(title);
        ((TextView)findViewById(R.id.showDescription)).setText(Description);
        ((TextView)findViewById(R.id.showStatus)).setText(status);


    }
}