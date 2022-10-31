package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
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

public class profile extends AppCompatActivity {

    EditText fullname, email, tele, age, id;
    Button editprof, deleteprof, logoutbtn, backbtn;
    DatabaseReference dbRef;
    users user;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent userSession = getIntent();
        String fn = userSession.getStringExtra("fn");

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fn);

        fullname = findViewById(R.id.editFn);
        fullname.setEnabled(false);

        email = findViewById(R.id.editEmail);
        email.setEnabled(false);

        tele = findViewById(R.id.editTele);
        tele.setEnabled(false);

        age = findViewById(R.id.editAge);
        age.setEnabled(false);

        id = findViewById(R.id.edittxt5);
        id.setEnabled(false);

        editprof = findViewById(R.id.btn1);
        deleteprof = findViewById(R.id.deleteprofile);
        logoutbtn = findViewById(R.id.logoutbtn1);
        backbtn = findViewById(R.id.backbtn2);

        user = new users();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, userhome.class);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, home.class);
                startActivity(intent);
            }
        });

        deleteprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if( snapshot.hasChild(fn) ){
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(profile.this, home.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userSession = getIntent();
                String fn = userSession.getStringExtra("fn");

                Intent intent1 = new Intent(profile.this, profile_edit.class);
                intent1.putExtra("fn", fn);
                startActivity(intent1);
            }
        });

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.hasChildren() ){
                    fullname.setText(snapshot.child("fullname").getValue(String.class));
                    email.setText(snapshot.child("email").getValue(String.class));
                    tele.setText(snapshot.child("telephone").getValue(String.class));
                    age.setText(snapshot.child("age").getValue(String.class));
                    id.setText(snapshot.child("uID").getValue().toString());
                }

                deleteprof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(profile.this, home.class);
                        startActivity(intent);

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
