package co.edu.uniquindio.laboratorio1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AgregarDeportesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bAtrasAgregarDeportes;

    @FXML
    private Button bGuardarAgregarDeportes;

    @FXML
    private ChoiceBox<Dificultad> cbDificultadDeporte;

    @FXML
    private Label lTituloAgregarDeportes;

    @FXML
    private Text tDescripcionDeporte;

    @FXML
    private Text tDescripcionDeporte1;

    @FXML
    private Text tNombreDeporte;

    @FXML
    private TextField tfDescripcionDeporte;

    @FXML
    private TextField tfNombreDeporte;

    @FXML
    public void initialize() {
        cbDificultadDeporte.getItems().addAll(Dificultad.values());
    }

    @SuppressWarnings("exports")
    @FXML
    public void click(javafx.event.ActionEvent event) {
        Button sourceButton = (Button) event.getSource();

        if (sourceButton == bAtrasAgregarDeportes) {
            irAtras();
        } else if (sourceButton == bGuardarAgregarDeportes) {
            guardarDeporte();
        }
    }

    private void irAtras() {
        try {
            Stage stage = (Stage) bAtrasAgregarDeportes.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionarDeportes.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar la página de gestionar deportes");
        }
    }

    private void guardarDeporte() {
        String nombre = tfNombreDeporte.getText();
        String descripcion = tfDescripcionDeporte.getText();
        Dificultad dificultad = cbDificultadDeporte.getValue();

        if (nombre == null || nombre.trim().isEmpty() ||
            descripcion == null || descripcion.trim().isEmpty() ||
            dificultad == null) {

            showError("Por favor, complete todos los campos.");
            return;
        }

        Deporte nuevoDeporte = new Deporte(nombre, descripcion, dificultad);
        Club.agregarDeporte(nuevoDeporte); // Guardar el deporte en el club

        // Persistir los cambios
        try {
            Club.guardarDatos(); // Guardar los datos después de agregar el deporte
            showConfirmation("Deporte guardado con éxito.");
            // Limpiar los campos
            tfNombreDeporte.clear();
            tfDescripcionDeporte.clear();
            cbDificultadDeporte.setValue(null);
        } catch (IOException e) {
            showError("Error al guardar los datos.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
