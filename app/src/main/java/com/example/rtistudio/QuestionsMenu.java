package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionsMenu extends AppCompatActivity {

    ListView questionsListView;
    Button saveButton;
    Button backButton;

    QuestionsMenu instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_menu);

        String questionsJSON = getIntent().getExtras().getString("com.example.RTIStudio.questions");

        questionsListView = findViewById(R.id.QuestionsListView);

        ArrayList<String> QuestionsListItems = new ArrayList<String>();
        ArrayList<Integer> AnswerTypes = new ArrayList<Integer>();


        //QuestionsListItems is set to an arraylist of the questions

        try {
            JSONArray questionsArray = new JSONObject(questionsJSON).getJSONArray("questions");

            for(int i=0; i<questionsArray.length();i++) {
                QuestionsListItems.add(questionsArray.getJSONObject(i).getString("question"));
                AnswerTypes.add(questionsArray.getJSONObject(i).getInt("type"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapter arrayAdapter = new CustomAdapter(this, R.layout.answer_yesno, AnswerTypes.toArray(new Integer[AnswerTypes.size()]), QuestionsListItems.toArray(new String[QuestionsListItems.size()]));


        questionsListView.setAdapter(arrayAdapter);

        backButton = (Button) findViewById(R.id.backButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        instance = this;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Click", "Back button clicked");

                //need to make this work better

//                Intent i = new Intent(getApplicationContext(), ToDoManager.class);
//                instance.startActivity(i);
            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Click", "Save button clicked");

            }
        });

    }
}
