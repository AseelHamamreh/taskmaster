package com.example.taskmaster.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.example.taskmaster.R;

public class signIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        configure();


        Button signIn = findViewById(R.id.sSignIn);
        EditText username = findViewById(R.id.s2UserName);
        EditText password = findViewById(R.id.s2Password);
        Button createNewAccount = findViewById(R.id.sNewAccountBtn);

        signIn.setOnClickListener(view -> {
            signIn(username.getText().toString(), password.getText().toString());
        });

        createNewAccount.setOnClickListener(view -> {
            Intent goToSignUp = new Intent(signIn.this, signUpPage.class);
            startActivity(goToSignUp);
        });
    }

    void signIn(String userName, String password){
        Amplify.Auth.signIn(
                userName,
                password,
                success ->{
                    Log.i("signIn", "signIn successful: " + success.toString());
                    Intent goToMain = new Intent(signIn.this, MainActivity.class);
                    startActivity(goToMain);
                },
                error ->{
                    Log.e("signIn", "signIn failed: " + error.toString());
                }
        );
    }

    void configure(){
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.e("testConfig", "success");

        } catch(AmplifyException exception){
            Log.e("testConfig", "onCreate: Failed to initialize Amplify plugins => " + exception.toString());
        }
    }


}