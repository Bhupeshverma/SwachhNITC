package com.example.bhupesh.swachhnew;

import android.app.ProgressDialog;
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

/**
 * Created by bhupesh on 6/5/17.
 *
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

public class BackgroundPost extends AsyncTask<String,Void,String> {

    Context ctx;


    BackgroundPost(Context ctx)
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
        String reg_url="http://andromeda.nitc.ac.in/~m140388ca/post.php";


             String image=params[0];

            String description=params[1];
            String department_email=params[2];
            String department_name=params[3];
            String username=params[4];
            String user_email=params[5];
            String uid=params[6];


            try
            {

                if(image==null)
                {

                    URL url=new URL(reg_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    String data= URLEncoder.encode("identifier_description","UTF-8") +"="+URLEncoder.encode(description,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_department_email","UTF-8") +"="+URLEncoder.encode(department_email,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_department_name","UTF-8") +"="+URLEncoder.encode(department_name,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_username","UTF-8") +"="+URLEncoder.encode(username,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_user_email","UTF-8") +"="+URLEncoder.encode(user_email,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_uid","UTF-8") +"="+URLEncoder.encode(uid,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is=httpURLConnection.getInputStream();
                    is.close();
                    return  "Posted Successfully...";

                }
                else {
                    URL url=new URL(reg_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    String data= URLEncoder.encode("identifier_image","UTF-8") +"="+URLEncoder.encode(image,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_description","UTF-8") +"="+URLEncoder.encode(description,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_department_email","UTF-8") +"="+URLEncoder.encode(department_email,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_department_name","UTF-8") +"="+URLEncoder.encode(department_name,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_username","UTF-8") +"="+URLEncoder.encode(username,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_user_email","UTF-8") +"="+URLEncoder.encode(user_email,"UTF-8")+"&"+
                            URLEncoder.encode("identifier_uid","UTF-8") +"="+URLEncoder.encode(uid,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is=httpURLConnection.getInputStream();
                    is.close();

                    return  "Posted Successfully...";
                }




            }
            catch(Exception e)
            {
//                Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG);
                Log.e("error msg", e.toString());
            }






        return "posting unsuccessfull";
    }

    @Override
    protected void onProgressUpdate(Void...  values) {

    }

    @Override
    protected  void onPostExecute(String result)
    {


    }

}