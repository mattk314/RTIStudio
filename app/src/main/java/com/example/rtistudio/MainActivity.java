package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    String email = "";
    String password = "";
    EditText emailEditText;
    EditText passwordEditText;
    TextView messageTextView;
    Button signinButton;

    MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signinButton = (Button) findViewById(R.id.signinButton);
        messageTextView = (TextView) findViewById(R.id.errorLogTextView);

        //TODO load credentials if they exist

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if(password.length()!=0 && email.length()!=0) {     //edit to also make sure !@#$ and such are not included (fits email format)
                    try {
                        new GetTokenTask(instance).execute(URLEncoder.encode(email, "UTF-8"), URLEncoder.encode(password, "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        messageTextView.setText("Invalid Login");
                    }
                    Log.d("Gets Here", "Successfully sends down email and password to getToken Class");
                }
                else{
                    messageTextView.setText("Invalid Login");
                    //set text box thing to "Invalid things"
                }

            }
        });
    }

    public void incorrectLogin(){
        //set text box thing to "invalid things"
        messageTextView.setText("Invalid Login");
    }

    public void SaveCredentials(){
        //Save username and password because login was successful
        //TODO this needs some research but this is where you would do it
    }

}



//send post to api with rtistudio login credentials to get token
//post token to get JSON with courses
//post token and course id to get questions for that course
//post token, id of question, id of answer to return the questions with the answers.



