package co.ucc.diseño.Star.modelo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Positive;

public class Estudiante extends Usuario implements IUsuario {
	
	@Length(min=5, max=30)
	private String nombre;
	
	
	public Estudiante(String correo, String password, String nombre) {
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
		Estudiante estudianteEncontrado = buscarEstudiante(nombre);
		if (estudianteEncontrado != null) {
			return estudianteEncontrado.getPassword().equals(password) &&
				   estudianteEncontrado.getNombre().equals(nombre);
		}
		return false;
	}
	private Estudiante buscarEstudiante(String correo) {
		return null;
	}
	@Override
	public String toString() {
		return "Estudiante [nombreCompleto=" + nombre + ", getCorreo()=" + getCorreo()
				+ ", getPassword()=" + getPassword() + "]";
	}
}
