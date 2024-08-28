package co.ucc.diseño.Star.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Profesor;
import co.ucc.diseño.Star.repository.ProfesorRepository;

@Service
public class ProfesorService implements IProfesorService { 

    @Autowired
    private ProfesorRepository profesorRepository; 

    @Override
    public Profesor crearProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> obtenerTodosLosProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> obtenerProfesorPorId(ObjectId id) throws LocalNotFoundException {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (!profesor.isPresent()) {
            throw new LocalNotFoundException("Profesor no encontrado con el ID: " + id);
        }
        return profesor;
    }

    @Override
    public void eliminarProfesor(ObjectId id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public boolean iniciarSesion(String nombre, String contraseña) {
        Profesor profesor = profesorRepository.findByNombre(nombre);
        return profesor != null && profesor.getPassword().equals(contraseña);
    }
}