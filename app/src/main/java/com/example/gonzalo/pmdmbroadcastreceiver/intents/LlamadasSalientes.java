package com.example.gonzalo.pmdmbroadcastreceiver.intents;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gonzalo.pmdmbroadcastreceiver.R;
import com.example.gonzalo.pmdmbroadcastreceiver.adaptadores.AdaptadorSalientes;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorSalientes;

/**
 * Created by Gonzalo on 02/02/2016.
 */
public class LlamadasSalientes extends AppCompatActivity {

    private GestorSalientes gestorSalientes;
    private ListView lv;
    private Cursor cursor;
    private AdaptadorSalientes adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_salientes);
        this.lv = (ListView) findViewById(R.id.listView);

        setTitle("He llamado a: ");
        gestorSalientes = new GestorSalientes(this);
        gestorSalientes.open();
        cursor= gestorSalientes.getCursor();
        tostada(cursor.getCount()+"");

    }

    public void tostada(String cadena){
        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        gestorSalientes.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gestorSalientes.open();
        cursor= gestorSalientes.getCursor();
        adap=new AdaptadorSalientes(this, cursor, gestorSalientes);
        lv.setAdapter(adap);
    }
}
