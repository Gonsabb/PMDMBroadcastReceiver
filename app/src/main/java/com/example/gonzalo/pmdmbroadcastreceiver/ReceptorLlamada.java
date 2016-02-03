package com.example.gonzalo.pmdmbroadcastreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.Call;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorEntrantes;
import com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos.GestorSalientes;

import java.util.Calendar;

/**
 * Created by Gonzalo on 02/02/2016.
 */
public class ReceptorLlamada extends BroadcastReceiver {

    private GestorEntrantes gestorEntrantes;
    private GestorSalientes gestorSalientes;
    private Call call1, call2;
    private Context con;

    @Override
    public void onReceive(Context context, Intent intent) {
        con =context;
        final Bundle extras = intent.getExtras();
        gestorEntrantes = new GestorEntrantes(con);
        gestorSalientes = new GestorSalientes(con);
        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) {
            final String originalNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            gestorSalientes.open();
            call1 = new Call(originalNumber,semana());
            gestorSalientes.insert(call1);
            gestorSalientes.close();
            tostada(call1.toString());
        }

        TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
        PhoneStateListener listener=new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if(state == TelephonyManager.CALL_STATE_RINGING) {
                    if (incomingNumber != null) {
                        //incoming call
                        String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                        gestorEntrantes.open();
                        call2 = new Call(phoneNumber, semana());
                        gestorEntrantes.insert(call2);
                        gestorEntrantes.close();
                        tostada(call2.toString());
                    }
                }
            }
        };
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
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





//    private Context contexto;
//    private GestorEntrantes gestorEntrantes;
//    private Call call;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Bundle extras = intent.getExtras();
//        gestorEntrantes = new GestorEntrantes(context);
//        contexto = context;
//        if (extras != null) {
//            String estado = extras.getString(TelephonyManager.EXTRA_STATE);
//            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//                String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
//
//                gestorEntrantes.open();
//                call = new Call(phoneNumber,semana());
//                gestorEntrantes.insert(call);
//                gestorEntrantes.close();
//                tostada(call.toString());
//            }
//        }
//    }
//
//
//    public void tostada(String cadena){
//        Toast.makeText(contexto, cadena, Toast.LENGTH_SHORT).show();
//    }
//
//    private String semana(){
//        String str ="";
//
//        Calendar calendar = Calendar.getInstance();
//        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
//
//        switch (diaSemana){
//            case 0:
//                return str="Domingo";
//            case 1:
//                return str="Lunes";
//            case 2:
//                return str="Martes";
//            case 3:
//                return str="Miercoles";
//            case 4:
//                return str="Jueves";
//            case 5:
//                return str="Viernes";
//            case 6:
//                return str="Sábado";
//            default:
//                return str="no matched day";
//        }
//    }

