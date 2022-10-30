package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    EditText Lfntxt, passtxt;
    Button Btn, adminbtn, regbtn, backbtn;
    DatabaseReference dbRef;
    users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        adminbtn = findViewById(R.id.adminbtn);
        regbtn = findViewById(R.id.reg);
        Btn = findViewById(R.id.updatecardbtn);
        Lfntxt = findViewById(R.id.Lfntxt);
        passtxt = findViewById(R.id.passtxt);
        backbtn = findViewById(R.id.backbtn3);

        user = new users();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, adminLogin.class);
                startActivity(intent);
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
                String fn = Lfntxt.getText().toString().trim();
                String pass = passtxt.getText().toString().trim();

                if( TextUtils.isEmpty(Lfntxt.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(passtxt.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_LONG).show();
                }
                else{
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if( snapshot.hasChild(fn) ){
                                String getpass = snapshot.child(fn).child("password").getValue(String.class);

                                if( getpass.equals(pass)){
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                                    Intent userSession = new Intent(login.this, userhome.class);
                                    userSession.putExtra("fn", fn);
                                    startActivity(userSession);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}
