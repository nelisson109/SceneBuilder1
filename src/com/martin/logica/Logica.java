package com.martin.logica;

import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;

public class Logica {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Logica INSTANCE = null;

    private Logica() {
        Partido p1 = new Partido(1, "Sporting", "Oviedo", Division.PRIMERA, "3-2", null);
        Partido p2 = new Partido(2, "Barsa", "Madrid", Division.PRIMERA, "3-0", null);
        partidos.addAll(p1, p2);

    }

    public static Logica getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Logica();

        return INSTANCE;
    }

    private ObservableList<Partido> partidos = FXCollections.observableArrayList();

    public void addPartido(Partido partido) {
        partidos.add(partido);
    }
    public void borrarPartido(int indiceBorrar){
        partidos.remove(indiceBorrar);
    }
    public void modificarPartido(Partido partido, int posicion){
        partidos.set(posicion, partido);

    }

    public ObservableList<Partido> getPartidos() {
        return partidos;
    }
    public ObservableList filtrar(Division f){
        ObservableList<Partido> listaFiltrada = FXCollections.observableArrayList();
        if(f.equals(Division.PRIMERA) || f.equals(Division.SEGUNDA) || f.equals(Division.TERCERA)){
            for(int i = 0; i<partidos.size(); i++){
                if(partidos.get(i).getD().equals(f)){
                    listaFiltrada.add(partidos.get(i));
                }
            }
            return listaFiltrada;
        }
        else {
            return partidos;
        }
    }
}