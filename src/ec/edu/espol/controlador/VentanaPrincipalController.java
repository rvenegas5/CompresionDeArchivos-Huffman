/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WILLIAM
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button btnSelectFile;
    @FXML
    private Button btnComprimirArchivo;
    @FXML
    private Button btnDescomprimir;
    @FXML
    private Button btnSalir;
    @FXML
    private Label ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void selectFile(ActionEvent event) {
        try {
            FileChooser fil_chooser = new FileChooser();
            File file = fil_chooser.showOpenDialog(null);

            if (file != null) {
                ruta.setText(file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void comprimirArchivo(ActionEvent event) {
        // Comprimir el archivo
    }
    
    @FXML
    private void desComprimirArchivo(ActionEvent event) {
        // Comprimir el archivo
    }

    @FXML
    private void salirVentanaPrincipal(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

}
