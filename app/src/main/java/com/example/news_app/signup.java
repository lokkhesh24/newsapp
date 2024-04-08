package com.example.news_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText newusername;
    EditText newpass;
    EditText confirmpass;
    Button createButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView loginnow;



    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newusername = findViewById(R.id.username);
        newpass = findViewById(R.id.password);
        confirmpass = findViewById(R.id.conpassword);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        loginnow=findViewById(R.id.loginText);

        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email,password;
            email=newusername.getText().toString();
            password=newpass.getText().toString();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(signup.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                return;

            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(signup.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                return;

            }
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "Account Created.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });
        loginnow.setOnClickListener(view -> {
            Intent intent=new Intent(signup.this,MainActivity2.class);
            startActivity(intent);
            finish();
        });
    }
}