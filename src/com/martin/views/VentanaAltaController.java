package com.martin.views;
import com.martin.logica.Logica;
import com.martin.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaAltaController {

        private Partido partidoModificar;
        @FXML
        private ComboBox<?> cbDivision;

        @FXML
        private TextField tfLocal;

        @FXML
        private TextField tfVisitante;

        @FXML
        private TextField tfResultado;

        @FXML
        private DatePicker dpFecha;

        @FXML
        private Button btnAceptar;

        @FXML
        private Button btnCancelar;

         @FXML
        void altaPartido(ActionEvent event){
                 if ()
            int id = 0;
            Partido partido = new Partido(id, tfLocal.getText(), tfVisitante.getText(), null, tfResultado.getText(), null);
            Logica.getInstance().addPartido(partido);//esto esta en metodo void altaPersona
            Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            stage.close();
    }
    public void setPartidoModificar(Partido partido){
                 this.partidoModificar = partidoModificar;
                 tfLocal.setText(partido.getEquipoLocal());
                 tfVisitante.setText(partido.getEquipoVisitante());

    }
}
