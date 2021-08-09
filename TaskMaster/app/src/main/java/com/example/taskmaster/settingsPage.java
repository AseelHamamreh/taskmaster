package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class settingsPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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