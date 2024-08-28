package co.ucc.diseño.Star.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.ucc.diseño.Star.modelo.Curso;

public interface CursoRepository extends MongoRepository<Curso, ObjectId> {
	
	
}
