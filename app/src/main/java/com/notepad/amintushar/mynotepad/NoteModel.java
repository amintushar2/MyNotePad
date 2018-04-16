package com.notepad.amintushar.mynotepad;

public class NoteModel {

    public NoteModel(){

    }
    public  String noteTitele;
    public  String noteContent;
    public  String noteTime;


    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public NoteModel(String noteTitele, String noteTime, String noteContent) {
        this.noteTitele = noteTitele;
        this.noteTime = noteTime;
        this.noteContent = noteContent;
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
