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
import co.ucc.diseño.Star.modelo.Profesor;
import co.ucc.diseño.Star.service.ProfesorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Star.com/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping("/crear-profesor")
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.crearProfesor(profesor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Profesor>> obtenerTodosLosProfesores() {
        try {
            return ResponseEntity.ok().body(profesorService.obtenerTodosLosProfesores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obtener-por-id/{id}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable ObjectId id) throws LocalNotFoundException {
        try {
            Optional<Profesor> profesor = profesorService.obtenerProfesorPorId(id);
            return profesor.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/eliminar-profesor/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable ObjectId id) {
        try {
            profesorService.eliminarProfesor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Profesor profesor) {
        try {
            boolean authenticated = profesorService.iniciarSesion(profesor.getNombre(), profesor.getPassword());
            if (authenticated) {
                return ResponseEntity.ok().body("Inicio de sesión exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
    

}
