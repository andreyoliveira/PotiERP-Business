package br.com.potierp.auditoria;

/**
 * Define o tipo dos objetos auditáveis.
 * 
 * @author 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public interface Auditavel {
	
	/**
	 * 
	 * @return
	 */
	String auditoria();
	
	/**
	 * 
	 * @return
	 */
	String getTableName();

}
