package co.ucc.diseño.Star.service;



import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Curso; 
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    Curso crearCurso(Curso curso);
    List<Curso> obtenerTodosLosCursos();
    Optional<Curso> obtenerCursoPorId(ObjectId id) throws LocalNotFoundException;
    void eliminarCurso(ObjectId id);
    Curso editarCurso(ObjectId id, Curso cursoActualizado) throws LocalNotFoundException;
}
