module co.edu.uniquindio.laboratorio1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens co.edu.uniquindio.laboratorio1 to javafx.fxml;
    exports co.edu.uniquindio.laboratorio1;
}
