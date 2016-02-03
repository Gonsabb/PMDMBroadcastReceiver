package com.example.gonzalo.pmdmbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.Call;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorEntrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorSalientes;

import java.util.Calendar;

/**
 * Created by Gonzalo on 02/02/2016.
 */
public class OutgoingCall extends BroadcastReceiver {

    private GestorEntrantes gestorEntrantes;
    private GestorSalientes gestorSalientes;
    private Call call1;
    private Context con;

    @Override
    public void onReceive(Context context, Intent intent) {
        con =context;
        gestorEntrantes = new GestorEntrantes(con);
        gestorSalientes = new GestorSalientes(con);
        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) {
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            gestorSalientes.open();
            call1 = new Call(phoneNumber, semana());
            gestorSalientes.insert(call1);
            gestorSalientes.close();
            tostada(call1.toString());
        }
    }

    public void tostada(String cadena){
        Toast.makeText(con, cadena, Toast.LENGTH_SHORT).show();
    }

    private String semana() {
        String str = "";

        Calendar calendar = Calendar.getInstance();
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        switch (diaSemana){
            case 1:
                return str="Domingo";
            case 2:
                return str="Lunes";
            case 3:
                return str="Martes";
            case 4:
                return str="Miércoles";
            case 5:
                return str="Jueves";
            case 6:
                return str="Viernes";
            case 7:
                return str="Sábado";
            default:
                return str="no matched day";
        }
    }
}
