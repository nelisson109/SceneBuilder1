package com.martin.views;

import com.martin.logica.Logica;
import com.martin.models.Division;
import com.martin.models.Partido;
import com.martin.views.Filters.FilterDivision;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {//un controller siempre implementa de Initializable
    private FilterDivision filterDivision;

    @FXML
    private ComboBox<Division> cbDivision;
    @FXML
    private Menu menuAlta;

    @FXML
    private TableView<Partido> tvpartidos;

    @FXML
    void AltaPartido(ActionEvent event) {//debemos abrir la ventana para el alta
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VentanaAlta.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Alta partido");
            stage.setScene(new Scene(root, 700, 775));
            stage.showAndWait();
            filtrar();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    void modificarPartido(ActionEvent event){//debemos abrir la ventana para modificar
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VentanaAlta.fxml"));
            Parent root = fxmlLoader.load();
            VentanaAltaController controller = fxmlLoader.getController();
            Partido partido = tvpartidos.getSelectionModel().getSelectedItem();
            controller.setPartidoModificar(partido);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root, 300, 275));
            stage.showAndWait();
            filtrar();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void borrarPartido(ActionEvent event){
        Partido partido = tvpartidos.getSelectionModel().getSelectedItem();
        if (partido!=null)
            Logica.getInstance().borrarPartido(partido);
    }
    void guardar(ActionEvent event){
        //modificar para usar un FileChooser
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvpartidos.setItems(Logica.getInstance().getPartidos());
        filterDivision = new FilterDivision(Logica.getInstance().getPartidos());

        ObservableList<Division> categorias = FXCollections.observableArrayList();
        categorias.add(Division.TODO);
        categorias.add(Division.PRIMERA);
        categorias.add(Division.SEGUNDA);
        categorias.add(Division.TERCERA);
        cbDivision.setItems(FXCollections.observableArrayList(Division.values()));//sin acabar
//        cbDivision.getSelectionModel().selectFirst();

 /*       cbDivision.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Division d = (Division) cbDivision.getSelectionModel().getSelectedItem();
                tvpartidos.setItems(Logica.getInstance().filtrar(d));
            }
        });*/

        cbDivision.itemsProperty().addListener(new ChangeListener<ObservableList<Division>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Division>> observableValue, ObservableList<Division> oldValue, ObservableList<Division> newValue) {
                Division d = (Division) cbDivision.getSelectionModel().getSelectedItem();
                tvpartidos.setItems(filterDivision.filtrar(newValue));
            }
        });

    }
    private void filtrar(){
        tvpartidos.setItems(filterDivision.filtrar(cbDivision.getItems()));

    }
}
