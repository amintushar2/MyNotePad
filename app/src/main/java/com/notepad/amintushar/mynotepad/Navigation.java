package com.notepad.amintushar.mynotepad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.notepad.amintushar.mynotepad.user_activity.UserProfile;
import com.notepad.amintushar.mynotepad.user_activity.log_in_activity;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView mNavigation;
    DatabaseReference fnoteDatabase;
    Button logoutBtn;
    FirebaseAuth loginAuth;
    GridView gridView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        logoutBtn=findViewById(R.id.logoutm);
        gridView =findViewById(R.id.gridview1);
        gridView.setNumColumns(2);
        gridView.setVerticalSpacing(6);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//floating Menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation.this,new_note.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//navigationDrawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//loginChwck
        loginAuth = FirebaseAuth.getInstance();

        if (loginAuth.getCurrentUser()!=null){
            fnoteDatabase= FirebaseDatabase.getInstance().getReference().child("Notes").child(loginAuth.getCurrentUser().getUid());
        }

        updateUI();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.logoutm) {
            loginAuth.signOut();
                        Toast.makeText(Navigation.this, "LogOut Succesfully", Toast.LENGTH_SHORT).show();
                        Intent startIntent = new Intent(Navigation.this, log_in_activity.class);
                        startActivity(startIntent);
                        finish();
        }else if (id == R.id.db){
            Intent startIntent = new Intent(Navigation.this, About_Me.class);
            startActivity(startIntent);


        }else if (id == R.id.cntus){
            Intent startIntent = new Intent(Navigation.this, Contact_us.class);
            startActivity(startIntent);


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void updateUI(){

        if (loginAuth.getCurrentUser() != null){
            Log.i("MainActivity", "loginAuth != null");
        } else {
            Intent startIntent = new Intent(Navigation.this, log_in_activity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity", "loginAuth == null");
        }
        NoteAdapter adapter = new NoteAdapter(this,R.layout.single_row,fnoteDatabase, loginAuth);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                if (loginAuth.getCurrentUser() == null || fnoteDatabase == null)
//                    return;
//
//                fnoteDatabase.child(null);
//                Query query =fnoteDatabase.child("title");
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        fnoteDatabase.child("k").getRef().removeValue();
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });


//                Intent intent = new Intent(Navigation.this,new_note.class);
//                startActivity(intent);
//                intent.putExtra("title",String.valueOf(id));
//                intent.putExtra("content",String.valueOf(id));
//                updateUI();
            }
        });
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                fnoteDatabase.child()
//                return false;
//            }
//        });
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
//                fnoteDatabase= FirebaseDatabase.getInstance().getReference(loginAuth.getUid());
//
//                AlertDialog.Builder altdial = new AlertDialog.Builder(Navigation.this);
//                altdial.setMessage("Do you want to Quit this app ???")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//
//                                fnoteDatabase.child("Child").getKey().removeValue();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert = altdial.create();
//
//                alert.show();
//
//
//
//
//
//                return false;
//            }
//        });



    }
}
