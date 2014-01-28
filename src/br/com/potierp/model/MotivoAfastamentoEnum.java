package br.com.potierp.model;

/**
 * @author Andrey Oliveira
 */
public enum MotivoAfastamentoEnum {

	ACIDENTE_TRABALHO("Acidente de Trabalho"),
	
	AUXILIO_DOENCA("Auxílio Doença"),
	
	MATERNIDADE("Maternidade"),
	
	PATERNIDADE("Paternidade"),
	
	LICENCA_REMUNERADA("Licença Remunerada"),
	
	LICENCA_NAO_REMUNERADA("Licença Não Remunerada");
	
	private String motivo;
	
	private MotivoAfastamentoEnum(final String motivo) {
		this.motivo = motivo;
	}
	
	public String getMotivo() {
		return motivo;
	}
}
