package com.example.rtistudio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private String[] questions;
    private Integer[] types;
    private Context context;

    public CustomAdapter(Context context, int textViewResourceId, Integer[] types, String[] questions) {
        super(context, textViewResourceId, types);

        this.questions = questions;
        this.types = types;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        /*LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.answer_yesno, null);
        TextView textView = view.findViewById(R.id.questionsName);
        textView.setText(questions[position]);

        Switch yesnoAnswerSwitch = (Switch) view.findViewById(R.id.yesnoAnswerSwitch);
        EditText answerEditText = (EditText) view.findViewById(R.id.answerEditText);
        SeekBar answerSeekBar = (SeekBar) view.findViewById(R.id.answerSeekBar);

        if(types[position]==0){
            //text
            yesnoAnswerSwitch.setVisibility(View.GONE);
            answerSeekBar.setVisibility(View.GONE);
        }
        if(types[position]==1){
            //slider
            yesnoAnswerSwitch.setVisibility(View.GONE);
            answerEditText.setVisibility(View.GONE);
        }
        if(types[position]==2){
            //toggle
            answerSeekBar.setVisibility(View.GONE);
            answerEditText.setVisibility(View.GONE);
        }

        return view;*/
        return null;
    }

}


//This is the tutorial being used (maybe good maybe not): https://demonuts.com/android-custom-arrayadapter/