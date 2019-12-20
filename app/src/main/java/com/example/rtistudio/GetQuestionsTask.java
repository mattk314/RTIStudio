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


public class GetQuestionsTask extends AsyncTask<String, Void, String> {


    public ToDoManager activity;

    public GetQuestionsTask(ToDoManager a){
        this.activity=a;
    }

    @Override
    protected String doInBackground(String... strings) {

        // Create data variable for sent values to server

        // GONNA NEED TO CHANGE THIS TO TOKEN AND COURSE ID
        String data = "token=" + strings[0] + "&id=" + strings[1];

        String text = "";
        BufferedReader reader = null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("https://rtistudio.com/api/questions");

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

        return text;
    }


    protected void onPostExecute(String result){

        Log.d("JSON Questions result", "Result for questions is: " + result);


        //Start questions activity and send "result" JSON string so questionsMenu can show questions to be answered.

        Intent intent = new Intent(activity, QuestionsMenu.class);
        intent.putExtra("com.example.RTIStudio.questions", result);
        activity.startActivity(intent);

    }

}
