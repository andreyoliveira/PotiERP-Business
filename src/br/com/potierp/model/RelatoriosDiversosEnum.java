package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum RelatoriosDiversosEnum {
	
	PROTOCOLO_ENTREGA_ATESTADO_MEDICO("Protocolo de Entrega de Atestado Médico"),
	
	PROTOCOLO_ENTREGA_CTPS("Protocolo de Entrega da CTPS"),
	
	AUTORIZACAO_DESCONTO_EQUIPAMENTO("Autorização de Desconto de Equipamento");
	
	private String relatorio;
	
	private RelatoriosDiversosEnum(final String relatorio) {
		this.relatorio = relatorio;
	}
	
	public String getRelatorio() {
		return this.relatorio;
	}
}
