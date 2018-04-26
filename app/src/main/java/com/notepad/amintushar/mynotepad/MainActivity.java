package com.notepad.amintushar.mynotepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.notepad.amintushar.mynotepad.user_activity.log_in_activity;

public class MainActivity extends AppCompatActivity {
    DatabaseReference fnoteDatabase;
    FirebaseAuth loginAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    loginAuth = FirebaseAuth.getInstance();
                    if (loginAuth.getCurrentUser() != null) {
                        fnoteDatabase = FirebaseDatabase.getInstance().getReference().child("Notes").child(loginAuth.getCurrentUser().getUid());


                    }

                    updateUI();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();



    }

    private void updateUI () {


        if (loginAuth.getCurrentUser() != null) {
            Log.i("MainActivity", "loginAuth != null");
            Intent startIntent = new Intent(MainActivity.this, Navigation.class);
            startActivity(startIntent);
        } else {
            Intent startIntent = new Intent(MainActivity.this, log_in_activity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity", "loginAuth == null");
        }


    }
}