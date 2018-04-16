package com.notepad.amintushar.mynotepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Map;

class Notes {
    String title,timeTamp,content;

    public Notes(String title, String timeTamp,String content) {
        this.title = title;
        this.timeTamp = timeTamp;
        this.content = content;
    }
}

public class NoteAdapter extends BaseAdapter {

    DatabaseReference ref;
    ArrayList<DataSnapshot> snapshots = new ArrayList<>();
    Context context;
    int resource;
    ChildEventListener cListener =new ChildEventListener() {


        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            snapshots.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    public  NoteAdapter(Context context, int resource, DatabaseReference ref, FirebaseAuth myAppAuth){
        this.ref =ref;
        this.context = context;
        this.resource= resource;
        this.ref = ref;
        this.ref.addChildEventListener(cListener);
    }



    @Override
    public int getCount() {
        return snapshots.size();
    }

    @Override
    public DataSnapshot getItem(int position) {
        return snapshots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View row = inflater.inflate(resource, parent,false);
        TextView title1 = row.findViewById(R.id.note_title);
        TextView timeTamp  = row.findViewById(R.id.note_time);
        TextView content = row.findViewById( R.id.note_content);
        Map note = (Map) getItem(position).getValue();
        title1.setText(note.get("title").toString());
        timeTamp.setText(note.get("timeTamp").toString());
        content.setText(note.get("content").toString());


        return row;
    }

}