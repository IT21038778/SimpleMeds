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

public class EditcardActivity extends AppCompatActivity {
    EditText nickname,cvv,exdate;
    Button updatecardbtn;
    DatabaseReference dbRef;
    users user;
    card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcard);
        dbRef = FirebaseDatabase.getInstance().getReference();
        updatecardbtn = findViewById(R.id.updatecardbtn);
        cvv = findViewById(R.id.cvv);
        exdate = findViewById(R.id.carddate);
        nickname = findViewById(R.id.nickname);
        user = new users();
        card = new card();

        Intent userSession = getIntent();
        String fn = userSession.getStringExtra("fn");
        updatecardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(nickname.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter a nickname", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(cvv.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter CVV", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(exdate.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_LONG).show();
                }
                else{
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if( snapshot.hasChild(fn) ) {

                                card.setNickname(nickname.getText().toString().trim());
                                card.setCvv(cvv.getText().toString().trim());
                                card.setExdate(exdate.getText().toString().trim());
                                final String name = user.getFullname();

                                dbRef.child(fn).child("nickname").setValue(card.getNickname());
                                dbRef.child(fn).child("cvv").setValue(card.getCvv());
                                dbRef.child(fn).child("exdate").setValue(card.getExdate());


                                Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(EditcardActivity.this, Viewcard.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}