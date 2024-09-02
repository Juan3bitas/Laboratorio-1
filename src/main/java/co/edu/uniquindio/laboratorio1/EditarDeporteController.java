package co.edu.uniquindio.laboratorio1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarDeporteController {

    @FXML
    private TextField tfNombreDeporte;

    @FXML
    private TextField tfDescripcionDeporte;

    @FXML
    private ChoiceBox<Dificultad> cbDificultadDeporte;

    @FXML
    private Button bGuardar;

    @FXML
    private Button bCancelar;

    private Deporte deporteSeleccionado;

    @FXML
    public void initialize() {
        // Inicialización de datos de la ChoiceBox si es necesario
        cbDificultadDeporte.getItems().setAll(Dificultad.values());
    }

    public void inicializar(Deporte deporte) {
        this.deporteSeleccionado = deporte;
        tfNombreDeporte.setText(deporte.getNombre());
        tfDescripcionDeporte.setText(deporte.getDescripcion());
        cbDificultadDeporte.setValue(deporte.getDificultad());
    }

    @FXML
    public void guardarCambios() {
        // Validar los campos
        String nombre = tfNombreDeporte.getText();
        String descripcion = tfDescripcionDeporte.getText();
        Dificultad dificultad = cbDificultadDeporte.getValue();

        if (nombre.isEmpty() || descripcion.isEmpty() || dificultad == null) {
            showError("Por favor, completa todos los campos.");
            return;
        }

        // Actualizar el deporte
        deporteSeleccionado.setNombre(nombre);
        deporteSeleccionado.setDescripcion(descripcion);
        deporteSeleccionado.setDificultad(dificultad);

        // Cerrar la ventana
        cerrarVentana();
    }

    @FXML
    public void cancelarEdicion() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombreDeporte.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}