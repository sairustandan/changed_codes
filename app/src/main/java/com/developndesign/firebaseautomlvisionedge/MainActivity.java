package com.developndesign.firebaseautomlvisionedge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailID, password;
    Button btn_login, btn_register;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


        // so chaii register click garyo bhaney
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();

                String pwd = password.getText().toString();

                if (email.isEmpty()){
                    emailID.setError("Please enter EmailId");
                    emailID.requestFocus();

                }
                else if(pwd.isEmpty()){
                    password.setError("Enter your password");
                    password.requestFocus();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Signup Unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }

            }

        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}