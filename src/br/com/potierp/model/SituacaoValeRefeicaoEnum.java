package br.com.potierp.model;

public enum SituacaoValeRefeicaoEnum {
	
	CALCULADO("Calculado"),
	
	GRAVADO("Gravado");
	
	private String situacao;
	
	private SituacaoValeRefeicaoEnum(final String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
}