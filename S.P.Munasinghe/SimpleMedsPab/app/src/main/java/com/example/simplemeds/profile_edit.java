package com.example.simplemeds;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class profile_edit extends AppCompatActivity {

    EditText fullname, email, telephone, age, editpass, editpassconf;
    Button btn2, backbtn;
    DatabaseReference dbRef;
    users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        email = findViewById(R.id.editEmail);
        telephone = findViewById(R.id.editTele);
        age = findViewById(R.id.editAge);
        btn2 = findViewById(R.id.submit);
        editpass = findViewById(R.id.editpasstxt);
        editpassconf = findViewById(R.id.editpassconftxt);
        backbtn = findViewById(R.id.backbtn1);

        user = new users();

        Intent userSession = getIntent();
        String fn = userSession.getStringExtra("fn");

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile_edit.this, profile.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(email.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(telephone.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your telephone", Toast.LENGTH_LONG).show();
                }
                else if( TextUtils.isEmpty(age.getText().toString()) ){
                    Toast.makeText(getApplicationContext(), "Enter your age", Toast.LENGTH_LONG).show();
                }
                else{
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if( snapshot.hasChild(fn) ) {
                                if( !editpass.getText().toString().equals(editpassconf.getText().toString()) ){
                                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                                }

                                user.setEmail(email.getText().toString().trim());
                                user.setTelephone(telephone.getText().toString().trim());
                                user.setAge(age.getText().toString().trim());
                                user.setPassword(editpass.getText().toString().trim());
                                final String name = user.getFullname();

                                dbRef.child(fn).child("age").setValue(user.getAge());
                                dbRef.child(fn).child("email").setValue(user.getEmail());
                                dbRef.child(fn).child("telephone").setValue(user.getTelephone());
                                dbRef.child(fn).child("password").setValue(user.getPassword());

                                Toast.makeText(getApplicationContext(), "Successfully edited", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(profile_edit.this, profile.class);
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
