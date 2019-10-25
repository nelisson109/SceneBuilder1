package com.martin.views;
import com.martin.Utils.Utils;
import com.martin.logica.Logica;
import com.martin.models.Division;
import com.martin.models.Partido;
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

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaAltaController {

        private Partido partidoModificar;
        private ObservableList<Division> categorias = FXCollections.observableArrayList();

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
        /*     ObservableList<Division> categorias = FXCollections.observableArrayList();
             categorias.add(Division.PRIMERA);
             categorias.add(Division.SEGUNDA);
             categorias.add(Division.TERCERA);
             cbDivision.setItems(FXCollections.observableArrayList(Division.values()));
             cbDivision.getItems().setAll(Division.values());*/
             categorias.add(Division.PRIMERA);
             categorias.add(Division.SEGUNDA);
             categorias.add(Division.TERCERA);
             cbDivision.getItems().setAll(Division.values());
                 if (partidoModificar!=null) {//si estamos modificando
                     partidoModificar.setEquipoLocal(tfLocal.getText());
                     partidoModificar.setEquipoVisitante(tfVisitante.getText());
                     partidoModificar.setResultado(tfResultado.getText());

                     partidoModificar.setD((Division) cbDivision.getSelectionModel().getSelectedItem());
                     LocalDate localDate = Utils.convertToLocalDate(partidoModificar.getFecha());

                     Logica.getInstance().modificarPartido(partidoModificar);

                 }
                 else{//si estamos haciendo un alta normal

                     LocalDate localDate = (LocalDate) dpFecha.getValue();
                     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                     Partido partido = new Partido(tfLocal.getText(), tfVisitante.getText(), (Division)cbDivision.getSelectionModel().getSelectedItem(), tfResultado.getText(), date);
                     Logica.getInstance().addPartido(partido);
                 }

             Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());//obtener stage desde evento
             stage.close();
    }
    public void setPartidoModificar(Partido partidoModificar){//llamado por modificarPartido
                 this.partidoModificar = partidoModificar;
        categorias.add(Division.PRIMERA);
        categorias.add(Division.SEGUNDA);
        categorias.add(Division.TERCERA);
        cbDivision.getItems().setAll(Division.values());
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
}
