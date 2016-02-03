package com.example.gonzalo.pmdmbroadcastreceiver.intents;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gonzalo.pmdmbroadcastreceiver.adaptadores.Adaptador;
import com.example.gonzalo.pmdmbroadcastreceiver.R;
import com.example.gonzalo.pmdmbroadcastreceiver.database.Contrato;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorEntrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorSalientes;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class LlamadasEntrantes extends AppCompatActivity {

    private GestorEntrantes gestorEntrantes;
    private GestorSalientes gestorSalientes;
    private ListView lv;
    private Cursor cursor;
    private Adaptador adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llamadas);
        this.lv = (ListView) findViewById(R.id.lv);
        setTitle("He sido llamado por:");

        gestorEntrantes = new GestorEntrantes(this);
        gestorEntrantes.open();
        cursor= gestorEntrantes.getCursor();
        tostada(cursor.getCount() + "");

    }

    public void tostada(String cadena){
        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        gestorEntrantes.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gestorEntrantes.open();
        cursor= gestorEntrantes.getCursor();
        adap=new Adaptador(this, cursor, gestorEntrantes);
        lv.setAdapter(adap);
    }


}
