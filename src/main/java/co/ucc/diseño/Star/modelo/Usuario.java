package co.ucc.diseño.Star.modelo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public abstract class Usuario {
	
	
    @Length(min=10, max=80)
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String correo;
    
    @Length(min=8, max=50)
    private String password;

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public boolean iniciarSesion(String password, String correo) {
        return this.password.equals(password) && this.correo.equals(correo);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        return "Persona [correo=" + correo + ", contraseña=" + password + "]";
    }

}