package br.com.potierp.infra.bd;

/**
 * Enum que classifica os sql's (nativos) utilizados pelo Sistema.
 * 
 * @author 
 * 09/02/2010 11:38:27
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public enum SelectsEnum {
	
	/**
	 * 
	 */
	QUERY("");
	
	/**
	 * conteudo do sql.
	 */
	private String sql;

	/**
	 * Construtor recebe codigo sql.
	 * @param sql
	 */
	private SelectsEnum(final String sql) {
		this.sql = sql;
	}

	/**
	 * @return sql.
	 */
	public String getSql() {
		return this.sql;
	}

}
