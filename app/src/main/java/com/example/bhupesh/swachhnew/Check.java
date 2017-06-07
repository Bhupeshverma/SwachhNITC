package com.example.bhupesh.swachhnew;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.TokenWatcher;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by bhupesh on 6/5/17.
 */

class Check extends AsyncTask<String,Void,String>{

    Context context;
    public Check(Context applicationContext) {
        context=applicationContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String check_url="http://andromeda.nitc.ac.in/~m140388ca/check.php";

        String email=params[0];

        try
        {
            URL url=new URL(check_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String data= URLEncoder.encode("identifier_email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream is=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String msg="";
            String ch;
            while((ch=bufferedReader.readLine())!=null)
            {
                    msg+=ch;
            }
            bufferedReader.close();
            is.close();
            return msg;

        }
        catch(Exception e)
        {
//                Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG);
            Log.e("error msg", e.toString());
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {

        Log.d("mayak", result);


        if(result.equals("user"))
        {
            Intent resultSpecifier = new Intent(context, Post.class);
            resultSpecifier.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(resultSpecifier);
        }
        else {
            Intent resultSpecifier = new Intent(context, ViewPost.class);
            resultSpecifier.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(resultSpecifier);
        }
    }
}
