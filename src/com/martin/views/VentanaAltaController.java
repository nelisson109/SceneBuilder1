package com.martin.views;
import com.martin.Utils.Utils;
import com.martin.logica.Logica;
import com.martin.models.Division;
import com.martin.models.Partido;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaAltaController implements Initializable {

        private Partido partidoModificar;
    //    private ObservableList<Division> categorias = FXCollections.observableArrayList();

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
        void altaModificarPartido(ActionEvent event){//alta nueva o alta modificando
                 if (partidoModificar!=null) {//si estamos modificando
                     partidoModificar.setEquipoLocal(tfLocal.getText());
                     partidoModificar.setEquipoVisitante(tfVisitante.getText());
                     partidoModificar.setResultado(tfResultado.getText());

                     partidoModificar.setD((Division) cbDivision.getSelectionModel().getSelectedItem());
                     Date date = Utils.convertToDate(dpFecha.getValue());
                     partidoModificar.setFecha(date);

                     Logica.getInstance().modificarPartido(partidoModificar);

                 }
                 else{//si estamos haciendo un alta normal
                     String equipoLocal, equipoVisitante, resultado;
                     equipoLocal = tfLocal.getText();
                     equipoVisitante = tfVisitante.getText();
                     resultado = tfResultado.getText();

                     Division division = (Division)cbDivision.getSelectionModel().getSelectedItem();
                     LocalDate localDate = (LocalDate) dpFecha.getValue();
                     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                     Partido partido = new Partido(equipoLocal, equipoVisitante, division, resultado, date);
                     Logica.getInstance().addPartido(partido);
                 }

             Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());//obtener stage desde evento
             stage.close();
    }
    public void setPartidoModificar(Partido partidoModificar){//llamado por modificarPartido
                 this.partidoModificar = partidoModificar;
                 tfLocal.setText(partidoModificar.getEquipoLocal());
                 tfVisitante.setText(partidoModificar.getEquipoVisitante());
                 cbDivision.setValue(partidoModificar.getD());
                 tfResultado.setText(partidoModificar.getResultado());
                LocalDate localDate = Utils.convertToLocalDate(partidoModificar.getFecha());
                dpFecha.setValue(localDate);
    }
    public void btnCancelar(ActionEvent event){
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());//obtener stage desde evento
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(tfLocal, Validator.createEmptyValidator("El equipo local no puede estar vacío"));
        validationSupport.registerValidator(tfVisitante, Validator.createEmptyValidator("El equipo visitante no puede estar vacio"));
        validationSupport.registerValidator(tfResultado, Validator.createEmptyValidator("El resultado no puede estar vacío"));
        validationSupport.registerValidator(cbDivision, Validator.createEmptyValidator("Debe seleccionar division"));
        validationSupport.registerValidator(dpFecha, Validator.createEmptyValidator("Debe seleccionar fecha"));
        //lo mismo con todos los campos

        validationSupport.invalidProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                btnAceptar.setDisable(newValue);
            }
        });
        // btnAceptar.disableProperty().bind(validationSupport.invalidProperty());-->Esto seria lo mismo que lo anterior

        cbDivision.getItems().setAll(Division.values());

    }
}
