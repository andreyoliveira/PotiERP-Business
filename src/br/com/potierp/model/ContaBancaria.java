package br.com.potierp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ContaBancaria implements Serializable{

	private static final long serialVersionUID = -1584120817960328645L;
	
	private String banco;
	
	private String agencia;
	
	private String conta;
	
	@Enumerated(EnumType.STRING)
	private TipoContaBancariaEnum tipoContaBancaria;

	public String getBanco() {
		return banco;
	}

	public void setBanco(final String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(final String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(final String conta) {
		this.conta = conta;
	}

	public TipoContaBancariaEnum getTipoContaBancaria() {
		return tipoContaBancaria;
	}

	public void setTipoContaBancaria(final TipoContaBancariaEnum tipoContaBancaria) {
		this.tipoContaBancaria = tipoContaBancaria;
	}

	@Override
	public String toString() {
		return "ContaBancaria [banco=" + banco + ", agencia=" + agencia
				+ ", conta=" + conta + ", tipoContaBancaria="
				+ tipoContaBancaria + "]";
	}
}