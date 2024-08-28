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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import co.ucc.diseño.Star.CursoController;
import co.ucc.diseño.Star.EstudianteController;
import co.ucc.diseño.Star.error.LocalNotFoundException;
import co.ucc.diseño.Star.modelo.Estudiante;
import co.ucc.diseño.Star.service.ICursoService;
import co.ucc.diseño.Star.service.IEstudianteService;

@ExtendWith(MockitoExtension.class)
public class StarApplicationTests {

    @Mock
    private ICursoService cursoServiceMock;
    private IEstudianteService estudianteServiceMock;

    @InjectMocks
    private CursoController cursoController;
    private EstudianteController estudianteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
    }
    
  
}