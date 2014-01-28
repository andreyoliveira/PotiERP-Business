package br.com.potierp.model;

public enum EscolaridadeEnum {
	
	ANALFABETO("Analfabeto"),
	
	PRIMEIROGRAU_COMPLETO("1º. Grau Completo"),
	
	PRIMEIROGRAU_INCOMPLETO("1º. Grau Incompleto"),
	
	SEGUNDOGRAU_COMPLETO("2º. Grau Completo"),
	
	SEGUNDOGRAU_INCOMPLETO("2º. Grau Incompleto"),
	
	SUPERIOR_COMPLETO("Superior Completo"),
		
	SUPERIOR_INCOMPLETO("Superior Incompleto"),
	
	MESTRADO_COMPLETO("Mestrado Completo"),
	
	MESTRADO_INCOMPLETO("Mestrado Incompleto"),
	
	DOUTORADO_COMPLETO("Doutorado Completo"),
	
	DOUTORADO_INCOMPLETO("Doutorado Incompleto"),
	
	QUARTASERIE_INCOMPLETA("Até a 4ª. Série Incompleta do 1º. Grau"),
	
	QUARTASERIE_COMPLETA("Com a 4ª. Série Completa do 1º. Grau"),
	
	QUINTA_OITAVA_INCOMPLETA("Da 5ª. á 8ª. Série Incompleta do 1º. Grau");
	
	private String descricao;
	
	private EscolaridadeEnum(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
}