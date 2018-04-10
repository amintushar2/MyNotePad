package com.notepad.amintushar.mynotepad.Model_ViewHolder;

public class NoteModel {
    public  String noteTitele;
    public  String noteTime;

    public NoteModel(){

    }
    public NoteModel(String noteTitele, String noteTime) {
        this.noteTitele = noteTitele;
        this.noteTime = noteTime;
    }

    public String getNoteTitele() {
        return noteTitele;
    }

    public void setNoteTitele(String noteTitele) {
        this.noteTitele = noteTitele;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }


}
