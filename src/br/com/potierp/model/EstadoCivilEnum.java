package br.com.potierp.model;

public enum EstadoCivilEnum{
	
	CASADO("Casado"),
	
	DIVORCIADO("Divorciado"),
	
	MARITAL("Marital"),
	
	SEPARADO("Separado"),
	
	SOLTEIRO("Solteiro"),
		
	VIUVO("Viúvo");
	
	private String estadoCivil;
	
	private EstadoCivilEnum(final String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(final String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}