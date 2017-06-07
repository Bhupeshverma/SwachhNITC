package com.example.bhupesh.swachhnew;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dilraj on 18/3/17.
 */

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data;
    RecyclerView rv;



    ArrayList<com.example.bhupesh.swachhnew.Departments> depts=new ArrayList<>();
    ContentAdapter adapter;

    public Parser(Context c, String data, RecyclerView rv) {
        this.c = c;
        this.data = data;
        this.rv = rv;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();



    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();


    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);


        if (integer==1)
        {
            adapter=new ContentAdapter(c,depts);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(c,"Unable to Parse data",Toast.LENGTH_SHORT).show();
        }
    }

    private int parse()
    {
        try
        {

            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");

         //   JSONArray ja=new JSONArray(data);
            JSONObject jo=null;
            depts.clear();

            Departments d;
            for(int i=0;i<jsonArray.length();i++)
            {
                jo = jsonArray.getJSONObject(i);
                Departments dp=new Departments(jo.getString("type"),jo.getString("email"),jo.getString("phone"),jo.getString("description"));
              /*  String name = jo.getString("type");
                String description = jo.getString("Description");


                d = new Dept();
                d.setType(name);
                d.setDescription(description);*/
                depts.add(dp);
            }

            Log.d("JSON_STRING",data);
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }


}

