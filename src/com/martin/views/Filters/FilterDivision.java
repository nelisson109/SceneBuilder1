package com.martin.views.Filters;

import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilterDivision {
    private ObservableList<Partido> partidos;
    private ObservableList<Partido> listaFiltrada;

    public FilterDivision(ObservableList<Partido> partidos){
        this.partidos = partidos;
        listaFiltrada = FXCollections.observableArrayList();

    }
    public ObservableList<Partido> filtrar(ObservableList<Division> divisionFiltrar){
        if(divisionFiltrar!=null && !"".equals(divisionFiltrar)){
            listaFiltrada.clear();
            for(Partido partido : partidos){
                if(partido.getD().equals(divisionFiltrar)){
                    listaFiltrada.add(partido);
                }
            }
            return listaFiltrada;
        }else{
            return partidos;
        }
    }

}
