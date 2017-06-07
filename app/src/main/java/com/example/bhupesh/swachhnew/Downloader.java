package com.example.bhupesh.swachhnew;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dilraj on 18/3/17.
 */

public class Downloader extends AsyncTask<Void,Integer,String>
{


    Context c;
    String urlAddress;
    RecyclerView rv;


    public Downloader(Context c, String urlAddress, RecyclerView rv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(Void... params) {
        String data=this.downloadData();
        return data;
    }



    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        if (data!=null)
        {
            Parser p=new Parser(c,data,rv);
            p.execute();


        }
        else {

            Toast.makeText(c,"Unable to download",Toast.LENGTH_SHORT).show();
        }
    }


    private String downloadData()
    {
        String result="";


        try {

            URL url = new URL(urlAddress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            InputStream inputStreamReader = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStreamReader.close();
            httpURLConnection.disconnect();
            Log.d("RESULT",result);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
