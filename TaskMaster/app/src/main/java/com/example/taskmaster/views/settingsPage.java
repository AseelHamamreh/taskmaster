package com.example.taskmaster.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmaster.R;

public class settingsPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Settings");
        setContentView(R.layout.activity_settings_page);
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
    }
    
    public void updateUserName(View view){
        Toast.makeText(this,"UserName Added!", Toast.LENGTH_LONG).show();

        EditText name = (EditText) findViewById(R.id.userName);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myName", name.getText().toString());
        editor.commit();
    }
}