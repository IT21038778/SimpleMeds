package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userhome extends AppCompatActivity {

    Button shop, prof, logoutbtn, addpaymentbtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);

        shop = findViewById(R.id.update);
        prof = findViewById(R.id.remove);
        logoutbtn = findViewById(R.id.logoutbtn2);
        addpaymentbtn = findViewById(R.id.managepaybtn);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
        addpaymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhome.this, ManagecardsActivity.class);
                startActivity(intent);
            }
        });


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhome.this, home.class);
                startActivity(intent);
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent1 = new Intent(userhome.this, profile.class);
                intent1.putExtra("fn", fn);
                startActivity(intent1);
            }
        });

    }
}
