package co.ucc.diseño.Star.service;



import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Curso;
import co.ucc.diseño.Star.repository.CursoRepository;

@Service
@Transactional
public class CursoService implements ICursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

	@Override
	public List<Curso> obtenerTodosLosCursos() {
		// TODO Auto-generated method stub
		return cursoRepository.findAll();
	}

	@Override
    public Optional<Curso> obtenerCursoPorId(ObjectId id) throws LocalNotFoundException {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {
            throw new LocalNotFoundException("Curso no encontrado");
        }
        return curso;
    }

	@Override
	public void eliminarCurso(ObjectId id) {
		// TODO Auto-generated method stub
		cursoRepository.deleteById(id);
	}

	 @Override
	    public Curso editarCurso(ObjectId id, Curso cursoActualizado) throws LocalNotFoundException {
	        Optional<Curso> cursoExistente = cursoRepository.findById(id);
	        if (cursoExistente.isPresent()) {
	            Curso curso = cursoExistente.get();
	            curso.setTitulo(cursoActualizado.getTitulo());
	            curso.setSubtitulo(cursoActualizado.getSubtitulo());
	            curso.setDescripcion(cursoActualizado.getDescripcion());
	            curso.setCat(cursoActualizado.getDescripcion());
	            curso.setMaterial(cursoActualizado.getMaterial());
	            curso.setCat(cursoActualizado.getCat());
	            curso.setIdioma(cursoActualizado.getIdioma());
	           return cursoRepository.save(curso);
	        } else {
	            throw new LocalNotFoundException("Curso no encontrado");
	        }
	    }
}	
