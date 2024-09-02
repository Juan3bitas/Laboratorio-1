package co.edu.uniquindio.laboratorio1;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActualizarDeportesController {

    @FXML
    private Button bAtrasActualizarDeportes;

    @FXML
    private Button bBuscarActualizarDeportes;

    @FXML
    private Button bGuardarActualizarDeportes;

    @FXML
    private Label lTituloActualizarDeportes;

    @FXML
    private ListView<Deporte> lvDeportes;

    @FXML
    private Text tTextoBuscar;

    @FXML
    private TextField tfBuscarDeportes;

    @FXML
    public void initialize() {
        // Inicializar la lista de deportes
        lvDeportes.getItems().addAll(Club.obtenerDeportes());
    }

    @SuppressWarnings("exports")
    @FXML
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bAtrasActualizarDeportes) {
            irAtras();
        } else if (sourceButton == bBuscarActualizarDeportes) {
            buscarDeporte();
        } else if (sourceButton == bGuardarActualizarDeportes) {
            actualizarDeporte();
        }
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasActualizarDeportes.getScene().getWindow();
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

    private void actualizarDeporte() {
        Deporte deporteSeleccionado = lvDeportes.getSelectionModel().getSelectedItem();
        if (deporteSeleccionado == null) {
            showError("Por favor, selecciona un deporte de la lista.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarDeporte.fxml"));
            Parent root = loader.load();

            EditarDeporteController controller = loader.getController();
            controller.inicializar(deporteSeleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Deporte");
            stage.setScene(new Scene(root));
            stage.initOwner(bGuardarActualizarDeportes.getScene().getWindow()); // Establece la ventana principal como propietario
            stage.showAndWait();

            // Refrescar la lista después de la edición
            lvDeportes.getItems().clear();
            lvDeportes.getItems().addAll(Club.obtenerDeportes()); // Actualizar la lista de deportes
            Club.guardarDatos(); // Guardar los datos después de la edición

        } catch (IOException e) {
            showError("Error al cargar la ventana de edición.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
