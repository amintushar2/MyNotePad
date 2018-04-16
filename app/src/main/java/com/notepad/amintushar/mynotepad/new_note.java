package com.notepad.amintushar.mynotepad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;


import java.util.HashMap;
import java.util.Map;

public class new_note extends AppCompatActivity {

    Button btnsave;
    EditText etTitle, etContent;

   Toolbar newnotetoolber;
   DatabaseReference newnoteDatabase;
   FirebaseAuth myAppAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        newnotetoolber = findViewById(R.id.newtoobler);
        btnsave = findViewById(R.id.about);
        etTitle =  findViewById(R.id.new_note_title);
        etContent =  findViewById(R.id.new_note_content);
        setSupportActionBar(newnotetoolber);
        myAppAuth=FirebaseAuth.getInstance();
     newnoteDatabase=FirebaseDatabase.getInstance().getReference().child("Notes").child(myAppAuth.getCurrentUser().getUid());

//    btnsave.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
////        String title = etTitle.getText().toString().trim();
////        String content = etContent.getText().toString().trim();
////        if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)){
////            creatNote(title,content);
////        }else{
////            Toast.makeText(new_note.this, "Fill Empty", Toast.LENGTH_SHORT).show();
////            //Snackbar
////        }
////    }
////
////    private void creatNote(String title, String content) {
////
////        if (noteappAuth.getCurrentUser()!=null){
////            final DatabaseReference noteref = newnoteDatabase.push();
////            final Map noteMap = new HashMap();
////            noteMap.put("title",title);
////            noteMap.put("conten",content);
////            noteMap.put("timetamp",ServerValue.TIMESTAMP);
////            Thread newthread= new Thread(new Runnable() {
////
////                @Override
////                public void run() {
////                    noteref.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>() {
////                        @Override
////                        public void onComplete(@NonNull Task<Void> task) {
////
////
////
////                            if (task.isSuccessful()){
////                                Toast.makeText(new_note.this, "Save Data", Toast.LENGTH_SHORT).show();
////                            }else{
////                                Toast.makeText(new_note.this, "ERROR:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
////                            }
////                        }
////                    });
////                }
////            });
////            newthread.start();
////
////        }else{
////            Toast.makeText(new_note.this, "Signed In Error ", Toast.LENGTH_SHORT).show();
////        }
//
//  }
//});


        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.button_create2:
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)){
                    creatNote(title,content);

                }else{
                    Toast.makeText(new_note.this, "Fill Empty", Toast.LENGTH_SHORT).show();
                    //Snackbar
                }
        }
        return true;
    }

    private void creatNote(String title, String content) {

        if (myAppAuth.getCurrentUser()!=null){
            final DatabaseReference noteref = newnoteDatabase.push();
            final Map noteMap = new HashMap();
            noteMap.put("title",title);
            noteMap.put("content",content);
            noteMap.put("timeTamp",ServerValue.TIMESTAMP);
            Thread newthread= new Thread(new Runnable() {
                @Override
                public void run() {
                    noteref.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(new_note.this, "Save Data", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(new_note.this, "ERROR:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            newthread.start();
            Intent startIntent = new Intent(new_note.this, Navigation.class);
            startActivity(startIntent);

        }else{
            Toast.makeText(new_note.this, "Signed In Error ", Toast.LENGTH_SHORT).show();
        }




    }


}


