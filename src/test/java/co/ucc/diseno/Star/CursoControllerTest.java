package co.ucc.diseno.Star;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.ucc.diseño.Star.CursoController;
import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Curso;
import co.ucc.diseño.Star.service.CursoService;
import co.ucc.diseño.Star.service.ICursoService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTest {
	
	@Mock
	private CursoService cursoServiceMock;
	
	@InjectMocks 
	private CursoController cursoController;
	
	@BeforeEach
	public void setUp () {
		MockitoAnnotations.openMocks(this); 
	}
	
	@Test 
	public void testObtenerTodosLosCursos() {
		List<Curso> cursos = new ArrayList<> ();
		when(cursoServiceMock.obtenerTodosLosCursos()).thenReturn(cursos);
		
		ResponseEntity<List<Curso>> response = cursoController.obtenerTodosLosCursos(); 
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cursos, response.getBody());
		
	}
	
    @Test
    public void testObtenerCursoPorId() {
        ObjectId id = new ObjectId();
        Curso curso = new Curso(id, "titulo", "subtitulo", "descripcion", "idioma", "cat", "material");
        try {
			when(cursoServiceMock.obtenerCursoPorId(id)).thenReturn(Optional.of(curso));
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ResponseEntity<Curso> response = cursoController.obtenerCursoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
        try {
			verify(cursoServiceMock, times(1)).obtenerCursoPorId(id);
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testObtenerCursoPorId_NotFound() {
        ObjectId id = new ObjectId();
        try {
			when(cursoServiceMock.obtenerCursoPorId(id)).thenReturn(Optional.empty());
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			  
		}

        ResponseEntity<Curso> response = cursoController.obtenerCursoPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        try {
			verify(cursoServiceMock, times(1)).obtenerCursoPorId(id);
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testEliminarCurso() {
        ObjectId id = new ObjectId();

        doNothing().when(cursoServiceMock).eliminarCurso(id);

        ResponseEntity<Void> response = cursoController.eliminarCurso(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cursoServiceMock, times(1)).eliminarCurso(id);
    }
    
    @Test
    public void testEditarCurso() {
        ObjectId id = new ObjectId();
        Curso curso = new Curso(id, "titulo", "subtitulo", "descripcion", "idioma", "cat", "material");
        try {
			when(cursoServiceMock.editarCurso(eq(id), any(Curso.class))).thenReturn(curso);
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ResponseEntity<Curso> response = cursoController.editarCurso(id, curso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
        try {
			verify(cursoServiceMock, times(1)).editarCurso(eq(id), any(Curso.class));
		} catch (LocalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testCrearCurso() {
        Curso curso = new Curso(new ObjectId(), "Curso de prueba", "Subtítulo de prueba", "Descripción de prueba", "Español", "Categoria de prueba", "Material de prueba");
        when(cursoServiceMock.crearCurso(any(Curso.class))).thenReturn(curso);

        ResponseEntity<Curso> response = cursoController.crearCurso(curso);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

}
    


