package co.ucc.diseno.Star;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.ucc.diseño.Star.EstudianteController;
import co.ucc.diseño.Star.ProfesorController;
import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Estudiante;
import co.ucc.diseño.Star.modelo.Profesor;
import co.ucc.diseño.Star.service.IProfesorService;

@ExtendWith(MockitoExtension.class)
public class ProfesorControllerTest {
	@Mock
	private IProfesorService profesorServiceMock;
	@InjectMocks
	private ProfesorController profesorController;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testCrearProfesor()throws LocalNotFoundException{
		Profesor profesor = new Profesor("profesor", "profesor1234", "profesor@gmail.com");
		when(profesorServiceMock.crearProfesor(any(Profesor.class))).thenReturn(profesor);
		ResponseEntity<Profesor> response = profesorController.crearProfesor(profesor);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());;
		assertEquals(profesor, response.getBody());
	}
	@Test
	public void testEliminarProfesor() {
		ObjectId id = new ObjectId();
		doNothing().when(profesorServiceMock).eliminarProfesor(id);
		ResponseEntity<Void> response = profesorController.eliminarProfesor(id);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(profesorServiceMock, times(1)).eliminarProfesor(id);

	}
	@Test
	public void testObtenerProfesorPorId_Notfound() throws LocalNotFoundException {
		ObjectId id = new ObjectId();
		
		try {
			when(profesorServiceMock.obtenerProfesorPorId(id)).thenReturn(Optional.empty());
			
		}catch(LocalNotFoundException e) {
			e.printStackTrace();
		}
		ResponseEntity<Profesor> response = profesorController.obtenerProfesorPorId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		try {
			verify(profesorServiceMock, times(1)).obtenerProfesorPorId(id);
		}catch(LocalNotFoundException e){
			e.printStackTrace();
		}
	}
	@Test
	public void testObtenerProfesorPorId() throws LocalNotFoundException {
		ObjectId id = new ObjectId();
		Profesor profesor = new Profesor("profesro", "profesor123", "profesor@gmail.com");
		
		try {
			when(profesorServiceMock.obtenerProfesorPorId(id)).thenReturn(Optional.empty());
			
		}catch(LocalNotFoundException e) {
			e.printStackTrace();
		}
		ResponseEntity<Profesor> response = profesorController.obtenerProfesorPorId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		try {
			verify(profesorServiceMock, times(1)).obtenerProfesorPorId(id);
		}catch(LocalNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerTodosLosEstudiantes() {
		List<Profesor> profesor = new ArrayList<>();
		when(profesorServiceMock.obtenerTodosLosProfesores()).thenReturn(profesor);
		ResponseEntity<List<Profesor>> response = profesorController.obtenerTodosLosProfesores();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(profesor, response.getBody());
		
	}
		
}

