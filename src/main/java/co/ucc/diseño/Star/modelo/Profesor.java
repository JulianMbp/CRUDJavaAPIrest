package co.ucc.diseño.Star.modelo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Profesor extends Usuario {
	
	@Length(min=5, max=30)
	@NotBlank(message="Espacio obligatorio")
	private String nombre;
	


    public Profesor(String correo, String password, String nombre) {
    	super(correo, password);
    	this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean iniciarSesion(String password, String nombre) {
        Profesor profesorEncontrado = buscarProfesorPorNombre(nombre);
        if (profesorEncontrado != null) {
            return profesorEncontrado.getPassword().equals(password) &&
                   profesorEncontrado.getNombre().equals(nombre); 
        }
        return false;
    }

    private Profesor buscarProfesorPorNombre(String nombre) {
        return null;  
    }

	@Override
	public String toString() {
		return "Profesor [nombre=" + nombre + ", getCorreo()=" + getCorreo()
				+ ", getContraseña()=" + getPassword() + "]";
	}
}
