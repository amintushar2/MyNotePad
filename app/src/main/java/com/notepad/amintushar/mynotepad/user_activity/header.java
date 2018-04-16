package com.notepad.amintushar.mynotepad.user_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.notepad.amintushar.mynotepad.R;

public class header extends AppCompatActivity {



    FirebaseAuth loginAuth;
    TextView profilename, pemail;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);


//        profilename= findViewById(R.id.pfname);
//        pemail=findViewById(R.id.pfemail);

//        loginAuth=FirebaseAuth.getInstance();
//        firebaseDatabase =FirebaseDatabase.getInstance();
//        DatabaseReference fnoteDatabase =firebaseDatabase.getReference(loginAuth.getUid());
//        fnoteDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                UserProfile up = dataSnapshot.getValue(UserProfile.class);
//
//                 pemail.setText(up.getUserEmail());
//                profilename.setText(up.getUserName());
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//
//            }
//        });
    }
}
