package com.example.simplemeds;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddcardActivity extends AppCompatActivity {
    EditText nickname,cardno,cvv,exdate;
    Button addcardbtn;
    DatabaseReference dbRef;
    users user;
    card card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcard);
        dbRef = FirebaseDatabase.getInstance().getReference();
        addcardbtn = findViewById(R.id.updatecardbtn);
        cardno = findViewById(R.id.cardnumber);
        cvv = findViewById(R.id.cvv);
        exdate = findViewById(R.id.carddate);
        nickname = findViewById(R.id.nickname);
        user = new users();
        card = new card();


        addcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(nickname.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter card Nickname", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(cardno.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter card Card Number", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(cvv.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter card CVV", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(exdate.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter Expiry date", Toast.LENGTH_LONG).show();
                }
                else{

                    Intent userSession = getIntent();
                    String fn = userSession.getStringExtra("fn");

                    card.setNickname(nickname.getText().toString().trim());
                    card.setCardNo(cardno.getText().toString().trim());
                    card.setCvv(cvv.getText().toString().trim());
                    card.setExdate(exdate.getText().toString().trim());
                    card.setUser(fn);
                    dbRef.child("Card").child(card.getCardNo()).setValue(card);
                    Intent intent = new Intent (AddcardActivity.this, userhome.class);
                    startActivity(intent);

                }

            }
        });
    }
}



