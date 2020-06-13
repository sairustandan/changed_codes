package com.developndesign.firebaseautomlvisionedge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

        Button btn_show;
        EditText search_field;
        TextView et_id, et_status, et_age;
        DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_show = findViewById(R.id.btn_show);

        et_id = findViewById(R.id.et_id);

        et_age = findViewById(R.id.et_age);

        et_status = findViewById(R.id.et_status);
/*
        et_status = findViewById(R.id.et_status);

        et_age = findViewById(R.id.et_age);*/

        search_field = findViewById(R.id.search_field);


        final String sairus= "21, 01, Yes";
        final String megha = "20, 02, Yes";
        final String jenisha = "21, 03, No";
        final String alishan = "20, 04, Yes";


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String criminal= search_field.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(criminal);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      /*String name = dataSnapshot.child("name").getValue().toString();
                        String status = dataSnapshot.child("criminal").getValue().toString();
                        String age = dataSnapshot.child("age").getValue().toString();*/



                        if (criminal.equals("sairus")){
                            String[] arrSplit = sairus.split(",");
                            for (int i = 0; i<arrSplit.length; i++){
                                et_age.setText(arrSplit[0]);
                                et_id.setText(arrSplit[1]);
                                et_status.setText(arrSplit[2]);
                                //System.out.println(arrSplit[i]);
                            }
                        }
                        else if(criminal.equals("megha")){
                            et_id.setText(megha);
                        }
                        else if(criminal.equals("jenisha")){
                            et_id.setText(jenisha);
                        }
                        else if(criminal.equals("alishan")){

                            et_id.setText(alishan);
                        }
                        else {
                            et_id.setText("No Record of that person");
                            }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}