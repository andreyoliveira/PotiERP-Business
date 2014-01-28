package br.com.potierp.model;

public enum TipoContratoEnum {
	
	DETERMINADO("Determinado"),
	
	INDETERMINADO("Indeterminado");
	
	private String tipoContrato;
	
	private TipoContratoEnum(final String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
}