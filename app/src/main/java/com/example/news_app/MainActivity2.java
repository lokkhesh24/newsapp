package com.example.news_app;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    EditText username;
    EditText pass;
    Button loginButton;
    TextView registernow;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registernow = findViewById(R.id.signupText);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);


        registernow.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity2.this,signup.class);
            startActivity(intent);
            finish();
        });


        loginButton.setOnClickListener(view -> {
            String email,password;
            email=username.getText().toString();
            password=pass.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            if(TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity2.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                return;

            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(MainActivity2.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                return;

            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(MainActivity2.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });
    }
}