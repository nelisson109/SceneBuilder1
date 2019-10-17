package com.martin.views;

import com.martin.logica.Logica;
import com.martin.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {//un controller siempre implementa de Initializable

    @FXML
    private Menu menuAlta;

    @FXML
    private TableView<Partido> tvpartidos;

    @FXML
    void AltaPartido(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VentanaAlta.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Alta partido");
            stage.setScene(new Scene(root, 700, 775));
            stage.show();
        }catch(IOException e){
            e.getLocalizedMessage();
        }

    }
    void modificarPartido(ActionEvent event){
        VentanaAltaController controller = fxmlLoader.getController();
        Partido partido = tvpartidos.getSelectionModel().getSelectedItem();
        controller.setPartidoModificar(partido);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvpartidos.setItems(Logica.getInstance().getPartidos());

    }
}
