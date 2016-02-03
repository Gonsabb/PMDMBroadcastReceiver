package com.example.gonzalo.pmdmbroadcastreceiver.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.gonzalo.pmdmbroadcastreceiver.R;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.Call;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorEntrantes;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class Adaptador extends CursorAdapter{

    private GestorEntrantes gestorEntrantes;
    private Cursor c;
    private android.widget.TextView tvNumero;
    private android.widget.TextView tvDia;

    public Adaptador(Context context, Cursor c, GestorEntrantes g) {
        super(context, c, true);
        this.gestorEntrantes =g;
        this.c=c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        return i.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        this.tvDia = (TextView) view.findViewById(R.id.tvDia);
        this.tvNumero = (TextView) view.findViewById(R.id.tvNumero);

        Call call = new Call();
        call= gestorEntrantes.getRow(cursor);

        tvNumero.setText(call.getNumero());
        tvDia.setText(call.getDia());
    }
}
