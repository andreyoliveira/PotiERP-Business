package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Pis implements Serializable{
	
	private static final long serialVersionUID = 2571991935123583703L;

	private String pis;
	
	@Column(name = "dataEmissaoPis")
	private Date dataEmissao;

	public String getPis() {
		return pis;
	}

	public void setPis(final String pis) {
		this.pis = pis;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(final Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Override
	public String toString() {
		return "Pis [pis=" + pis + ", dataEmissao=" + dataEmissao + "]";
	}
}
