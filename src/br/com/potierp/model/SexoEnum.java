package br.com.potierp.model;

public enum SexoEnum {
	
	MASCULINO("M"),
	
	FEMININO("F");
	
	private String sexo;
	
	private SexoEnum(final String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(final String sexo) {
		this.sexo = sexo;
	}
}