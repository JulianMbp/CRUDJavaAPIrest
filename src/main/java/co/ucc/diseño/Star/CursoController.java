package co.ucc.diseño.Star;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Curso;
import co.ucc.diseño.Star.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Star.com/cursos")
public class CursoController {
	
	@Autowired
    private CursoService cursoService;

    @PostMapping("/profesor/crear-cursos")
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crearCurso(curso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/profesor/obtener-cursos")
    public  ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        try {
            return ResponseEntity.ok().body(cursoService.obtenerTodosLosCursos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable ObjectId id){
        try {
            Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
            return curso.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/profesor/eliminar-curso/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable ObjectId id) {
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/profesor/editar-curso/{id}")
    public ResponseEntity<Curso> editarCurso(@PathVariable ObjectId id, @Valid @RequestBody Curso cursoActualizado) {
        try {
            Curso cursoEditado = cursoService.editarCurso(id, cursoActualizado);
            return ResponseEntity.ok().body(cursoEditado);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
