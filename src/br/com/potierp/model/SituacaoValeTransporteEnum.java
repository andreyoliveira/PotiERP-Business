package br.com.potierp.model;

public enum SituacaoValeTransporteEnum {
	
	CALCULADO("Calculado"),
	
	GRAVADO("Gravado");
	
	private String situacao;
	
	private SituacaoValeTransporteEnum(final String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
}