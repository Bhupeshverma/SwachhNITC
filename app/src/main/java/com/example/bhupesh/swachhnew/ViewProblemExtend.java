package com.example.bhupesh.swachhnew;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static android.R.attr.id;

public class ViewProblemExtend extends AppCompatActivity {

Context context;
    TextView t1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem_extend);
        img=(ImageView)findViewById(R.id.image);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        t1=(TextView)findViewById(R.id.place_detail);

        String image=getIntent().getExtras().getString("arg2");
        String desc=getIntent().getExtras().getString("arg3");
        t1.setText(desc);
        if(image.isEmpty())
        {
            Picasso.with(getApplicationContext()).load("http://andromeda.nitc.ac.in/~m140388ca/im.gif").into(img);
        }
        else
        {
            Picasso.with(getApplicationContext()).load(image).into(img);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
