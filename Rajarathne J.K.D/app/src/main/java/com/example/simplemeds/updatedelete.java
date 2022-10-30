package com.example.simplemeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updatedelete extends AppCompatActivity {

    EditText displayname;
    EditText displayquantity;
    EditText displaymfd;
    EditText displayexpd;
    EditText displayprice;
    EditText ritemcodes;
    Button update;
    Button delete;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete);

        displayname = findViewById(R.id.rdisplayname);
        displayname.setEnabled(false);

        displayquantity = findViewById(R.id.rdisplayquantity);
        displayquantity.setEnabled(false);

        displaymfd = findViewById(R.id.rdisplaymfd);
        displaymfd.setEnabled(false);

        displayexpd = findViewById(R.id.rdisplayexpd);
        displayexpd.setEnabled(false);

        displayprice = findViewById(R.id.rdisplayprice);
        displayprice.setEnabled(false);

        ritemcodes = findViewById(R.id.ritemcodes);
        ritemcodes.setEnabled(false);

        Intent userSession = getIntent();
        String icode = userSession.getStringExtra("icode");

        dbRef = FirebaseDatabase.getInstance().getReference().child("items").child(icode);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    ritemcodes.setText(dataSnapshot.child("itemCode").getValue(String.class));
                    displayname.setText(dataSnapshot.child("name").getValue(String.class));
                    displayexpd.setText(dataSnapshot.child("expd").getValue(String.class));
                    displaymfd.setText(dataSnapshot.child("mfd").getValue(String.class));
                    displayprice.setText(dataSnapshot.child("price").getValue(String.class));
                    displayquantity.setText(dataSnapshot.child("quantity").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        update = findViewById(R.id.update1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updatedelete.this, update.class);
                intent.putExtra("icode", icode);
                startActivity(intent);
            }
        });

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dbRef.removeValue();

                        Toast.makeText(getApplicationContext(),"Item Removed", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(updatedelete.this, search.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}