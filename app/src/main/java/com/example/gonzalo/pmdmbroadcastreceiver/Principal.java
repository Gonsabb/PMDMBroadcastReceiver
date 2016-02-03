package com.example.gonzalo.pmdmbroadcastreceiver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gonzalo.pmdmbroadcastreceiver.database.Contrato;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorEntrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorSalientes;
import com.example.gonzalo.pmdmbroadcastreceiver.intents.Entrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.intents.LlamadasEntrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.intents.LlamadasSalientes;
import com.example.gonzalo.pmdmbroadcastreceiver.intents.Salientes;

public class Principal extends AppCompatActivity {

    private GestorEntrantes gestorEntrantes;
    private GestorSalientes gestorSalientes;
    private ListView lv;
    private Cursor cursorE, cursorS;
    private android.widget.TextView tvinfo1;
    private android.widget.Button btLlamadas;
    private android.widget.TextView tvinfo2;
    private android.widget.Button btEntrantes;
    private android.widget.TextView tvinfo3;
    private android.widget.Button btSalientes;
    private android.widget.TextView tvinfo4;
    private android.widget.Button btLlamadasSalientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.btLlamadasSalientes = (Button) findViewById(R.id.btLlamadasSalientes);
        this.tvinfo4 = (TextView) findViewById(R.id.tvinfo4);
        this.btSalientes = (Button) findViewById(R.id.btSalientes);
        this.tvinfo3 = (TextView) findViewById(R.id.tvinfo3);
        this.btEntrantes = (Button) findViewById(R.id.btEntrantes);
        this.tvinfo2 = (TextView) findViewById(R.id.tvinfo2);
        this.btLlamadas = (Button) findViewById(R.id.btLlamadas);
        this.tvinfo1 = (TextView) findViewById(R.id.tvinfo1);

        gestorEntrantes = new GestorEntrantes(this);
        gestorEntrantes.open();
        cursorE = gestorEntrantes.getCursor();


        gestorSalientes = new GestorSalientes(this);
        gestorSalientes.open();
        cursorS = gestorSalientes.getCursor();

        btEntrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startE(v, obtenerLlamadasEntrantes());
            }
        });

        btSalientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startS(v, obtenerLlamadasSalientes());
            }
        });

    }

    public void aLlamadas(View v){
        Intent i = new Intent(this, LlamadasEntrantes.class);
        startActivity(i);
    }

    public void aLlamadasSalientes(View v){
        Intent i = new Intent(this, LlamadasSalientes.class);
        startActivity(i);
    }


    public void startE(View v, int[] datos){
        Intent i = new Intent(this, Entrantes.class);
        i.putExtra("datosE", datos);
        startActivityForResult(i, 1);
    }


    public void startS(View v, int[] datos){
        Intent i = new Intent(this, Salientes.class);
        i.putExtra("datosS", datos);
        startActivityForResult(i, 2);
    }


    private int[] obtenerLlamadasEntrantes(){
        String se =   Contrato.TablaCalls.DIA+" = ?";

        String [] lunes = {"Lunes"};
        String [] martes= {"Martes"};
        String [] miercoles = {"Miércoles"};
        String [] jueves = {"Jueves"};
        String [] viernes= {"Viernes"};
        String [] sabado = {"Sábado"};
        String [] domingo = {"Domingo"};

        Cursor clunes = gestorEntrantes.getCursor(se,lunes);
        Cursor cmartes = gestorEntrantes.getCursor(se,martes);
        Cursor cmiercoles = gestorEntrantes.getCursor(se,miercoles);
        Cursor cjueves = gestorEntrantes.getCursor(se,jueves);
        Cursor cviernes = gestorEntrantes.getCursor(se,viernes);
        Cursor csabado = gestorEntrantes.getCursor(se,sabado);
        Cursor cdomingo = gestorEntrantes.getCursor(se,domingo);

        int lista[] = new  int[]{clunes.getCount(),cmartes.getCount(),cmiercoles.getCount(),
                cjueves.getCount(),cviernes.getCount(),csabado.getCount(),cdomingo.getCount()};

        return lista;
    }

    private int[] obtenerLlamadasSalientes(){
        String ms =   Contrato.TablaSalientes.DIA+" = ?";
        String [] lunes = {"Lunes"};
        Cursor clunes = gestorSalientes.getCursor(ms,lunes);
        String [] martes= {"Martes"};
        Cursor cmartes = gestorSalientes.getCursor(ms,martes);
        String [] miercoles = {"Miércoles"};
        Cursor cmiercoles = gestorSalientes.getCursor(ms,miercoles);
        String [] jueves = {"Jueves"};
        Cursor cjueves = gestorSalientes.getCursor(ms,jueves);
        String [] viernes= {"Viernes"};
        Cursor cviernes = gestorSalientes.getCursor(ms,viernes);
        String [] sabado = {"Sábado"};
        Cursor csabado = gestorSalientes.getCursor(ms,sabado);
        String [] domingo = {"Domingo"};
        Cursor cdomingo = gestorSalientes.getCursor(ms,domingo);

        int lista[] = new  int[]{clunes.getCount(),cmartes.getCount(),cmiercoles.getCount(),
                cjueves.getCount(),cviernes.getCount(),csabado.getCount(),cdomingo.getCount()};

        return lista;
    }
}
