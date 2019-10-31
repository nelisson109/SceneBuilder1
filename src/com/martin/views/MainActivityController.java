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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable, Serializable {//un controller siempre implementa de Initializable
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

        File file = fileChooser.showSaveDialog(stage);
        ArrayList <Partido> listaGuarda = new ArrayList<Partido>();
        FileOutputStream escribir = null;
        ObjectOutputStream escritura = null;
        try {

            for (int i = 0; i < Logica.getInstance().getPartidos().size(); i++) {
                listaGuarda.add((Partido) Logica.getInstance().getPartidos().get(i));
            }
            if (file != null) {
                escribir = new FileOutputStream(file);
                escritura = new ObjectOutputStream(escribir);

                escritura.writeObject(listaGuarda);

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Error, no se encuentra el fichero para escribir");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error de entrada/salida");
        }finally {
            try {
                if (escritura != null) {
                    escritura.close();
                }
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Error de entrada/salida");
            }
        }
    }
    @FXML
    void cargar(ActionEvent event){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Carga Fichero");
        //tipos de archivo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de datos (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            //cargar el archivo
            FileInputStream leer = null;
            ObjectInputStream lectura = null;
            try {
                ArrayList<Partido> listaCarga = new ArrayList<Partido>();
                leer = new FileInputStream(file);
                lectura = new ObjectInputStream(leer);

                    listaCarga = (ArrayList) lectura.readObject();

                for (int i = 0; i < listaCarga.size(); i++) {
                    Logica.getInstance().getPartidos().add(listaCarga.get(i));
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
                System.out.println("No se encuentra el fichero");
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error de entrada/salida");
            }catch (ClassNotFoundException e){
                e.printStackTrace();
                System.out.println("No se encuentra la clase especificada");
            }finally {
                try{
                    if (lectura!=null){
                        lectura.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                    System.out.println("Error de entrada/salida");
                }
            }
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

    }
    @FXML
    private void filtrar(){
        tvpartidos.setItems(filterDivision.filtrar(cbFiltrar.getValue()));

    }
}
