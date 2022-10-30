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

    EditText Lfntxt, passtxt;
    Button Btn, backbtn;
    DatabaseReference dbRef;
    users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        backbtn = findViewById(R.id.backbtn4);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminLogin.this, home.class);
                startActivity(intent);
            }
        });

    }
}
