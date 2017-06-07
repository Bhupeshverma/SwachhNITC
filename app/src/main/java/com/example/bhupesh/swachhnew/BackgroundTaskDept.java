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

/**
 * Created by bhupesh on 30/4/17.
 */

public class BackgroundTaskDept extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTaskDept(Context ctx)
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

        String reg_url="http://andromeda.nitc.ac.in/~m140388ca/deptreg.php";

        String method=params[0];
        if(method=="DeptRegister")
        {
            String type=params[1];
            String email=params[2];
            String phone=params[3];
            String description=params[4];
            try
            {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("identifier_type","UTF-8") +"="+URLEncoder.encode(type,"UTF-8")+"&"+
                        URLEncoder.encode("identifier_email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("identifier_phone","UTF-8") +"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("identifier_description","UTF-8") +"="+URLEncoder.encode(description,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return  "RESGISTRATION SUCCESS...";

            }
            catch(Exception e)
            {
//                Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG);
                Log.e("error msg", e.toString());
            }




        }
        return null;
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
