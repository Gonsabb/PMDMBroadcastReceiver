package com.example.gonzalo.pmdmbroadcastreceiver.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calls.sqlite";
    public static final int DATABASE_VERSION = 10;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table " + Contrato.TablaCalls.TABLA + "( " +
                Contrato.TablaCalls._ID + " integer primary key autoincrement, " +
                Contrato.TablaCalls.NUMERO + " text, " +
                Contrato.TablaCalls.DIA + " text)";
        db.execSQL(sql);


        sql = "create table " + Contrato.TablaSalientes.TABLA + "( " +
                Contrato.TablaSalientes._ID + " integer primary key autoincrement, " +
                Contrato.TablaSalientes.NUMERO + " text, " +
                Contrato.TablaSalientes.DIA + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Contrato.TablaCalls.TABLA;
        db.execSQL(sql);
        sql="drop table if exists "+ Contrato.TablaSalientes.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }
}
