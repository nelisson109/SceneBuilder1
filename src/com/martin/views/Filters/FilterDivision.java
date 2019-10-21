package com.martin.views.Filters;

import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilterDivision {
    private ObservableList<Division> listaPartidos;
    private ObservableList<Division> listaFiltrada;

    public FilterDivision(ObservableList<Division> listaPartidos){
        this.listaPartidos = listaPartidos;
        listaFiltrada = FXCollections.observableArrayList();

    }
    public void filtrar(Division divisionFiltrar){
        if ()
    }
}
