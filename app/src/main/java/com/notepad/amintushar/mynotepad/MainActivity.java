package com.notepad.amintushar.mynotepad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.notepad.amintushar.mynotepad.user_activity.log_in_activity;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    FirebaseAuth loginAuth;
    FloatingActionButton fabadd, newnote, todolist;
    Animation fabopn ,fabclose,fabclock,fabrAnticlock;
    Toolbar newnotetoolber;
    NavigationView mNavigation;
    DatabaseReference fnoteDatabase;
    Button logoutBtn;

    GridView gridView ;


    boolean isOpen = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);





        logoutBtn=findViewById(R.id.logoutm);
        //toolbar
       newnotetoolber = findViewById(R.id.toolbar2);
        setSupportActionBar(newnotetoolber);

       fabadd = findViewById(R.id.adbutton);
       newnote=findViewById(R.id.newnotebutton);
        todolist=findViewById(R.id.newtodolist);
       fabopn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
       fabclose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabclose);
       fabclock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
       fabrAnticlock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
       gridView =findViewById(R.id.gridview1);


//navigation Drawer and Button Click
        mNavigation=findViewById(R.id.navigation);
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logoutm:
                        loginAuth.signOut();
                        Toast.makeText(MainActivity.this, "LogOut Succesfully", Toast.LENGTH_SHORT).show();
                        Intent startIntent = new Intent(MainActivity.this, log_in_activity.class);
                        startActivity(startIntent);
                        finish();

                }
                return false;
            }
        });
        mDrawerLayout= findViewById(R.id.drawerlayout1);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//Note List
//        gridLayoutManager = new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
//        notelist=findViewById(R.id.note_list);
//    notelist.setHasFixedSize(true);
//        notelist.setLayoutManager(gridLayoutManager);

//Firebase
        loginAuth = FirebaseAuth.getInstance();

        if (loginAuth.getCurrentUser()!=null){
            fnoteDatabase= FirebaseDatabase.getInstance().getReference().child("Notes").child(loginAuth.getCurrentUser().getUid());
        }

        //Define Floating Menu//

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

//new note activyty//
       newnote.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent startIntent = new Intent(MainActivity.this, new_note.class);
               startActivity(startIntent);
           }
       });

        updateUI();
    }


    //NavigationDrawer select Item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if( mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Checking User //
    private void updateUI(){

        if (loginAuth.getCurrentUser() != null){
            Log.i("MainActivity", "loginAuth != null");
        } else {
            Intent startIntent = new Intent(MainActivity.this, log_in_activity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity", "loginAuth == null");
        }
        NoteAdapter adapter = new NoteAdapter(this,R.layout.single_row,fnoteDatabase, loginAuth);
        gridView.setAdapter(adapter);
    }
}


