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

public class signup extends AppCompatActivity {

    EditText emailtxt, passtxt, fntext, teltext, Agetxt, conftext;
    Button Btn, loginredirrect;
    DatabaseReference dbRef;
    users user;
    long uID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        dbRef = FirebaseDatabase.getInstance().getReference();
        Btn = findViewById(R.id.adminlogin);
        loginredirrect = findViewById(R.id.loginreddirect);
        emailtxt = findViewById(R.id.emailtext);
        passtxt = findViewById(R.id.passtext);
        fntext = findViewById(R.id.fntext);
        teltext = findViewById(R.id.teletxt);
        Agetxt = findViewById(R.id.Agetxt);
        conftext = findViewById(R.id.conftext);

        user = new users();

        loginredirrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    uID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( TextUtils.isEmpty(emailtxt.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(passtxt.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(fntext.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your full name", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(teltext.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your telephone", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(Agetxt.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your age", Toast.LENGTH_LONG).show();
                }
                else if( !passtxt.getText().toString().equals(conftext.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if( !(passtxt.getText().toString().length() > 8) ){
                    Toast.makeText(getApplicationContext(), "Password must be longer than 8 characters", Toast.LENGTH_LONG).show();
                }
                else if( teltext.getText().toString().length() < 10 && teltext.getText().toString().length() > 10 ){
                    Toast.makeText(getApplicationContext(), "Your telephone number must have 10 numbers", Toast.LENGTH_LONG).show();
                }
                else{
                    user.setFullname(fntext.getText().toString().trim());
                    user.setEmail(emailtxt.getText().toString().trim());
                    user.setPassword(passtxt.getText().toString().trim());
                    user.setTelephone(teltext.getText().toString().trim());
                    user.setAge(Agetxt.getText().toString().trim());
                    user.setuID(uID);

                    final String name = user.getFullname();

                    dbRef.child("Users").child(name).setValue(user);

                    Intent intent = new Intent(signup.this, login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
