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
import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Estudiante;
import co.ucc.diseño.Star.service.EstudianteService;
import co.ucc.diseño.Star.service.IEstudianteService;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@ExtendWith(MockitoExtension.class)
public class EstudianteControllerTest {

	@Mock
	private IEstudianteService estudianteServiceMock;
	
	@InjectMocks
	private EstudianteController estudianteController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testCrearEstudiante() throws LocalNotFoundException {
		Estudiante estudiante = new Estudiante("prueba", "prueba1234", "example@gmail.com");
		when(estudianteServiceMock.crearEstudiante(any(Estudiante.class))).thenReturn(estudiante);
		
		ResponseEntity<Estudiante> response = estudianteController.crearEstudiante(estudiante);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(estudiante, response.getBody());
		
	}
	
	@Test
	public void testEliminarEstudiante() {
		ObjectId id = new ObjectId();
		doNothing().when(estudianteServiceMock).eliminarEstudiante(id);
		ResponseEntity<Void> response = estudianteController.eliminarEstudiante(id);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(estudianteServiceMock, times(1)).eliminarEstudiante(id);

	}
	
	@Test
	public void testObtenerEstudiantePorId_NotFound() {
		ObjectId id = new ObjectId();
		
		try {
			when(estudianteServiceMock.obtenerEstudiantePorId(id)).thenReturn(Optional.empty());
			
		}catch(LocalNotFoundException e) {
			e.printStackTrace();
		}
		ResponseEntity<Estudiante> response = estudianteController.obtenerEstudiantePorId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		try {
			verify(estudianteServiceMock, times(1)).obtenerEstudiantePorId(id);
		}catch(LocalNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerEstudiantePorId() {
		ObjectId id = new ObjectId();
		Estudiante estudiante = new Estudiante("correo", "password", "nombre");
		
		try {
			when(estudianteServiceMock.obtenerEstudiantePorId(id)).thenReturn(Optional.empty());
			
		}catch(LocalNotFoundException e) {
			e.printStackTrace();
		}
		ResponseEntity<Estudiante> response = estudianteController.obtenerEstudiantePorId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		try {
			verify(estudianteServiceMock, times(1)).obtenerEstudiantePorId(id);
		}catch(LocalNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerTodosLosEstudiantes() {
		List<Estudiante> estudiante = new ArrayList<>();
		when(estudianteServiceMock.obtenerTodosLosEstudiantes()).thenReturn(estudiante);
		ResponseEntity<List<Estudiante>> response = estudianteController.obtenerTodosLosEstudiantes();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(estudiante, response.getBody());
	}
}