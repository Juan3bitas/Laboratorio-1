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

public class GestionarEntrenadoresController {

    @FXML
    private Button bMostrarEntrenador;

    @FXML
    private Button bAgregarEntrenador;

    @FXML
    private Button bActualizarEntrenador;

    @FXML
    private Button bEliminarEntrenador;

    @FXML
    private Button bAtrasEntrenador;

    @SuppressWarnings("exports")
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bMostrarEntrenador) {
            mostrarEntrenador();
        } else if (sourceButton == bAgregarEntrenador) {
            agregarEntrenador();
        } else if (sourceButton == bActualizarEntrenador) {
            actualizarEntrenador();
        } else if (sourceButton == bEliminarEntrenador) {
            eliminarEntrenador();
        } else if (sourceButton == bAtrasEntrenador) {
            irAtras();
        }
    }

    private void mostrarEntrenador() {
        // Aquí deberías cargar la interfaz para mostrar entrenadores
        System.out.println("Mostrar Entrenadores");
        // Por ejemplo, podrías cargar otra escena o abrir una nueva ventana
        try {
            Stage stage = (Stage) bMostrarEntrenador.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("MostrarEntrenadores.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Mostrar Entrenadores");
        }
    }

    private void agregarEntrenador() {
        // Aquí deberías cargar la interfaz para agregar un nuevo Entrenador
        System.out.println("Agregar Entrenadores");
        try {
            Stage stage = (Stage) bAgregarEntrenador.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarEntrenadores.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Agregar Entrenadores");
        }
    }

    private void actualizarEntrenador() {
        // Aquí deberías cargar la interfaz para actualizar un Entrenador existente
        System.out.println("Actualizar Entrenadores");
        try {
            Stage stage = (Stage) bActualizarEntrenador.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ActualizarEntrenadores.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Actualizar Entrenadores");
        }
    }

    private void eliminarEntrenador() {
        // Aquí deberías cargar la interfaz para eliminar un Entrenador existente
        System.out.println("Eliminar Entrenadores");
        try {
            Stage stage = (Stage) bEliminarEntrenador.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("EliminarEntrenadores.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la interfaz de Eliminar Entrenadores");
        }
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasEntrenador.getScene().getWindow();
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