package co.ucc.diseño.Star.repository;

import co.ucc.diseño.Star.modelo.Estudiante;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends MongoRepository<Estudiante, ObjectId> {

	Estudiante findByNombre(String nombre);  

}
