package br.com.potierp.infra.exception;

import java.text.MessageFormat;

import javax.ejb.ApplicationException;

import br.com.potierp.infra.msg.MensagensExceptionEnum;

/**
 * Exception referente a camada do DAO.
 *
 * @author
 * 22/02/2010
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
@ApplicationException(rollback = true)
public class DaoException extends Exception {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -5611835095148495327L;

	/**
	 * Construtor default.
	 */
	public DaoException(){
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param cause
	 */
	public DaoException(final String message,final Throwable cause){
		super(message,cause);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param cause
	 */
	public DaoException(final MensagensExceptionEnum message,
			final Throwable cause) {
		super(message.getMsg(),cause);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param params
	 */
	public DaoException(final MensagensExceptionEnum message,
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
	public DaoException(final MensagensExceptionEnum message,
			final Object[] params, final Throwable cause) {
		super(MessageFormat.format(message.getMsg(), params),cause);
	}

	/**
	 * Construtor.
	 * @param message
	 */
	public DaoException(final MensagensExceptionEnum message){
		super(message.getMsg());
	}

	/**
	 * Construtor.
	 * @param message
	 */
	public DaoException(final String message){
		super(message);
	}

	/**
	 * Construtor.
	 * @param cause
	 */
	public DaoException(final Throwable cause){
		super(cause);
	}
}