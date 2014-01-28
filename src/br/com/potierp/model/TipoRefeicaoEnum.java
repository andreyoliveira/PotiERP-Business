package br.com.potierp.model;

/**
 * @author Doug
 *
 */
public enum TipoRefeicaoEnum {
 
	ALIMENTACAO("Alimentação"),
	
	INTERVALO_MATINAL("Intervalo Matinal"),
	
	INTERVALO_VESPERTINO("Intervalo Vespertino"),
	
	INTERVALO_NOTURNO("Intervalo Noturno");
	
	private String tipoRefeicao;
	
	private TipoRefeicaoEnum(final String tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
	
	/**
	 * @return the tipoRefeicao
	 */
	public String getTipoRefeicao() {
		return tipoRefeicao;
	}
}
