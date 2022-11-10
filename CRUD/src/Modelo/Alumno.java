package Modelo;

public class Alumno {
	int id;
	String Nombre;
	String Grupo;
	String Carrera;
	String Municipio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getGrupo() {
		return Grupo;
	}
	public void setGrupo(String grupo) {
		Grupo = grupo;
	}
	public String getCarrera() {
		return Carrera;
	}
	public void setCarrera(String carrera) {
		Carrera = carrera;
	}
	public String getMunicipio() {
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
}

