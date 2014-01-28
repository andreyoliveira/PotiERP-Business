package br.com.potierp.model;

import java.io.Serializable;

/**
 * Define contrato para classes que representam entidades no banco.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public interface BaseEntity extends Serializable {

	/**
	 * @return O Id da entidade.
	 */
	Long getId();

	/**
	 * Indica se a entidade eh nova, ou seja, ainda nao persistida.
	 * @return
	 */
	boolean isNew();
}
