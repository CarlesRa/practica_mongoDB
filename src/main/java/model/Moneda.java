package model;

import java.util.List;

import org.bson.Document;

public class Moneda {
	private String _id;
	private String pais;
	private String anyo;
	private String descripcion;
	private List<Document> valores;
	
	public Moneda(String _id, String pais, String anyo, String descripcion, List<Document> valores) {
		this._id = _id;
		this.pais = pais;
		this.anyo = anyo;
		this.descripcion = descripcion;
		this.valores = valores;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Document> getValores() {
		return valores;
	}

	public void setValores(List<Document> valores) {
		this.valores = valores;
	}

	
}
