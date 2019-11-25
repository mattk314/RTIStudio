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


public class GetToDoTask extends AsyncTask<String, Void, String> {

  public ToDoManager activity;

  public GetToDoTask(ToDoManager a){
    this.activity=a;
  }



  @Override
  protected String doInBackground(String... strings) {

    // Create data variable for sent values to server

    String data = "token=" + strings[0];

    String text = "";
    BufferedReader reader = null;

    // Send data
    try
    {
      // Defined URL  where to send data
      URL url = new URL("https://rtistudio.com/api/courses");

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
    ToDoManager.toDoList = result;
    Log.d("GetToDoTask returns", result);
  }
}