package br.com.potierp.model;

public enum SituacaoEnum {
	
	ATIVO("Ativo"),
	
	INATIVO("Inativo");
	
	private String situacao;
	
	private SituacaoEnum(final String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}