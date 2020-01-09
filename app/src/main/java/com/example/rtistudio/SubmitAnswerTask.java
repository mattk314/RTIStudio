package com.example.rtistudio;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Struct;


public class SubmitAnswerTask extends AsyncTask<String, Void, String> {

    public SubmitAnswerTask(QuestionsMenu a){
        this.activity=a;
    }


    public QuestionsMenu activity;


    String token = "";

    @Override
    protected String doInBackground(String... strings) {

        // Create data variable for sent values to server

        token = strings[0];

        String data = "token=" + token + "&id=" + strings[1] + "&answer=" + strings[2];

        String text = "";
        BufferedReader reader = null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("https://rtistudio.com/api/answer");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter wr = new OutputStreamWriter(outputStream);
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();

            Log.d("API response", sb.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }

            catch(Exception ex) {
                ex.printStackTrace();
                text += "Exception ex 2";
            }
        }

        return "" + strings[1]+ ", " + strings[2];
    }

    protected void onPostExecute(String idAndAnswer){

        Log.d("submitAnswerTask return", idAndAnswer);

        // Probably go back to the list of courses
        //No don't do that, do that when the loop in QuestionsMenu is finished.

    }

}
