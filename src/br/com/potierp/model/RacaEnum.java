/**
 * 
 */
package br.com.potierp.model;

/**
 * @author felipe
 *
 */
public enum RacaEnum {
	
	BRANCO("Branco"),
	
	NEGRO("Negro"),
	
	AMARELO("Amarelo"),
	
	PARDO("Pardo"),
	
	INDIGENA("Indígena");
	
	private String descricao;
	
	private RacaEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
