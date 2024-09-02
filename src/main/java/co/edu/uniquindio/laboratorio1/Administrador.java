package co.edu.uniquindio.laboratorio1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private static List<Entrenamiento> listaEntrenamientos = new ArrayList<>();

    private String nombre;
    private String identificacion;

    public Administrador(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public static List<Entrenamiento> obtenerEntrenamientos() {
        return listaEntrenamientos;
    }

    public static void agregarEntrenamiento(Entrenamiento entrenamiento) {
        LocalDate fechaActual = LocalDate.now();

        // Obtener la fecha y estado del entrenamiento
        LocalDate fechaEntrenamiento = entrenamiento.getFecha();
        EstadoEntrenamiento estadoEntrenamiento = entrenamiento.getEstadoEntrenamiento();

        // Validar que el estado y la fecha sean consistentes
        if (fechaEntrenamiento.isAfter(fechaActual) && estadoEntrenamiento == EstadoEntrenamiento.COMPLETADO) {
            throw new IllegalArgumentException("Un entrenamiento completado no puede tener una fecha posterior a la fecha actual.");
        }

        if (fechaEntrenamiento.isBefore(fechaActual) && estadoEntrenamiento == EstadoEntrenamiento.PROGRAMADO) {
            throw new IllegalArgumentException("Un entrenamiento programado no puede tener una fecha anterior a la fecha actual.");
        }
        listaEntrenamientos.add(entrenamiento);
    }

    public void eliminarEntrenamiento(Entrenador entrenador, Entrenamiento entrenamiento){
        if (entrenamiento == null) {
            System.out.println("El entrenamiento proporcionado es nulo y no puede ser eliminado.");
        }
        entrenador.eliminarEntrenamiento(entrenamiento);
    }

    public List<Entrenamiento> leerEntrenamientos(Entrenador entrenador) {
        List<Entrenamiento> entrenamientos = entrenador.getEntrenamientos();
        if (entrenamientos.isEmpty()) {
            System.out.println("No se encontraron entrenamientos para el entrenador: " + entrenador.getNombre());
        }
        return entrenamientos;
    }

    public void actualizarEntrenamientos(Entrenamiento entrenamientoModificable, Entrenador entrenador, LocalDate nuevaFecha, 
                                            Duration nuevaDuracion, EstadoEntrenamiento nuevoEstadoEntrenamiento, Deporte nuevoDeporte){
        if (entrenamientoModificable == null) {
        throw new IllegalArgumentException("El entrenamiento proporcionado es nulo, por lo que no puede ser actualizado.");
        }

        if (nuevaFecha != null) {
        entrenamientoModificable.setFecha(nuevaFecha);
        }

        if (nuevaDuracion != null) {
        entrenamientoModificable.setDuracion(nuevaDuracion);
        }
        
        if (nuevoEstadoEntrenamiento != null) {
        entrenamientoModificable.setEstadoEntrenamiento(nuevoEstadoEntrenamiento);
        }
        
        if (nuevoDeporte != null) {
        entrenamientoModificable.setDeporte(nuevoDeporte);
        }   
    }

    public static void guardarDatos() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("entrenamientos.dat"))) {
            oos.writeObject(listaEntrenamientos);
        }
    }

    @SuppressWarnings("unchecked")
    public static void cargarDatos() throws IOException, ClassNotFoundException {
        File file = new File("entrenamientos.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                listaEntrenamientos = (List<Entrenamiento>) ois.readObject();
            }
        }
    }
}