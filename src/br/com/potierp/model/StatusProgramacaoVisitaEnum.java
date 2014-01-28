package br.com.potierp.model;

/**
 * @author Ralph
 */
public enum StatusProgramacaoVisitaEnum {
	A_VISITAR("A Visitar"),
	
	VISITADO("Visistado"),
	
	NAO_VISITADO("Não visitado");
	
	private String status;
	
	StatusProgramacaoVisitaEnum(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
