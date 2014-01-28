package br.com.potierp.model;

public enum CorEnum {
	
	AMARELO("Amarelo"),
	
	AZUL("Azul"),
	
	CINZA("Cinza"),
	
	PRETO("Preto"),
	
	VERDE("Verde"),
		
	VERMELHO("Vermelho");
	
	private String nome;
	
	private CorEnum(final String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}
}