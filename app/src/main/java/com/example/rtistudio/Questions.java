package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Questions extends AppCompatActivity {

    ListView questionsListView;
    Button sumbitAnswersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questionsListView = (ListView) findViewById(R.id.questionsListView);
        sumbitAnswersButton = (Button) findViewById(R.id.submitAnswersButton);

        setQuestionsListView();


        sumbitAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void setQuestionsListView(){  //take in arraylist of questionAnswer objects?
        ArrayList<QuestionAnswer> itemsToDo = new ArrayList<QuestionAnswer>();

        itemsToDo.add(new QuestionAnswer("What is your name?")); //replace string with a getQuestion from the api?
        itemsToDo.add(new QuestionAnswer("How are you doing?"));
        itemsToDo.add(new QuestionAnswer("How was your day?"));
        itemsToDo.add(new QuestionAnswer("What have you done today?"));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsToDo);

        questionsListView.setAdapter(arrayAdapter);
    }

}
