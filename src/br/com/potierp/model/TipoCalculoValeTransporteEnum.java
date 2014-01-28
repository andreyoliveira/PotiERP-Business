package br.com.potierp.model;

/**
 * @author renan
 *
 */
public enum TipoCalculoValeTransporteEnum {
	
	PERIODO("Periodo"),
	
	FUNCIONARIO("Funcionário");
	
	private String tipoCalculo;
	
	private TipoCalculoValeTransporteEnum(final String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}
}
