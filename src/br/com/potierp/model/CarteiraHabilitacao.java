package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CarteiraHabilitacao implements Serializable{
	
	private static final long serialVersionUID = -7802636396588544233L;

	private String habilitacao;
	
	@Column(name = "categoriaHabilitacao")
	private String categoria;
	
	@Column(name = "dataVencimentoHabilitacao")
	private Date dataVencimento;

	public String getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(final String habilitacao) {
		this.habilitacao = habilitacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(final String categoria) {
		this.categoria = categoria;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(final Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		return "CarteiraHabilitacao [habilitacao=" + habilitacao
				+ ", categoria=" + categoria + ", dataVencimento="
				+ dataVencimento + "]";
	}
}