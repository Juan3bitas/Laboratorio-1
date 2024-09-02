package co.edu.uniquindio.laboratorio1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class GestionarDeportesController {

    @FXML
    private Button bMostrarDeporte;

    @FXML
    private Button bAgregarDeporte;

    @FXML
    private Button bActualizarDeporte;

    @FXML
    private Button bEliminarDeporte;

    @FXML
    private Button bAtrasDeportes;

    @SuppressWarnings("exports")
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bMostrarDeporte) {
            mostrarDeportes();
        } else if (sourceButton == bAgregarDeporte) {
            agregarDeporte();
        } else if (sourceButton == bActualizarDeporte) {
            actualizarDeporte();
        } else if (sourceButton == bEliminarDeporte) {
            eliminarDeporte();
        } else if (sourceButton == bAtrasDeportes) {
            irAtras();
        }
    }

    private void mostrarDeportes() {
        // Aquí deberías cargar la interfaz para mostrar deportes
        System.out.println("Mostrar Deportes");
        // Por ejemplo, podrías cargar otra escena o abrir una nueva ventana
        try {
            Stage stage = (Stage) bMostrarDeporte.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("MostrarDeportes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Mostrar Deportes");
        }
    }

    private void agregarDeporte() {
        // Aquí deberías cargar la interfaz para agregar un nuevo deporte
        System.out.println("Agregar Deportes");
        try {
            Stage stage = (Stage) bAgregarDeporte.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarDeportes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Agregar Deportes");
        }
    }

    private void actualizarDeporte() {
        // Aquí deberías cargar la interfaz para actualizar un deporte existente
        System.out.println("Actualizar Deportes");
        try {
            Stage stage = (Stage) bActualizarDeporte.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ActualizarDeportes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Actualizar Deportes");
        }
    }

    private void eliminarDeporte() {
        // Aquí deberías cargar la interfaz para eliminar un deporte existente
        System.out.println("Eliminar Deporte");
        try {
            Stage stage = (Stage) bEliminarDeporte.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("EliminarDeportes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Eliminar Deportes");
        }
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasDeportes.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("PaginaPrincipal.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la página principal");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}