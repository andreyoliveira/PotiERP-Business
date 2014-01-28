package br.com.potierp.model;

/**
 * @author renan
 *
 */
public enum TipoCalculoValeRefeicaoEnum {
	
	PERIODO("Periodo"),
	
	FUNCIONARIO("Funcionário");
	
	private String tipoCalculo;
	
	private TipoCalculoValeRefeicaoEnum(final String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}
}
