package br.com.potierp.model;

public enum TipoContaBancariaEnum {
	
	SALARIO("Conta Salário"),
	
	CORRENTE("Conta Corrente"),
	
	POUPANCA("Conta Poupança");
	
	private String tipoConta;
	
	private TipoContaBancariaEnum(final String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
}