package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SituacaoFgts implements Serializable{
	
	private static final long serialVersionUID = -4055359113103310774L;

	@Column(name = "optanteFgts")
	private Boolean optante;
	
	@Column(name = "dataOpcaoFgts")
	private Date dataOpcao;
	
	@Column(name = "dataRetratacaoFgts")
	private Date dataRetratacao;
	
	@Column(name = "bancoDepositanteFgts")
	private String bancoDepositante;

	public Boolean getOptante() {
		return optante;
	}

	public void setOptante(final Boolean optante) {
		this.optante = optante;
	}

	public Date getDataOpcao() {
		return dataOpcao;
	}

	public void setDataOpcao(final Date dataOpcao) {
		this.dataOpcao = dataOpcao;
	}

	public Date getDataRetratacao() {
		return dataRetratacao;
	}

	public void setDataRetratacao(final Date dataRetratacao) {
		this.dataRetratacao = dataRetratacao;
	}

	public String getBancoDepositante() {
		return bancoDepositante;
	}

	public void setBancoDepositante(final String bancoDepositante) {
		this.bancoDepositante = bancoDepositante;
	}

	@Override
	public String toString() {
		return "SituacaoFgts [optante=" + optante + ", dataOpcao=" + dataOpcao
				+ ", dataRetratacao=" + dataRetratacao + ", bancoDepositante="
				+ bancoDepositante + "]";
	}
}