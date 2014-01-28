package br.com.potierp.model;

/**
 * @author Doug
 *
 */
public enum TipoFeriadoEnum {
	
	NACIONAL("Nacional"),
	
	ESTADUAL("Estadual"),
	
	MUNICIPAL("Municipal");
	
	private String tipoFeriado;
	
	private TipoFeriadoEnum(final String tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}

	public String getTipoFeriado() {
		return tipoFeriado;
	}

}
