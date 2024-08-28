package co.ucc.diseño.Star.repository;

import co.ucc.diseño.Star.modelo.Profesor;
import co.ucc.diseño.Star.modelo.Usuario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, ObjectId> {

	Profesor findByNombre(String nombre); 
}
