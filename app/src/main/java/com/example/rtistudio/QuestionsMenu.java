package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionsMenu extends AppCompatActivity {

    ArrayList<Integer> AnswerTypes;
    LinearLayout questionsListView;
    Button saveButton;
    Button backButton;

    QuestionsMenu instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_menu);

        String questionsJSON = getIntent().getExtras().getString("com.example.RTIStudio.questions");

        questionsListView = findViewById(R.id.QuestionsListViewLayout);

        ArrayList<String> QuestionsListItems = new ArrayList<String>();
        AnswerTypes = new ArrayList<Integer>();


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

        //Make question views
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i=0;i< QuestionsListItems.size();i++) {
            View view = null;

            if (AnswerTypes.get(i) == 0) {
                //text
                view = inflater.inflate(R.layout.answer_textbox, null);
                EditText answerEditText = view.findViewById(R.id.answer);
                //TODO Load the answers into these boxes if the question has already been answered
            }
            if (AnswerTypes.get(i) == 1) {
                //slider
                view = inflater.inflate(R.layout.answer_slider, null);
                SeekBar answerSeekBar = view.findViewById(R.id.answer);

            }
            if (AnswerTypes.get(i) == 2) {
                //toggle
                view = inflater.inflate(R.layout.answer_yesno, null);
                Switch yesnoAnswerSwitch = view.findViewById(R.id.answer);
            }

            TextView textView = view.findViewById(R.id.questionsName);
            textView.setText(QuestionsListItems.get(i));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            questionsListView.addView(view);
        }

        backButton = (Button) findViewById(R.id.backButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        instance = this;

        final String token = getIntent().getExtras().getString("com.example.RTIStudio.tokenFromGetQuestions");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Click", "Back button clicked");

                //need to make this work better

                Intent i = new Intent(getApplicationContext(), ToDoManager.class);
                i.putExtra("com.example.RTIStudio.theToken", token);
                instance.startActivity(i);
        }});



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", "Save button clicked");

                for(int i = 0; i< questionsListView.getChildCount(); i++){
                    if(AnswerTypes.get(i) == 0){
                        EditText editText = questionsListView.getChildAt(i).findViewById(R.id.answer);
                        Log.e("text answer", editText.getText().toString());
                    }
                    if(AnswerTypes.get(i) == 1){
                        SeekBar slider = questionsListView.getChildAt(i).findViewById(R.id.answer);
                        Log.e("slider answer", slider.getProgress() + "");
                    }
                    if(AnswerTypes.get(i) == 2){
                        Switch toggle = questionsListView.getChildAt(i).findViewById(R.id.answer);
                        Log.e("toggle answer", toggle.isChecked() ? "true" : "false");
                    }
                }

                //TODO save this data
            }
        });

    }
}

//Do this to fix the thing with the edittext weird thing:
// https://vikaskanani.wordpress.com/2011/07/27/android-focusable-edittext-inside-listview/
