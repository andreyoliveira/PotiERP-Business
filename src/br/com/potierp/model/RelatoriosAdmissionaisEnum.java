package br.com.potierp.model;

/**
 * @author Doug
 */
public enum RelatoriosAdmissionaisEnum {

	AUTORIZACAO_DESCONTO_REFEICAO("Autorização para Desconto de Refeição"),
	
	//AUTORIZACAO_DESCONTO_REFEICAO_CESARI("Autorização para Desconto de Refeição Cesari"),
	
	AUTORIZACAO_DESCONTO_UNIFORMES("Autorização para Desconto de Uniformes"),
	
	AUTORIZACAO_DESCONTO_CONTRIBUICAO_ASSISTENCIAL_FAMILIAR("Autorização de Desc. Contrib. e Assistência Familiar"),
	
	CONTRATO_EXPERIENCIA("Contrato de experiência"),
	
	DECLARACAO_PARA_FINS_IR("Declaração para fins de imposto de renda"),
	
	DESVIO_DE_FUNCAO("Desvio de Função"),
	
	FICHA_REGISTRO("Ficha de registro"),
	
	SALARIO_FAMILIA("Salário família"),
	
	SOLICITACAO_VALE_TRANSPORTE("Solicitação de vale transporte"),
	
	TERMO_RESPONSABILIDADE_CRACHA("Termo de Responsabilidade para Crachá");
	
	private String relatorio;
	
	private RelatoriosAdmissionaisEnum(final String relatorio) {
		this.relatorio = relatorio;
	}

	public String getRelatorio() {
		return this.relatorio;
	}
}
