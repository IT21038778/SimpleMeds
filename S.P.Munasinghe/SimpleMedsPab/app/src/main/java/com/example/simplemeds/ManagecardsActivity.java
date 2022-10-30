package com.example.simplemeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class ManagecardsActivity extends AppCompatActivity {
    Button viewcard, addcardbtn,logoutbtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managecards);

        addcardbtn = findViewById(R.id.update);
        viewcard = findViewById(R.id.remove);
        logoutbtn = findViewById(R.id.logoutbtn2);
        addcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagecardsActivity.this, AddcardActivity.class);
                startActivity(intent);
            }
        });
        viewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagecardsActivity.this, Viewcard.class);
                startActivity(intent);
            }
        });


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagecardsActivity.this, home.class);
                startActivity(intent);
            }
        });
    }
}