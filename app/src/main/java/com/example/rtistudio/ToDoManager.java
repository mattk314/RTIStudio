package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ToDoManager extends AppCompatActivity {
    static String toDoList;
    String token;

    ToDoManager instance;
    ListView toDoListView;
    Button backToLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_manager);

        instance = this;

        token = getIntent().getExtras().getString("com.example.RTIStudio.theToken");

        toDoListView = (ListView) findViewById(R.id.toDoListView);
        backToLoginButton = (Button) findViewById(R.id.backToLoginButton);

        new GetToDoTask(instance).execute(token);


        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Click", "Back button clicked");

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                instance.startActivity(i);
            }
        });
    }

    public void listAdd (final JSONArray jsonArray){


        ArrayList<String> itemsToDo = new ArrayList<String>(); //Will be the student object?

        try {
            for (int i=0;i<jsonArray.length();i++) {

                String courseName = jsonArray.getJSONObject(i).getString("course_name");
                String studentName = jsonArray.getJSONObject(i).getString("student_name");

                String status="";
                if(jsonArray.getJSONObject(i).getString("answered_today").equals("true")){
                    status = "has been answered";
                }
                else if(jsonArray.getJSONObject(i).getString("overdue").equals("true")){
                    status = "overdue";
                }
                else{
                    status = jsonArray.getJSONObject(i).getString("reminder_time");
                }

                itemsToDo.add(courseName + ", " + studentName + ", " + status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsToDo);

        toDoListView.setAdapter(arrayAdapter);

        ///////////////////////////////////////////////////////////////////////////////////////////


        //get questions for course clicked by sending token and courseId to API
        //to do this, get courseID somehow.
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Item clicked", "" + position);

                String courseId="";

                try {
                    courseId = jsonArray.getJSONObject(position).getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("Clicked course id",courseId);

                new GetQuestionsTask().execute(token, courseId);

            }
        });


    }
}
