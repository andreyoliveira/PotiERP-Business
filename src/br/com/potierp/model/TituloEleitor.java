package br.com.potierp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TituloEleitor implements Serializable{
	
	private static final long serialVersionUID = 2998651117447948235L;

	@Column(name = "tituloEleitor")
	private String titulo;
	
	@Column(name = "zonaTituloEleitor")
	private String zona;
	
	@Column(name = "secaoTituloEleitor")
	private String secao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(final String zona) {
		this.zona = zona;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(final String secao) {
		this.secao = secao;
	}

	@Override
	public String toString() {
		return "TituloEleitor [titulo=" + titulo + ", zona=" + zona
				+ ", secao=" + secao + "]";
	}
}