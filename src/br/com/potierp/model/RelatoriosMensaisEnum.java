package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum RelatoriosMensaisEnum {
	
	CARTAO_PONTO("Cartão de Ponto");
	
	private String relatorio;
	
	private RelatoriosMensaisEnum(final String relatorio) {
		this.relatorio = relatorio;
	}
	
	public String getRelatorio() {
		return this.relatorio;
	}
}
