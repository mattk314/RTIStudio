package com.example.rtistudio;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class GetTokenTask extends AsyncTask<String, Void, String> {

    public MainActivity activity;

    public GetTokenTask(MainActivity a){
        this.activity=a;
    }



    @Override
    protected String doInBackground(String... strings) {



        // Create data variable for sent values to server

        String data = "email=" + strings[0] + "&password=" + strings[1];

        String text = "";
        BufferedReader reader = null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("https://rtistudio.com/api/token");

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
            text += " Exception ex 1";
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
        if(result.contains("invalid login")){
            activity.incorrectLogin();
        }
        else {

            Log.d("RESULT HERE", result);
            Intent intent = new Intent(activity, ToDoManager.class);
            intent.putExtra("com.example.RTIStudio.theToken", result);
            activity.startActivity(intent);
            // activity.setToken(result);
        }
    }
}
