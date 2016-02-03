package com.example.gonzalo.pmdmbroadcastreceiver.gestiondatos;

import android.database.Cursor;

import com.example.gonzalo.pmdmbroadcastreceiver.database.Contrato;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class Call {
    private long id;
    private String numero, dia;

    public Call() {
    }

    public Call(long id, String numero, String dia) {
        this.id = id;
        this.numero = numero;
        this.dia = dia;
    }

    public Call(String numero, String dia) {
        this.numero = numero;
        this.dia = dia;
    }

//    public void set(Cursor c){
//        setId(c.getLong(c.getColumnIndex(Contrato.TablaCalls._ID)));
//        setNumero(c.getString(c.getColumnIndex(Contrato.TablaCalls.NUMERO)));
//        setDia(c.getString(c.getColumnIndex(Contrato.TablaCalls.DIA)));
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (id != call.id) return false;
        if (numero != null ? !numero.equals(call.numero) : call.numero != null) return false;
        return !(dia != null ? !dia.equals(call.dia) : call.dia != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (dia != null ? dia.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", dia='" + dia + '\'' +
                '}';
    }
}
