package com.martin.views;

import com.martin.logica.Logica;
import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;


public class PantallaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception{
       File fichero = new File("ficheroPartidos.dat");
       //C:\Users\DAM\Desktop\
        if(fichero.exists()){
            Logica.getInstance().leerObjetos(fichero);
        }

        stage.setTitle("Pantalla Principal");
        TableView tableView = new TableView(Logica.getInstance().getPartidos());
        AnchorPane.setTopAnchor(tableView,10d);
        AnchorPane.setLeftAnchor(tableView,10d);
        AnchorPane.setRightAnchor(tableView,10d);
        AnchorPane.setBottomAnchor(tableView,10d);

        ComboBox CBfiltro;
        ObservableList<Division> categorias = FXCollections.observableArrayList();
        categorias.addAll(Division.TODO, Division.PRIMERA, Division.SEGUNDA, Division.TERCERA);
        CBfiltro = new ComboBox<Division>(categorias);
        CBfiltro.getSelectionModel().selectFirst();

        CBfiltro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Division d = (Division) CBfiltro.getSelectionModel().getSelectedItem();
                tableView.setItems(Logica.getInstance().filtrar(d));
            }
        });

        TableColumn<String, Partido> column1 = new TableColumn<>("equipoLocal");
        TableColumn<String, Partido> column2 = new TableColumn<>("equipoVisitante");
        TableColumn<String, Partido> column3 = new TableColumn<>("Division");
        TableColumn<String, Partido> column5 = new TableColumn<>("resultado");
        TableColumn<String, Partido> column4 = new TableColumn<>("fecha");
        column1.setCellValueFactory(new PropertyValueFactory<>("equipoLocal"));
        column2.setCellValueFactory(new PropertyValueFactory<>("equipoVisitante"));
        column3.setCellValueFactory(new PropertyValueFactory<>("d"));
        column5.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        column4.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tableView.getColumns().addAll(column1, column2, column3, column5, column4);



        Button añadir = new Button("Añadir partido");

        Button modificar = new Button("Modificar");

        modificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int indice = tableView.getSelectionModel().getSelectedIndex();
                Partido partido = Logica.getInstance().getPartidos().get(indice);

                AltaPartido altaPartido = new AltaPartido(partido, indice);
                altaPartido.show();
            }
        });

        añadir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AltaPartido altaPartido = new AltaPartido();
                altaPartido.show();
            }
        });

        Button borrar = new Button("Borrar");
        borrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int indiceBorrar = tableView.getSelectionModel().getSelectedIndex();
                if (indiceBorrar>=0)
                    Logica.getInstance().borrarPartido(indiceBorrar);
            }
        });

        Button guardar = new Button("Guardar");
        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Logica.getInstance().escribirObjetos(fichero);
                System.out.println("Salvado");
            }
        });
        ImageView imagen = new ImageView(getClass().getResource("resources/rugby.jpg").toExternalForm());
        imagen.setPreserveRatio(true);
        imagen.setFitHeight(150);

        AnchorPane anchorPane = new AnchorPane(tableView);
        HBox hBox = new HBox(imagen);

        hBox.getChildren().add(borrar);
        hBox.getChildren().add(añadir);
        hBox.getChildren().add(modificar);
        hBox.getChildren().add(guardar);
        hBox.getChildren().add(CBfiltro);
        hBox.setSpacing(10);
        VBox vBox = new VBox(anchorPane, hBox);


        Scene scena = new Scene(vBox, 600, 500);
        stage.setScene(scena);
        stage.show();

    }


    public static void main(String args []){
        launch();
    }
}
