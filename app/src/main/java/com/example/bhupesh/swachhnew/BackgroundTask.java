package com.example.bhupesh.swachhnew;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Mayank on 10/3/17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String...   params)
    {
        String reg_url="http://andromeda.nitc.ac.in/~m140388ca/reg.php";


        String method=params[0];
        if(method=="register")
        {
            String email=params[1];
            String username=params[2];
            String uid=params[3];

            try
            {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("identifier_email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("identifier_username","UTF-8") +"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("identifier_uid","UTF-8") +"="+URLEncoder.encode(uid,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                //return  "RESGISTRATION SUCCESS...";

            }
            catch(Exception e)
            {
//                Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG);
                Log.e("error msg", e.toString());
            }




        }

        return "Welcome...";
    }

    @Override
    protected void onProgressUpdate(Void...  values) {
    }

    @Override
    protected  void onPostExecute(String result)
    {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

}