package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminsearch extends AppCompatActivity {

    EditText fname, email, tele, uid;
    DatabaseReference dbRef;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminsearch);

        fname = findViewById(R.id.searchname);
        fname.setEnabled(false);

        email = findViewById(R.id.searchemail);
        email.setEnabled(false);

        tele = findViewById(R.id.searchtele);
        tele.setEnabled(false);

        uid = findViewById(R.id.searchuid);
        uid.setEnabled(false);

        back = findViewById(R.id.adminsBack);

        Intent userSession = getIntent();
        String name = userSession.getStringExtra("name");

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(name);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.hasChildren() ){
                    fname.setText( snapshot.child("fullname").getValue(String.class) );
                    email.setText( snapshot.child("email").getValue(String.class) );
                    tele.setText( snapshot.child("telephone").getValue(String.class) );
                    uid.setText( snapshot.child("uID").getValue().toString() );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminsearch.this, adminhome.class);
                startActivity(intent);
            }
        });
    }
}
