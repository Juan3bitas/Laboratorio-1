package co.edu.uniquindio.laboratorio1;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EliminarDeportesController {

    @FXML
    private Button bAtrasEliminarDeportes;

    @FXML
    private Button bBuscarEliminarDeportes;

    @FXML
    private Button bGuardarEliminarDeportes;

    @FXML
    private Label lTituloEliminarDeportes;

    @FXML
    private ListView<Deporte> lvDeportes;

    @FXML
    private Text tTextoBuscar;

    @FXML
    private TextField tfBuscarDeportes;

    @FXML
    public void initialize() {
        // Cargar todos los deportes en el ListView al inicializar la ventana
        lvDeportes.getItems().addAll(Club.obtenerDeportes());
    }

    @SuppressWarnings("exports")
    @FXML
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bAtrasEliminarDeportes) {
            irAtras();
        } else if (sourceButton == bBuscarEliminarDeportes) {
            buscarDeporte();
        } else if (sourceButton == bGuardarEliminarDeportes) {
            eliminarDeporteSeleccionado();
        }
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasEliminarDeportes.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionarDeportes.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la página de gestionar deportes");
        }
    }

    private void buscarDeporte() {
        String nombreDeporte = tfBuscarDeportes.getText().trim().toLowerCase();
        lvDeportes.getItems().clear();  // Limpiar la lista antes de agregar los resultados de la búsqueda

        if (!nombreDeporte.isEmpty()) {
            for (Deporte deporte : Club.obtenerDeportes()) {
                if (deporte.getNombre().toLowerCase().contains(nombreDeporte)) {
                    lvDeportes.getItems().add(deporte);
                }
            }

            if (lvDeportes.getItems().isEmpty()) {
                showError("No se encontraron deportes con ese nombre");
            }
        } else {
            // Si el campo de búsqueda está vacío, mostrar todos los deportes
            lvDeportes.getItems().addAll(Club.obtenerDeportes());
        }
    }

    private void eliminarDeporteSeleccionado() {
        Deporte deporteSeleccionado = lvDeportes.getSelectionModel().getSelectedItem();
        if (deporteSeleccionado != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmación de Eliminación");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar el deporte: " + deporteSeleccionado.getNombre() + "?");
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Club.obtenerDeportes().remove(deporteSeleccionado);  // Eliminar el deporte de la lista global
                    lvDeportes.getItems().remove(deporteSeleccionado);  // Eliminarlo de la lista en pantalla

                    try {
                        Club.guardarDatos(); // Guardar los datos después de eliminar el deporte
                        showInfo("Deporte eliminado correctamente");
                    } catch (IOException e) {
                        showError("Error al guardar los datos.");
                    }
                }
            });
        } else {
            showError("Por favor, seleccione un deporte para eliminar");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}