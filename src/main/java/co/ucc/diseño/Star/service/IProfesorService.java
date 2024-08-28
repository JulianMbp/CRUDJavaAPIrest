package co.ucc.diseño.Star.service;


import java.util.List;
import java.util.Optional;
import co.ucc.diseño.Star.modelo.Profesor;
import org.bson.types.ObjectId;
import co.ucc.diseño.Star.error.LocalNotFoundException;
public interface IProfesorService {
   	Profesor crearProfesor(Profesor profesor);
	List<Profesor> obtenerTodosLosProfesores();
	Optional<Profesor> obtenerProfesorPorId(ObjectId id) throws LocalNotFoundException;
	void eliminarProfesor(ObjectId id);
	boolean iniciarSesion(String correo, String contraseña);
}