package co.edu.uniquindio.laboratorio1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private static List<Deporte> listaDeportes = new ArrayList<>();
    private static List<Entrenador> listaEntrenadores = new ArrayList<>();

    private String nombre;
    private String descripcion;
    private Administrador administrador;


    public Club(String nombre, String descripcion, Administrador administrador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.administrador = administrador;
    }
    
    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    //CRUD

    public static void agregarMiembro(Deporte deporte, Miembro miembro){
        deporte.agregarMiembro(miembro);
    }

    public static void eliminarMiembro(Deporte deporte, Miembro miembro){
        deporte.eliminarMiembro(miembro);
    }

    @SuppressWarnings("static-access")
    public static void inscribirMiembroADeporte(Deporte deporte, Miembro miembro) {
        //si la dificultad del deporte es alta, los miembros juveniles no pueden ser inscritos
        if (deporte.getDificultad() == Dificultad.ALTO && miembro.getTipoMiembro() == TipoMiembro.JUVENIL) {
            throw new IllegalArgumentException("Los miembros juveniles no se pueden inscribir en deportes de alta dificultad.");
        }

        //si el miembro ya esta inscrito, no se puede volver a inscribir
        if (deporte.obtenerMiembros().contains(miembro)) {
            throw new IllegalArgumentException("El miembro ya está inscrito en este entrenamiento.");
        }

        //inscribe al miembro
        deporte.agregarMiembro(miembro);
        System.out.println("El miembro " + miembro.getNombre() + " ha sido inscrito en el entrenamiento de: " + deporte.getNombre());
    }

    // Método para agregar un deporte
    public static void agregarDeporte(Deporte deporte) {
        listaDeportes.add(deporte);
    }

    // Método para obtener todos los deportes
    public static List<Deporte> obtenerDeportes() {
        return listaDeportes;
    }

    // Método para agregar un entrenador
    public static void agregarEntrenador(Entrenador entrenador) {
        listaEntrenadores.add(entrenador);
    }

    // Método para obtener todos los entrenadores
    public static List<Entrenador> obtenerEntrenador() {
        return listaEntrenadores;
    }

    public static void guardarDatos() throws IOException {
        try (ObjectOutputStream oosDeportes = new ObjectOutputStream(new FileOutputStream("deportes.dat"));
             ObjectOutputStream oosEntrenadores = new ObjectOutputStream(new FileOutputStream("entrenadores.dat"));) {
            oosDeportes.writeObject(listaDeportes);
            oosEntrenadores.writeObject(listaEntrenadores);
        }
    }

    // Método para cargar datos desde archivos
    @SuppressWarnings("unchecked")
    public static void cargarDatos() throws IOException, ClassNotFoundException {
        File deportesFile = new File("deportes.dat");
        File entrenadoresFile = new File("entrenadores.dat");
        

        // Cargar deportes
        if (deportesFile.exists()) {
            try (ObjectInputStream oisDeportes = new ObjectInputStream(new FileInputStream(deportesFile))) {
                listaDeportes = (List<Deporte>) oisDeportes.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Error al cargar los deportes: " + e.getMessage(), e);
            }
        } else {
            System.out.println("El archivo de deportes no existe.");
        }

        // Cargar entrenadores
        if (entrenadoresFile.exists()) {
            try (ObjectInputStream oisEntrenadores = new ObjectInputStream(new FileInputStream(entrenadoresFile))) {
                listaEntrenadores = (List<Entrenador>) oisEntrenadores.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Error al cargar los entrenadores: " + e.getMessage(), e);
            }
        } else {
            System.out.println("El archivo de entrenadores no existe.");
        }

    }
}
