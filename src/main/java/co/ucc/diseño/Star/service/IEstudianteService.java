package co.ucc.diseño.Star.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Estudiante;




public interface IEstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> obtenerTodosLosEstudiantes();
    Optional<Estudiante> obtenerEstudiantePorId(ObjectId id) throws LocalNotFoundException;
    void eliminarEstudiante(ObjectId id);
    boolean iniciarSesion(String nombre, String contraseña);
} 

