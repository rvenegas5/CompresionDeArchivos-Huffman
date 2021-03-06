/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.HuffmanTree;
import ec.edu.espol.modelo.Util;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WILLIAM
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button btnSalir;
    @FXML
    private Label ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void selectFile(ActionEvent event) {
        try {
            FileChooser fil_chooser = new FileChooser();
            fil_chooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
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
        HuffmanTree arbol = new HuffmanTree();

        Util util = new Util();
        String path = ruta.getText();
        if (path.equals("no ha seleccionado un archivo")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("NO SE HA SELECCIONADO UN ARCHIVO");
            alert.showAndWait();
        } else {
            HashMap<String, Integer> frequencias = util.calcularFrecuencias(util.leerTexto(path));
            arbol.calcularArbol(frequencias);
            HashMap<String, String> codigos = arbol.calcularCodigos();
            String codBin = HuffmanTree.codificar(util.leerTexto(path), codigos);
            String codHex = util.binarioHexadecimal(codBin);
            util.guardarTexto(path, codHex, codigos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXITO");
            alert.setContentText("ARCHIVO COMPRIMIDO EXITOSAMENTE!");
            alert.showAndWait();

            ruta.setText("no ha seleccionado un archivo");
        }

    }

    @FXML
    private void desComprimirArchivo(ActionEvent event) {

        Util util = new Util();
        String path = ruta.getText();
        String validarPath = "no ha seleccionado un archivo";
        if (path.equals(validarPath)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("NO SE HA SELECCIONADO UN ARCHIVO");
            alert.showAndWait();
        } else {
            HashMap<String, String> codigos = HuffmanTree.obtenerCodigos(path + "_compress.txt");
            String codBin = util.hexadecimalBinario(util.leerTexto(path));
            String codDecodificado = HuffmanTree.decodificar(codBin, codigos);

            util.guardarTexto(path, codDecodificado, codigos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXITO");
            alert.setContentText("ARCHIVO COMPRIMIDO EXITOSAMENTE!");
            alert.showAndWait();

            ruta.setText("no ha seleccionado un archivo");
        }
    }

    @FXML
    private void salirVentanaPrincipal(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

}
