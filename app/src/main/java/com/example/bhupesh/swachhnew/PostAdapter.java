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

public class PostAdapter extends  RecyclerView.Adapter <PostAdapter.OrgViewHolder> {
    Context c;
    ArrayList<Posts> players;

    String email;

    public PostAdapter(Context c, ArrayList<Posts> depts) {
        this.c = c;
        this.players=depts;

    }



    public static class OrgViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView Name,Description;
        String Image,Desc,email;

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
                    Intent intent=new Intent(context,ViewProblemExtend.class);
                    intent.putExtra("arg1",email);
                    intent.putExtra("arg2",Image);
                    intent.putExtra("arg3",Desc);
                    context.startActivity(intent);
                }
            });

        }





    }


    @Override
    public OrgViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dept_list_row, viewGroup, false);
        OrgViewHolder pvh = new OrgViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(OrgViewHolder orgViewHolder, final int i)
    {


        final String name = players.get(i).getUserName();
        final String detail=players.get(i).getDescription();
        final String image=players.get(i).getImage();


        {

            orgViewHolder.Name.setText(players.get(i).getUserName());
            orgViewHolder.Description.setText(players.get(i).getDescription());
            orgViewHolder.email=players.get(i).getUserEmail();
            orgViewHolder.Desc=players.get(i).getDescription();
            orgViewHolder.Image=players.get(i).getImage();

        }





    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}