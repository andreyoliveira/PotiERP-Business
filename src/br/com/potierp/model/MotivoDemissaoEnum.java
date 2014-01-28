package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum MotivoDemissaoEnum {
	
	DISPENSA_SEM_JUSTA_CAUSA("Dispensa sem justa causa"),
	
	DISPENSA_POR_JUSTA_CAUSA("Dispensa por justa causa"),
	
	PEDIDO_DEMISSAO_EMPREGADO("Pedido de demissão por parte do empregado"),
	
	ABANDONO_EMPREGO("Abandono de emprego"),
	
	TERMINO_EXPERIENCIA("Término da experiência"),
	
	ANTECIPACAO_TERMINO_EXPERIENCIA_EMPREGADOR("Antecipação do término experiência pelo empregador"),
	
	ANTECIPACAO_TERMINO_EXPERIENCIA_EMPREGADO("Antecipação do término experiência pelo empregado");
	
	private String motivo;
	
	private MotivoDemissaoEnum(final String motivo) {
		this.motivo = motivo;
	}
	
	public String getMotivo() {
		return motivo;
	}
}
