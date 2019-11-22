package com.example.rtistudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoManager extends AppCompatActivity {
    static String toDoList;

    ToDoManager instance;
    ListView toDoListView;
    Button backToLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_manager);

        instance = this;
        String token = getIntent().getExtras().getString("com.example.RTIStudio.theToken");

        toDoListView = (ListView) findViewById(R.id.toDoListView);
        backToLoginButton = (Button) findViewById(R.id.backToLoginButton);

        new GetToDoTask(instance).execute(token);


        ArrayList<String> itemsToDo = new ArrayList<String>(); //Will be the student object?

        itemsToDo.add(token);
        itemsToDo.add("testing");
        itemsToDo.add("hello");
        itemsToDo.add("12345");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsToDo);

        toDoListView.setAdapter(arrayAdapter);

        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                instance.startActivity(i);
            }
        });
    }
}
