package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rg implements Serializable{
	
	private static final long serialVersionUID = 4067187578920508533L;

	private String rg;
	
	@Column(name="dataEmissaoRg")
	private Date dataEmissao;
	
	@Column(name="orgaoEmissorRg")
	private String orgaoEmissor;
		
	@Column(name = "ufRg")
	private String uf;

	public String getRg() {
		return rg;
	}

	public void setRg(final String rg) {
		this.rg = rg;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(final Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(final String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(final String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Rg [rg=" + rg + ", dataEmissao=" + dataEmissao
				+ ", orgaoEmissor=" + orgaoEmissor + ", uf=" + uf + "]";
	}
}