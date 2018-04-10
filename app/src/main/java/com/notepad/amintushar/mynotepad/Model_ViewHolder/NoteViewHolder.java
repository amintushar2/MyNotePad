package com.notepad.amintushar.mynotepad.Model_ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.notepad.amintushar.mynotepad.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    View mView;
    TextView textTitel, textTime;

    public  NoteViewHolder(View itemView){
        super(itemView);
        mView=itemView;
        textTitel =mView.findViewById(R.id.note_title);
        textTime =mView.findViewById(R.id.note_time);
    }
    public void setNoteTitle(String title){
        textTitel.setText(title);

    }
    public void setNoteTime(String time){

        textTime.setText(time);
    }
}
