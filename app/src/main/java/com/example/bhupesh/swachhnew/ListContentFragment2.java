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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ListContentFragment2 extends Fragment {

    private RecyclerView rv;

    String idvo;
    String url = "http://andromeda.nitc.ac.in/~m140388ca/myPost.php";

    Context c;
    public ListContentFragment2(Context context) {
        // Required empty public constructor
        this.c=context;

    }
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recycler_view,null);
        // Inflate the layout for this fragment


        rv = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        mAuth = FirebaseAuth.getInstance();
        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());
        FirebaseUser user=mAuth.getCurrentUser();
        String username=user.getDisplayName();
        String uid=user.getUid();
        String user_email=user.getEmail();


        DownloaderFetch d = new DownloaderFetch(this.getActivity(), url, rv);
        d.execute(user_email);



        return v;
    }

}