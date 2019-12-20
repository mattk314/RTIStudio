package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestionsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_menu);

        String questionsJSON = getIntent().getExtras().getString("com.example.RTIStudio.questions");


 
    }
}
