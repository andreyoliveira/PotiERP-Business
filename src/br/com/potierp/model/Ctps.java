package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Ctps implements Serializable{

	private static final long serialVersionUID = -6749904674804746682L;
	
	private Long ctps;
	
	@Column(name = "serieCtps")
	private Long serie;
	
	@Column(name = "ufCtps")
	private String uf;
	
	@Column(name = "dataEmissaoCtps")
	private Date dataEmissao;
	
	public Long getCtps() {
		if(Long.valueOf(0).equals(ctps)){
			this.ctps = null;
		}
		return ctps;
	}

	public void setCtps(final Long ctps) {
		this.ctps = ctps;
	}

	public Long getSerie() {
		if(Long.valueOf(0).equals(serie)){
			this.serie = null;
		}
		return serie;
	}

	public void setSerie(final Long serie) {
		this.serie = serie;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(final String uf) {
		this.uf = uf;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(final Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Override
	public String toString() {
		return "Ctps [ctps=" + ctps + ", serie=" + serie + ", uf=" + uf
				+ ", dataEmissao=" + dataEmissao + "]";
	}
}