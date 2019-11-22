package com.example.rtistudio;

public class QuestionAnswer {
    private String question;
    private String answer;

    public QuestionAnswer(String q){
        question = q;
        answer = "";
    }

    public QuestionAnswer(String q, String a){
        question = q;
        answer = a;
    }

    public String setAnswer(String a){
        answer = a;
        return answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

}
