package com.example.taskmaster.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.example.taskmaster.R;

public class verification extends AppCompatActivity {
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        EditText editText = findViewById(R.id.sCode);
        Button verify = findViewById(R.id.sVerifyBtn);

        Intent intent = getIntent();
        username = intent.getExtras().getString("username", "");
        password = intent.getExtras().getString("password", "");

        verify.setOnClickListener(view -> verification(username, editText.getText().toString()));


    }

    void verification(String username, String verificationCode){
        Amplify.Auth.confirmSignUp(
                username,
                verificationCode,
                success -> {
                    Log.i("verification", "verification successful: " + success.toString());
                    Intent goToSignIn = new Intent(verification.this, signIn.class);
                    goToSignIn.putExtra("username", username);
                    startActivity(goToSignIn);
                },
                error -> {
                    Log.e("verification", "verification failed: " + error.toString());
                });
    }

}