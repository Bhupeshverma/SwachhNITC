package com.example.bhupesh.swachhnew;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeptList extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1;

    String type,email,phone,description;
    DatabaseReference dataref;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_list);

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        b1=(Button)findViewById(R.id.button2);
        dataref= FirebaseDatabase.getInstance().getReference().child("Departments");
        progress=new ProgressDialog(this);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                type=e1.getText().toString().trim();
                email=e2.getText().toString().trim();
                phone=e3.getText().toString().trim();
                description=e4.getText().toString().trim();
                String method="DeptRegister";

                if(!TextUtils.isEmpty(type) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(description))
                {

                    progress.setMessage("Registering department..");
                    progress.show();
                    Departments data=new Departments(type,email,phone,description);
                    dataref.push().setValue(data);


                    BackgroundTaskDept btask=new BackgroundTaskDept(getApplicationContext());
                    btask.execute(method,type,email,phone,description);

                    progress.dismiss();

                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                }
                else {
                    Toast.makeText(DeptList.this, "Rgeistration failed..", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
