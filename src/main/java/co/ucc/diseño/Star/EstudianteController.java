package co.ucc.diseño.Star;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Estudiante;
import co.ucc.diseño.Star.service.EstudianteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Star.com/estudiante") 
public class EstudianteController {
	
	@Autowired
    private EstudianteService estudianteService;
	
	@PostMapping("/crear-Estudiante")
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody Estudiante estudiante)throws LocalNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.crearEstudiante(estudiante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/obtener-Todos-Estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantes() {
        try {
            return ResponseEntity.ok().body(estudianteService.obtenerTodosLosEstudiantes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
    @GetMapping("/obtener-Estudiante-Id/{Id}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable ObjectId id){
        try {
            Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorId(id);
            return estudiante.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/eliminar-Estudiante-Id/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable ObjectId id) {
        try {
            estudianteService.eliminarEstudiante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/login-Estudiante")
    public ResponseEntity<String> iniciarSesion(@RequestBody Estudiante estudiante) {
        try {
            boolean authenticated = estudianteService.iniciarSesion(estudiante.getNombre(), estudiante.getPassword());
            if (authenticated) {
                return ResponseEntity.ok().body("Inicio de sesión exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tu nombre o contraseña son incorrectos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Servidor not found");
        }
    }

	
}



