package com.example.bhupesh.swachhnew;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ListContentFragment extends Fragment {

    private RecyclerView rv;

    String idvo;
    String url = "http://andromeda.nitc.ac.in/~m140388ca/deptList.php";

    Context c;
    public ListContentFragment(Context context) {
        // Required empty public constructor
        this.c=context;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recycler_view,null);
        // Inflate the layout for this fragment


        rv = (RecyclerView) v.findViewById(R.id.my_recycler_view);
       rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());

        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());


        Downloader d = new Downloader(this.getActivity(), url, rv);
        d.execute();



        return v;
    }

}