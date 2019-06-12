package br.com.padaria.objetos;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	private int idCategoria;
	private String tipoCategoria;
	
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
	
}
