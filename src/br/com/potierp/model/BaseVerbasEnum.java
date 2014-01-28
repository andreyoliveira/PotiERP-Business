package br.com.potierp.model;

public enum BaseVerbasEnum {
 
	DIA("Dia"),
	
	HORARIO("Horário"),
	
	HORARIONOTURNO("Horário Noturno"),
	
	VALOR("Valor");
	
	private String base;
	
	private BaseVerbasEnum(final String base) {
		this.base = base;
	}

	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}
}