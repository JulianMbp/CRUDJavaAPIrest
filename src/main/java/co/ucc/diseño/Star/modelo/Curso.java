package co.ucc.diseño.Star.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.validator.constraints.Length;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document(collection = "cursos")
public class Curso{
	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	private ObjectId id;
	
	@Length(min=3, max=60)
	private String titulo;
	
	@Length(min=3, max=60)
	private String subtitulo;

	@Length(min=3, max=60)
	private String descripcion;
	
	private String idioma;
	private String cat;
	private String material;
	
	public Curso(ObjectId id, String titulo,  String subtitulo,
			String descripcion, String idioma, String cat, String material) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.descripcion = descripcion;
		this.idioma = idioma;
		this.cat = cat;
		this.material = material;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Curso [getId()=" + getId() + ", getTitulo()=" + getTitulo() + ", getSubtitulo()=" + getSubtitulo()
				+ ", getDescripcion()=" + getDescripcion() + ", getIdioma()=" + getIdioma() + ", getCat()=" + getCat()
				+ ", getMaterial()=" + getMaterial() + "]";
	}
}