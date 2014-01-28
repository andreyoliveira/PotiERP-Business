package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum TipoAvisoPrevioEnum {
	
	INDENIZADO("Indenizado"),
	
	TRABALHADO("Trabalhado");
	
	private String tipo;
	
	private TipoAvisoPrevioEnum(final String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
