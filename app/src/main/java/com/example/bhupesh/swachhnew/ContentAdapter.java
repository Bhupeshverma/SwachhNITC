package com.example.bhupesh.swachhnew;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by dilraj on 18/3/17.
 */

public class ContentAdapter extends  RecyclerView.Adapter <ContentAdapter.OrgViewHolder> {
    Context c;
    ArrayList<Departments> players;

     String email;

    public ContentAdapter(Context c, ArrayList<Departments> depts) {
        this.c = c;
        this.players=depts;

    }



    public static class OrgViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView Name,Description;
        String email,type;

        //TextView descrip;




        OrgViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card);
            Name=(TextView)itemView.findViewById(R.id.textView5);
            Description=(TextView)itemView.findViewById(R.id.textView8);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context=v.getContext();
                    Intent intent=new Intent(context,PostProblem.class);
                    intent.putExtra("arg1",email);
                    intent.putExtra("arg2",type);
                    context.startActivity(intent);
                }
            });

        }





    }


    @Override
    public OrgViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        OrgViewHolder pvh = new OrgViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(OrgViewHolder orgViewHolder, final int i)
    {


        final String name = players.get(i).getType();
        final String detail=players.get(i).getDescription();
        final String email=players.get(i).getEmail();


        {

            orgViewHolder.Name.setText(players.get(i).getType());
            orgViewHolder.Description.setText(players.get(i).getDescription());
            orgViewHolder.email=players.get(i).getEmail();
            orgViewHolder.type=players.get(i).getType();
        }





    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}