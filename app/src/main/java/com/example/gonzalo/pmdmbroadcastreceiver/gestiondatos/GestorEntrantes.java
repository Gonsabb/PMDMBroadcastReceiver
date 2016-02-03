package com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gonzalo.pmdmbroadcastreceiver.database.Ayudante;
import com.example.gonzalo.pmdmbroadcastreceiver.database.Contrato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class GestorEntrantes {

    private Ayudante ayudante;
    private SQLiteDatabase bd;

    public GestorEntrantes(Context c) {
        ayudante=new Ayudante(c);
    }

    public void open() {
        bd = ayudante.getWritableDatabase();
    }

    public void openRead() {
        bd = ayudante.getReadableDatabase();
    }

    public void close() {
        ayudante.close();
    }

    public long insert(Call call) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCalls.NUMERO, call.getNumero());
        valores.put(Contrato.TablaCalls.DIA, call.getDia());
        long id = bd.insert(Contrato.TablaCalls.TABLA, null, valores);
        return id;
    }

    public int delete(Call call) {
        return delete(call.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaCalls._ID + " = ?";
        String[] argumentos = {id + ""};
        int cuenta = bd.delete(
                Contrato.TablaCalls.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Call call){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCalls.NUMERO, call.getNumero());
        valores.put(Contrato.TablaCalls.DIA, call.getDia());
        String condicion = Contrato.TablaCalls._ID + " = ?";
        String[] argumentos = { call.getId() + "" };
        int cuenta = bd.update(Contrato.TablaCalls.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Call getRow(Cursor c) {
        Call call = new Call();
        call.setId(c.getLong(c.getColumnIndex(Contrato.TablaCalls._ID)));
        call.setNumero(c.getString(c.getColumnIndex(Contrato.TablaCalls.NUMERO)));
        call.setDia(c.getString(c.getColumnIndex(Contrato.TablaCalls.DIA)));
        return call;
    }

    public Call getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id + ""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaCalls.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaCalls.NUMERO + ", " + Contrato.TablaCalls.DIA);
        return cursor;
    }

    public List<Call> select(String condicion, String[] parametros) {
        List<Call> lc = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Call call;
        while (cursor.moveToNext()) {
            call = getRow(cursor);
            lc.add(call);
        }
        cursor.close();
        return lc;
    }

}
