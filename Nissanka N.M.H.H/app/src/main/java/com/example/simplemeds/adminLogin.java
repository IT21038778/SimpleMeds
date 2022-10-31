package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class adminLogin extends AppCompatActivity {

    EditText adminfn, adminpass;
    Button Btn, backbtn;
    DatabaseReference dbRef;
    users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        Btn = findViewById(R.id.adminlogin);
        backbtn = findViewById(R.id.backbtn4);
        adminfn = findViewById(R.id.adminfn);
        adminpass = findViewById(R.id.adminpass);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminLogin.this, home.class);
                startActivity(intent);
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( adminfn.getText().toString().trim().equals("Admin") && adminpass.getText().toString().trim().equals("12345678") ){
                    Intent intent = new Intent(adminLogin.this, adminhome.class);
                    startActivity(intent);
                }
            }
        });
    }
}
