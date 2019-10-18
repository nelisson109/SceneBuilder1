package com.martin.views;

import com.martin.logica.Logica;
import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AltaPartido extends Stage {

    private TextField tfl;
    private TextField tfv;
    private ComboBox <Division> cbdivision;
    private Division d;
    private TextField resultado;
    private Date date;
    private DatePicker datep;
    private Button aceptar;

    public AltaPartido(){
        inicializaVista();
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              // addPartido();
                int id = 0;
                id = id + 1;
                LocalDate localDate = (LocalDate) datep.getValue();
                date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                d = (Division) cbdivision.getSelectionModel().getSelectedItem();
                Partido partido = new Partido(id, tfl.getText(), tfv.getText(), d, resultado.getText(), date);
                Logica.getInstance().addPartido(partido);
                close();


            }
        });
    }

    public AltaPartido(Partido partido, int indice) {
        inicializaVista();
        tfl.setText(partido.getEquipoLocal());
        tfv.setText(partido.getEquipoVisitante());
        resultado.setText(partido.getResultado());

        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int id = 0;
                id = id + 1;
                String local = tfl.getText();
                String visitante = tfv.getText();
                d = (Division) cbdivision.getSelectionModel().getSelectedItem();
                String result = resultado.getText();
                LocalDate localDate = (LocalDate) datep.getValue();
                date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Partido partido1 = new Partido(id, local, visitante, d, result, date);
                Logica.getInstance().modificarPartido(partido1, indice);
                close();

            }
        });

    }
    public void inicializaVista(){


        initModality(Modality.APPLICATION_MODAL);
        setTitle("Alta Partido");
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Mete equipo local"));
        tfl = new TextField();
        vBox.getChildren().add(tfl);
        vBox.getChildren().add(new Label("Mete equipo visitante"));
        tfv = new TextField();
        vBox.getChildren().add(tfv);
        vBox.getChildren().add(new Label("Mete marcador"));
        resultado = new TextField();
        vBox.getChildren().add(resultado);
        vBox.getChildren().add(new Label("Mete division"));
        ObservableList<Division> categorias = FXCollections.observableArrayList();
        categorias.add(Division.PRIMERA);
        categorias.add(Division.SEGUNDA);
        categorias.add(Division.TERCERA);
        cbdivision = new ComboBox<Division>(categorias);

        vBox.getChildren().add(cbdivision);
        vBox.getChildren().add(new Label("Mete la fecha del partido"));
        datep = new DatePicker();

        vBox.getChildren().add(datep);
        aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
        vBox.getChildren().add(aceptar);
        vBox.getChildren().add(cancelar);


        Scene escena = new Scene(vBox, 400, 400);
        setScene(escena);
    }

}
