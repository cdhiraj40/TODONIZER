package com.example.TODONIZER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteHandler extends DatabaseMaintainer {

    public NoteHandler(Context context) {
        super(context);
    }

    //CRUD  C create, R read, U update, D delete

    public boolean create(NoteInfo noteInfo) {

        ContentValues values = new ContentValues();

        values.put("title", noteInfo.getTitle());
        values.put("description", noteInfo.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccessfull = db.insert("Note", null, values) > 0;
        db.close();
        return isSuccessfull;
    }

    public ArrayList<NoteInfo> readNotes() {
        ArrayList<NoteInfo> noteInfos = new ArrayList<>();

        String sqlQuery = "SELECT * FROM Note ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                NoteInfo noteInfo = new NoteInfo(title, description);
                noteInfo.setId(id);
                noteInfos.add(noteInfo);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return noteInfos;
    }

    public NoteInfo readSingleNote(int id) {
        NoteInfo noteInfo = null;
        String sqlQuery = "SELECT * FROM Note WHERE id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            int noteId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            noteInfo = new NoteInfo(title, description);
            noteInfo.setId(noteId);
        }
        cursor.close();
        db.close();
        return noteInfo;
    }

    public boolean update(NoteInfo noteInfo) {

        ContentValues values = new ContentValues();
        values.put("title", noteInfo.getTitle());
        values.put("description", noteInfo.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessfull = db.update("Note", values, "id='" + noteInfo.getId() + "'", null) > 0;
        db.close();
        return isSuccessfull;
    }

    public boolean delete(int id) {
        boolean isDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("Note", "id='" + id + "'", null) > 0;
        db.close();
        return isDeleted;
    }

}
