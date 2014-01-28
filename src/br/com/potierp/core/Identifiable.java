package br.com.potierp.core;

/**
 * Interface que indica que o objeto � identific�vel por atrav�s de um id.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $ 
 */
public interface Identifiable {
	
	/**
	 * Obtem a identifica��o do Objeto.
	 * @return
	 */
	Object getIdentity();
}