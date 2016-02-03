package com.example.gonzalo.pmdmbroadcastreceiver.database;

import android.provider.BaseColumns;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class Contrato{

    private Contrato (){
    }

    public static abstract  class TablaCalls implements BaseColumns {
        public static final String TABLA = "llamadas_entrantes";
        public static final String NUMERO = "numero";
        public static final String DIA = "dia";
    }

    public static abstract  class TablaSalientes implements BaseColumns {
        public static final String TABLA = "llamadas_salientes";
        public static final String NUMERO = "numero";
        public static final String DIA = "dia";
    }

}
