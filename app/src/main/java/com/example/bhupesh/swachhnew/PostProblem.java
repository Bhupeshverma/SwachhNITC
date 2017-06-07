package com.example.bhupesh.swachhnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostProblem extends AppCompatActivity {


    private static final int RC_PHOTO_PICKER = 1;
    private static final String TAG ="PostProblem" ;
    ImageButton b1,b2;
    ImageView img;
    EditText e1;
    TextView t1,t2;
    Uri selectedImageUri=null;
    ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    Uri  downloadUrl;
    private FirebaseAuth.AuthStateListener mAuthListener;


    String email,image,type,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_problem);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        progressDialog=new ProgressDialog(this);
        img=(ImageView)findViewById(R.id.imgbtn);
        b2=(ImageButton)findViewById(R.id.img_btn);
        e1=(EditText)findViewById(R.id.e);
        t2=(TextView)findViewById(R.id.text);
         email=getIntent().getExtras().getString("arg1");

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Log.d("Email",email);
         type=getIntent().getExtras().getString("arg2");
        t2.setText(email);

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                email=t2.getText().toString().trim();
                description=e1.getText().toString().trim();



                if (!TextUtils.isEmpty(description) && !TextUtils.isEmpty(email) )
                {
                    progressDialog.setMessage("Posting...");
                    progressDialog.show();
                    if(selectedImageUri==null){
                        FirebaseUser user = mAuth.getCurrentUser();
                        String username=user.getDisplayName();
                        String user_email=user.getEmail();
                        String user_id=user.getUid();

                        BackgroundPost post=new BackgroundPost(getApplicationContext());
                        post.execute(null,description,email,type,username,user_email,user_id);

                        progressDialog.dismiss();
                        Toast.makeText(PostProblem.this, "Posted Successfully..", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                        img.setImageURI(null);

                    }else
                    {
                        StorageReference filepath=mStorageRef.child("Post_images").child(selectedImageUri.getLastPathSegment());
                        filepath.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                downloadUrl=taskSnapshot.getDownloadUrl();
                                image=downloadUrl.toString();
                                FirebaseUser user = mAuth.getCurrentUser();
                                String username=user.getDisplayName();
                                String user_email=user.getEmail();
                                String user_id=user.getUid();

                                BackgroundPost post=new BackgroundPost(getApplicationContext());
                                post.execute(image,description,email,type,username,user_email,user_id);

                                progressDialog.dismiss();
                                e1.setText("");
                                img.setImageURI(null);

                                //startActivity(new Intent(getApplicationContext(),Post.class));

                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                String username=user.getDisplayName();
                                String user_email=user.getEmail();
                                String user_id=user.getUid();

                                BackgroundPost post=new BackgroundPost(getApplicationContext());
                                post.execute(null,description,email,type,username,user_email,user_id);

                                progressDialog.dismiss();
                                e1.setText("");
                                img.setImageURI(null);
                            }
                        });

                    }

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(PostProblem.this, "Description field should not be empty.", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_image)
        {
            pick_image();
        }
        if(id==R.id.home)
        {
           Intent intent=new Intent(this,Post.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void pick_image() {

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_PHOTO_PICKER && resultCode==RESULT_OK)
        {

            selectedImageUri = data.getData();

            img.setImageURI(selectedImageUri);


        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

