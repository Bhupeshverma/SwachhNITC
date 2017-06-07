package com.example.bhupesh.swachhnew;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ViewPostFragment extends Fragment {



    private RecyclerView rv;

    String url = "http://andromeda.nitc.ac.in/~m140388ca/viewPost.php";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Context c;
    public ViewPostFragment(Context context) {
        // Required empty public constructor
        c=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.recycler_view,null);
        mAuth = FirebaseAuth.getInstance();

        rv = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());

        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());
        FirebaseUser user=mAuth.getCurrentUser();
        String username=user.getDisplayName();
        String uid=user.getUid();
        String user_email=user.getEmail();



        DownloaderPost d = new DownloaderPost(this.getActivity(), url, rv);
        d.execute(user_email);



        return v;
    }





}
