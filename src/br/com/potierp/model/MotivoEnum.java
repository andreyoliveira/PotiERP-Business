package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum MotivoEnum {
	
	DISSIDIO("Dissídio"),
	
	PROMOCAO("Promoção"),
	
	MERITO("Mérito");
	
	private String motivo;
	
	private MotivoEnum(final String motivo) {
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}
	
	
	
}
