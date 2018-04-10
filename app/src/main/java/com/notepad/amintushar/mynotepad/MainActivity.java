package com.notepad.amintushar.mynotepad;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.notepad.amintushar.mynotepad.user_activity.log_in_activity;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    FirebaseAuth loginAuth;
    FloatingActionButton fabadd, newnote, todolist;
    Animation fabopn ,fabclose,fabclock,fabrAnticlock;
   Toolbar newnotetoolber;
    boolean isOpen = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


       newnotetoolber = findViewById(R.id.toolbar2);
       fabadd = findViewById(R.id.adbutton);
       newnote=findViewById(R.id.newnotebutton);
       todolist=findViewById(R.id.newtodolist);
       fabopn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
       fabclose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabclose);
       fabclock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
       fabrAnticlock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
       setSupportActionBar(newnotetoolber);

        mDrawerLayout= findViewById(R.id.drawerlayout1);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginAuth = FirebaseAuth.getInstance();
       fabadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (isOpen)
               {
                   newnote.startAnimation(fabclose);
                   todolist.startAnimation(fabclose);
                   fabadd.startAnimation(fabrAnticlock);
                   newnote.setClickable(false);
                   todolist.setClickable(false);
                   isOpen =false;
               }else{
                   newnote.startAnimation(fabopn);
                   todolist.startAnimation(fabopn);
                   fabadd.startAnimation(fabclock);
                   newnote.setClickable(true);
                   todolist.setClickable(true);
                   isOpen =true;
               }
           }
       });
       newnote.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent startIntent = new Intent(MainActivity.this, new_note.class);
               startActivity(startIntent);
           }
       });

        updateUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){

        if (loginAuth.getCurrentUser() != null){
            Log.i("MainActivity", "loginAuth != null");
        } else {
            Intent startIntent = new Intent(MainActivity.this, log_in_activity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity", "loginAuth == null");
        }

    }


    }


