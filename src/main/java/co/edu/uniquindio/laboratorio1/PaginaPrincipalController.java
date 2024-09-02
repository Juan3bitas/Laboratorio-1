package co.edu.uniquindio.laboratorio1;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaginaPrincipalController {

    @SuppressWarnings("exports")
    public void click(ActionEvent event) {
        // Asegúrate de que el origen del evento es un botón
        Button button = (Button) event.getSource();
        String fxmlFile = null;

        // Usa el ID del botón para determinar qué archivo FXML cargar
        if (button.getId().equals("bDeportes")) {
            fxmlFile = "GestionarDeportes.fxml";
        } else if (button.getId().equals("bEntrenadores")) {
            fxmlFile = "GestionarEntrenadores.fxml"; // Asegúrate de tener este archivo
        } else if (button.getId().equals("bMiembros")) {
            fxmlFile = "GestionarMiembros.fxml"; // Asegúrate de tener este archivo
        } else if (button.getId().equals("bEntrenamientos")) {
            fxmlFile = "GestionarEntrenamientos.fxml"; // Asegúrate de tener este archivo
        }

        if (fxmlFile != null) {
            try {
                // Cargar el archivo FXML correspondiente
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                // Obtener el escenario actual y cambiar la escena
                Stage stage = (Stage) button.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
                showError("Error al cargar la interfaz " + fxmlFile);
            }
        }
    }

    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}