package br.com.potierp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConselhoRegional implements Serializable{

	private static final long serialVersionUID = 842336392819058927L;
	
	@Column(name = "nomeConselhoRegional")
	private String nome;
	
	@Column(name = "siglaConselhoRegional")
	private String sigla;
	
	@Column(name = "numeroConselhoRegional")
	private Long numero;
	
	@Column(name = "regiaoConselhoRegional")
	private String regiao;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
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

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(final String regiao) {
		this.regiao = regiao;
	}

	@Override
	public String toString() {
		return "ConselhoRegional [nome=" + nome + ", sigla=" + sigla
				+ ", numero=" + numero + ", regiao=" + regiao + "]";
	}
}