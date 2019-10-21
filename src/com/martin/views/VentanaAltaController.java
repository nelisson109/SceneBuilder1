package com.martin.views;
import com.martin.Utils.Utils;
import com.martin.logica.Logica;
import com.martin.models.Division;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaAltaController {

        private Partido partidoModificar;
        private int indiceModificar;
        @FXML
        private ComboBox<Division> cbDivision;

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
        void altaModificarPartido(ActionEvent event){
                 if (partidoModificar!=null) {//si se le da al boton modificar
                     partidoModificar.setEquipoLocal(tfLocal.getText());
                     partidoModificar.setEquipoVisitante(tfVisitante.getText());
                     partidoModificar.setResultado(tfResultado.getText());
                     partidoModificar.setD((Division) cbDivision.getValue());
                     LocalDate localDate = Utils.convertToLocalDate(partidoModificar.getFecha());

                     Logica.getInstance().modificarPartido(partidoModificar, indiceModificar);

                 }
                 else{//sino el alta normal de un partido
                     int id = 0;
                     LocalDate localDate = (LocalDate) dpFecha.getValue();
                     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                     Partido partido = new Partido(id, tfLocal.getText(), tfVisitante.getText(), (Division)cbDivision.getValue(), tfResultado.getText(), date);
                     Logica.getInstance().addPartido(partido);//esto esta en metodo void altaPersona
                 }

             Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());//obtener stage desde evento
             stage.close();
    }
    public void setPartidoModificar(Partido partidoModificar, int indiceModificar){
                 this.partidoModificar = partidoModificar;
                 this.indiceModificar = indiceModificar;
                 tfLocal.setText(partidoModificar.getEquipoLocal());
                 tfVisitante.setText(partidoModificar.getEquipoVisitante());
                 cbDivision.setValue(partidoModificar.getD());
                 tfResultado.setText(partidoModificar.getResultado());
                LocalDate localDate = Utils.convertToLocalDate(partidoModificar.getFecha());
                dpFecha.setValue(localDate);


    }
}
