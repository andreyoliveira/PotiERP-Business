package br.com.potierp.model;

/**
 * @author Doug
 *
 */
public enum MesEnum {
	
	JANEIRO("Janeiro", 1),
	
	FEVEREIRO("Fevereiro", 2),
	
	MARCO("Março", 3),
	
	ABRIL("Abril", 4),
	
	MAIO("Maio", 5),
	
	JUNHO("Junho", 6),
	
	JULHO("Julho", 7),
	
	AGOSTO("Agosto", 8),
	
	SETEMBRO("Setembro", 9),
	
	OUTUBRO("Outubro", 10),
	
	NOVEMBRO("Novembro", 11),
	
	DEZEMBRO("Dezembro", 12);
	
	private String mes;
	
	private Integer codigo;
	
	private MesEnum(final String mes, final Integer codigo) {
		this.mes = mes;
		this.codigo = codigo;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
}
