package com.upv.pm_2022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bdPyM extends  SQLiteOpenHelper{

    String sqlCreate   = "CREATE TABLE persona       (persona_id INTEGER PRIMARY KEY, nombre_persona TEXT)";
    String sqlCreate2  = "CREATE TABLE tarea     (tarea_id INTEGER PRIMARY KEY, nombre_tarea TEXT, fecha_asignada TEXT, estado TEXT)";
    String sqlCreate3  = "CREATE TABLE persona_tarea     (personatarea_id INTEGER PRIMARY KEY, vigencia TEXT, id_tarea REFERENCES tarea(tarea_id), id_persona REFERENCES persona(persoma_id))";
    public bdPyM(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
          db.execSQL(sqlCreate3);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL("DROP TABLE IF EXISTS tarea");
        db.execSQL("DROP TABLE IF EXISTS persona_tarea");

        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);

    }

}