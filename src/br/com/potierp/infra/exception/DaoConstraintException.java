package br.com.potierp.infra.exception;

import java.text.MessageFormat;

import br.com.potierp.infra.msg.MensagensExceptionEnum;

/**
 * @author
 * 01/09/2010
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class DaoConstraintException extends DaoException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4091871414049407844L;

	/**
	 * Construtor default.
	 */
	public DaoConstraintException(){
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param cause
	 */
	public DaoConstraintException(final String message,final Throwable cause){
		super(message,cause);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param cause
	 */
	public DaoConstraintException(final MensagensExceptionEnum message,
			final Throwable cause) {
		super(message.getMsg(),cause);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param params
	 */
	public DaoConstraintException(final MensagensExceptionEnum message,
			final Object[] params) {
		super(MessageFormat.format(message.getMsg(), params));
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param params
	 * @param cause
	 */
	public DaoConstraintException(final MensagensExceptionEnum message,
			final Object[] params, final Throwable cause) {
		super(MessageFormat.format(message.getMsg(), params),cause);
	}

	/**
	 * Construtor.
	 * @param message
	 */
	public DaoConstraintException(final MensagensExceptionEnum message){
		super(message.getMsg());
	}

	/**
	 * Construtor.
	 * @param message
	 */
	public DaoConstraintException(final String message){
		super(message);
	}

	/**
	 * Construtor.
	 * @param cause
	 */
	public DaoConstraintException(final Throwable cause){
		super(cause);
	}
}
