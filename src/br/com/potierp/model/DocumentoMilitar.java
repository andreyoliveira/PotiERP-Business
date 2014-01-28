package br.com.potierp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DocumentoMilitar implements Serializable{
	
	private static final long serialVersionUID = 1715352387255642772L;

	@Column(name = "documentoMilitar")
	private String documento;
	
	@Column(name = "numeroDocumentoMilitar")
	private Long numero;
	
	@Column(name = "categoriaDocumentoMilitar")
	private String categoria;

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(final String documento) {
		this.documento = documento;
	}

	public Long getNumero() {
		if(Long.valueOf(0).equals(numero)){
			this.numero = null;
		}
		return numero;
	}

	public void setNumero(final Long numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(final String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "DocumentoMilitar [documento=" + documento + ", numero="
				+ numero + ", categoria=" + categoria + "]";
	}
}