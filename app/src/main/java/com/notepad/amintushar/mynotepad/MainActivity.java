package com.notepad.amintushar.mynotepad;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.notepad.amintushar.mynotepad.Model_ViewHolder.NoteModel;
import com.notepad.amintushar.mynotepad.Model_ViewHolder.NoteViewHolder;
import com.notepad.amintushar.mynotepad.user_activity.log_in_activity;

import java.util.List;

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
    RecyclerView notelist;
    GridLayoutManager gridLayoutManager;

    boolean isOpen = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
logoutBtn=findViewById(R.id.logoutm);
//toolbar
       newnotetoolber = findViewById(R.id.toolbar2);
        setSupportActionBar(newnotetoolber);
        //Floating Actionbar
       fabadd = findViewById(R.id.adbutton);
       newnote=findViewById(R.id.newnotebutton);
       todolist=findViewById(R.id.newtodolist);
       fabopn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
       fabclose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabclose);
       fabclock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
       fabrAnticlock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
//Recycler View
//        //notelist=findViewById(R.id.main_note_list);
//        gridLayoutManager=new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false);
//        notelist.setHasFixedSize(true);
//        notelist.setLayoutManager(gridLayoutManager);

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

        //DEfine Floatind ActionBar
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<NoteModel,NoteViewHolder> FirebaseRecyclerAdapter= new FirebaseRecyclerAdapter<NoteModel, NoteViewHolder>(
//               NoteModel.class,
//                R.layout.single_row,
//                NoteViewHolder.class,
//                fnoteDatabase
//        ) {
//
//            @Override
//            protected void onBindViewHolder(@NonNull final NoteViewHolder holder, int position, @NonNull NoteModel model) {
//                String noteId = getRef(position).getKey();
//                fnoteDatabase.child(noteId).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String title =dataSnapshot.child("title").getValue().toString();
//                        String timeTamp =dataSnapshot.child("timeTamp").getValue().toString();
//                        holder.setNoteTitle(title);
//                        holder.setNoteTitle(timeTamp);
//
//                    }
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//
//            @NonNull
//            @Override
//            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
//                return new NoteViewHolder(cardView);
//            }
//
//        };
//
//    }



    //NavigationDrawer select Item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if( mToggle.onOptionsItemSelected(item)){


            return true;
        }




        return super.onOptionsItemSelected(item);
    }



    //update ui
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


