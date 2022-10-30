package com.example.simplemeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update extends AppCompatActivity {

    EditText displayname;
    EditText displayquantity;
    EditText displaymfd;
    EditText displayexpd;
    EditText displayprice;
    EditText itemcode;
    Button update;
    DatabaseReference dbRef;
    items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbRef = FirebaseDatabase.getInstance().getReference().child("items");

        displayname = findViewById(R.id.rdisplayname);
        displayquantity = findViewById(R.id.rdisplayquantity);
        displaymfd = findViewById(R.id.rdisplaymfd);
        displayexpd = findViewById(R.id.rdisplayexpd);
        displayprice = findViewById(R.id.rdisplayprice);
        update = findViewById(R.id.update);
        itemcode = findViewById(R.id.ritemcodes);
        itemcode.setEnabled(false);

        items = new items();

        Intent itemSession = getIntent();
        String icode = itemSession.getStringExtra("icode");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(displayname.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Enter new Item Name", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(displayquantity.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter updated item Quantity", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(displaymfd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter updated item MFD", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(displayexpd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter updated item EXPD", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(displayprice.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter updated item Price", Toast.LENGTH_LONG).show();
                }
                else{
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(icode)){
                                items.setName(displayname.getText().toString().trim());
                                items.setQuantity(displayquantity.getText().toString().trim());
                                items.setMfd(displaymfd.getText().toString().trim());
                                items.setExpd(displayexpd.getText().toString().trim());
                                items.setPrice(displayprice.getText().toString().trim());

                                final String itemcode = items.getItemCode();

                                dbRef.child(icode).child("name").setValue(items.getName());
                                dbRef.child(icode).child("quantity").setValue(items.getQuantity());
                                dbRef.child(icode).child("mfd").setValue(items.getMfd());
                                dbRef.child(icode).child("expd").setValue(items.getExpd());
                                dbRef.child(icode).child("price").setValue(items.getPrice());

                                Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(update.this, search.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
    }
}