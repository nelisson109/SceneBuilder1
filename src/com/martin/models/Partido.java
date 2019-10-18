package com.martin.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


public class Partido implements Comparable<Partido>, Serializable {
    private int id;
    private String equipoLocal;
    private String equipoVisitante;
    private Division d;
    private String resultado;
    private Date fecha;

    public Partido(int id, String equipoLocal, String equipoVisitante, Division d, String resultado, Date fecha) {
        this.id = id;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.d = d;
   /*     this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;   */
        this.resultado = resultado;
        this.fecha = fecha;
    }

    public int compareTo(Partido p) {
        //Objeto1.getAtributo().compareTo(Objeto2.getAtr());
        return fecha.compareTo(p.fecha);
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", equipoLocal='" + equipoLocal + '\'' +
                ", equipoVisitante='" + equipoVisitante + '\'' +
                ", d=" + d +
                ", resultado='" + resultado + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
/*
    public String getD() {
        return String.valueOf(d);
    }
    public void setD(Division d) {
        this.d = d;
    }
*/

    public Division getD() {
        return d;
    }

    public void setD(Division d) {
        this.d = d;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
