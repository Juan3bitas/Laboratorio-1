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

public class ActualizarEntrenadoresController {

    @FXML
    private Button bAtrasActualizarEntrenadores;

    @FXML
    private Button bBuscarActualizarEntrenadores;

    @FXML
    private Button bGuardarActualizarEntrenadores;

    @FXML
    private Label lTituloActualizarEntrenadores;

    @FXML
    private ListView<Entrenador> lvEntrenadores;

    @FXML
    private Text tTextoBuscar;

    @FXML
    private TextField tfBuscarEntrenadores;

    @FXML
    public void initialize() {
        // Inicializar la lista de entrenadores
        lvEntrenadores.getItems().addAll(Club.obtenerEntrenador());
    }

    @SuppressWarnings("exports")
    @FXML
    public void click(javafx.event.ActionEvent event) {
        // Determinar cuál botón fue presionado y llamar al método correspondiente
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bAtrasActualizarEntrenadores) {
            irAtras();
        }
        else if (sourceButton == bBuscarActualizarEntrenadores) {
            buscarEntrenador();
            }
        else if (sourceButton ==bGuardarActualizarEntrenadores){
            actualizarEntrenador();
        }
            
    }

    private void irAtras() {
        // Regresar a la página principal
        try {
            Stage stage = (Stage) bAtrasActualizarEntrenadores.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionarEntrenadores.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la página de gestionar entrenadores");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void buscarEntrenador() {
        String nombreEntrenador = tfBuscarEntrenadores.getText().trim().toLowerCase();
        lvEntrenadores.getItems().clear();  // Limpiar la lista antes de agregar los resultados de la búsqueda

        if (!nombreEntrenador.isEmpty()) {
            for (Entrenador entrenador : Persistencia.cargarEntrenadores()) {
                if (entrenador.getNombre().toLowerCase().contains(nombreEntrenador)) {
                    lvEntrenadores.getItems().add(entrenador);
                }
            }

            if (lvEntrenadores.getItems().isEmpty()) {
                showError("No se encontraron entrenadores con ese nombre");
            }
        } else { 
            // Si el campo de búsqueda está vacío, mostrar todos los entrenadores
            lvEntrenadores.getItems().addAll(Club.obtenerEntrenador());
        }
    }

    private void actualizarEntrenador() {
        Entrenador entrenadorSeleccionado = lvEntrenadores.getSelectionModel().getSelectedItem();
        if (entrenadorSeleccionado == null) {
            showError("Por favor, selecciona un entrenador de la lista.");  
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarEntrenador.fxml"));
            Parent root = loader.load();

            EditarEntrenadorController controller = loader.getController();
            controller.inicializar(entrenadorSeleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Entrenador");
            stage.setScene(new Scene(root));
            stage.initOwner(bGuardarActualizarEntrenadores.getScene().getWindow()); // Establece la ventana principal como propietario
            stage.showAndWait();

            // Refrescar la lista después de la edición
            lvEntrenadores.getItems().clear();
            lvEntrenadores.getItems().addAll(Club.obtenerEntrenador()); // Actualizar la lista de entrenadores
            Club.guardarDatos(); // Guardar los datos después de la edición

        } catch (IOException e) {
            showError("Error al cargar la ventana de edición.");
        }
    }
}