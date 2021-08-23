package com.example.taskmaster.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

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

    public void filterData(){
//        RadioGroup radioGroup = findViewById(R.id.radioGroup1);
//        int radioId = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButton = findViewById(radioId);
//        String string = radioButton.toString();

//        Log.i("myName", string);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("theTeam", "32803358-b4fa-407b-b67d-8de21eb3d3a1");
        editor.commit();
    }

}