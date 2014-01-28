package br.com.potierp.model;

public enum TipoVerbaEnum {
	
	CREDITO("Crédito"),
	DEBITO("Débito");
	
	private String nome;
	
	private TipoVerbaEnum(final String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
}
