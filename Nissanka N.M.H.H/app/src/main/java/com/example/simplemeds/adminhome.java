package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class adminhome extends AppCompatActivity {

    EditText searchfield;
    Button searchBtn, logoutbtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhome);

        searchBtn = findViewById(R.id.adminsearch);
        logoutbtn = findViewById(R.id.adminlogout);
        searchfield = findViewById(R.id.adminsearchfield);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = searchfield.getText().toString().trim();

                Intent intent = new Intent(adminhome.this, adminsearch.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminhome.this, home.class);
                startActivity(intent);
            }
        });
    }
}
