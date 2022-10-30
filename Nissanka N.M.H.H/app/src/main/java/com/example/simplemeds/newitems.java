package com.example.simplemeds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class newitems extends AppCompatActivity {

    EditText IEXPD;
    EditText Icode;
    EditText Iname;
    EditText Iquatity;
    EditText IMFD;
    EditText Iprice;
    Button add;
    Button addImage;
    DatabaseReference dbRef;
    items items;
    long itemId = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitems);

        dbRef = FirebaseDatabase.getInstance().getReference().child("items");
        add = findViewById(R.id.add);
        Icode = findViewById(R.id.Icode);
        IEXPD = findViewById(R.id.IEXPD);
        Iname = findViewById(R.id.Iname);
        Iquatity = findViewById(R.id.Iquatity);
        IMFD = findViewById(R.id.IMFD);
        Iprice = findViewById(R.id.Iprice);
        imageView = findViewById(R.id.imageView);


        items = new items();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    itemId = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( TextUtils.isEmpty(Icode.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Item Code", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(IEXPD.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Expiry Date for Product", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(Iname.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Name of Product", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(Iquatity.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter quantity of product", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(IMFD.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter manufactured date of Product", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(Iprice.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter price of product", Toast.LENGTH_LONG).show();
                }
                else{
                    items.setItemCode(Icode.getText().toString().trim());
                    items.setExpd(IEXPD.getText().toString().trim());
                    items.setMfd(IMFD.getText().toString().trim());
                    items.setName(Iname.getText().toString().trim());
                    items.setQuantity(Iquatity.getText().toString().trim());
                    items.setPrice(Iprice.getText().toString().trim());
                    items.setItemId(itemId + 1);

                    final String ItemCode = items.getItemCode();

                    dbRef.child(ItemCode).setValue(items);

                    Intent intent = new Intent(newitems.this, search.class);
                    startActivity(intent);
                }

               }
            }
        );
    }
}
