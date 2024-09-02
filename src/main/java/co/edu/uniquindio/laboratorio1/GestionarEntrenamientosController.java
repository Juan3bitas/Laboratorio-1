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

public class GestionarEntrenamientosController {

    @FXML
    private Button bMostrarEntrenamiento;

    @FXML
    private Button bAgregarEntrenamiento;

    @FXML
    private Button bActualizarEntrenamiento;

    @FXML
    private Button bEliminarEntrenamiento;

    @FXML
    private Button bAtrasEntrenamiento;

    @SuppressWarnings("exports")
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bMostrarEntrenamiento) {
            mostrarEntrenamiento();
        } else if (sourceButton == bAgregarEntrenamiento) {
            agregarEntrenamiento();
        } else if (sourceButton == bActualizarEntrenamiento) {
            actualizarEntrenamiento();
        } else if (sourceButton == bEliminarEntrenamiento) {
            eliminarEntrenamiento();
        } else if (sourceButton == bAtrasEntrenamiento) {
            irAtras();
        }
    }

    private void mostrarEntrenamiento() {
        // Aquí deberías cargar la interfaz para mostrar entrenamientos
        System.out.println("Mostrar Entrenamientos");
        // Por ejemplo, podrías cargar otra escena o abrir una nueva ventana
        try {
            Stage stage = (Stage) bMostrarEntrenamiento.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("MostrarEntrenamientos.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Mostrar Entrenamientos");
        }
    }

    private void agregarEntrenamiento() {
        // Aquí deberías cargar la interfaz para agregar un nuevo entrenamiento
        System.out.println("Agregar Entrenamientos");
        try {
            Stage stage = (Stage) bAgregarEntrenamiento.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarEntrenamientos.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Agregar Entrenamientos");
        }
    }

    private void actualizarEntrenamiento() {
        // Aquí deberías cargar la interfaz para actualizar un entrenamiento existente
        System.out.println("Actualizar Entrenamientos");
        try {
            Stage stage = (Stage) bActualizarEntrenamiento.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ActualizarEntrenamientos.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Actualizar Entrenamientos");
        }
    }

    private void eliminarEntrenamiento() {
        // Aquí deberías cargar la interfaz para eliminar un entrenamiento existente
        System.out.println("Eliminar Entrenamientos");
        try {
            Stage stage = (Stage) bEliminarEntrenamiento.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("EliminarEntrenamientos.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Eliminar Entrenamientos");
        }
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasEntrenamiento.getScene().getWindow();
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