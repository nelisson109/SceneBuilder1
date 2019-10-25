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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {//un controller siempre implementa de Initializable
    private FilterDivision filterDivision;

    @FXML
    private ComboBox<Division> cbFiltrar;
    @FXML
    private Menu menuAlta;
 /*   @FXML
    private Button btnCargar;
    @FXML
    private Button btnGuardar;*/

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
            stage.setScene(new Scene(root, 800, 575));
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
            stage.setScene(new Scene(root, 800, 575));
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
    @FXML
    void guardar(ActionEvent event){
        Stage stage = new Stage();
        stage.setTitle("Guardar cambios en el fichero");
        FileChooser fileChooser = new FileChooser();
        //Este paso es opcional, para dejar al usuario solo seleccionar ciertos tipos de archivo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de datos (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            //Hacer lo que queramos con el archivo.
            System.out.println(file.getAbsolutePath());
        }
    }
    @FXML
    void cargar(ActionEvent event){
        Stage stage = new Stage();
        stage.setTitle("Carga Fichero");

                FileChooser fileChooser = new FileChooser();
                //Este paso es opcional, para dejar al usuario solo seleccionar ciertos tipos de archivo
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de datos (*.dat)", "*.dat");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showOpenDialog(stage);

                if (file != null) {
                    //Hacer lo que queramos con el archivo.
                    System.out.println(file.getAbsolutePath());
                }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvpartidos.setItems(Logica.getInstance().getPartidos());
        filterDivision = new FilterDivision(Logica.getInstance().getPartidos());

    /*    ObservableList<Division> categorias = FXCollections.observableArrayList();
        categorias.add(Division.PRIMERA);
        categorias.add(Division.SEGUNDA);
        categorias.add(Division.TERCERA);*/

        cbFiltrar.setItems(FXCollections.observableArrayList(Division.values()));
        cbFiltrar.valueProperty().addListener(new ChangeListener<Division>() {
            @Override
            public void changed(ObservableValue<? extends Division> observableValue, Division oldValue, Division newValue) {
                tvpartidos.setItems(filterDivision.filtrar(newValue));
            }
        });

 /*       cbFiltrar.itemsProperty().addListener(new ChangeListener<ObservableList<Division>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Division>> observableValue, ObservableList<Division> oldValue, ObservableList<Division> newValue) {
                tvpartidos.setItems(filterDivision.filtrar(newValue));
            }
        });*/

    }
    @FXML
    private void filtrar(){
        tvpartidos.setItems(filterDivision.filtrar(cbFiltrar.getValue()));

    }
}
