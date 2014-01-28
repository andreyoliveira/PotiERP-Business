package br.com.potierp.model;

public enum TipoParametroEnum {
	
	NUMERICO("Numérico"),
	
	TEXTO("Texto"),
	
	DATA("Data"),
	
	HORARIO("Horário");	
	
	private String tipo;
	
	private TipoParametroEnum(final String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}