package br.com.potierp.model;

public enum VerbasSobreEnum {
 
	SALARIOFUNCIONARIO("Salário do Funcionário"),
	
	SALARIOMINIMO("Salário Mínimo"),
	
	VALORSIMPLES("Valor Simples");
	
	private String sobre;
	
	private VerbasSobreEnum(final String sobre) {
		this.sobre = sobre;
	}

	/**
	 * @return the sobre
	 */
	public String getSobre() {
		return sobre;
	}
}