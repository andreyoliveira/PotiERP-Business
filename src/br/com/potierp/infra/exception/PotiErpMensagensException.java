package br.com.potierp.infra.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception que guarda uma lista de mensagens.
 *
 * @author 
 * 08/06/2010
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class PotiErpMensagensException extends PotiErpException {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -1087654748895277617L;

	/**
	 * Mensagens com os par�metros.
	 */
	private final Map<String,Object[]> mensagens;

	/**
	 * Recebe um map com as mensagens.
	 * @param msg
	 */
	public PotiErpMensagensException(final Map<String,Object[]> msg) {
		super();
		this.mensagens = msg;
	}

	/**
	 * Recebe uma mensagem.
	 * @param msg
	 */
	public PotiErpMensagensException(final String msg) {
		super();
		this.mensagens = new HashMap<String, Object[]>();
		mensagens.put(msg, null);
	}
	
	/**
	 * Recebe uma mensagem e seus parametros.
	 * @param msg
	 * @param params
	 */
	public PotiErpMensagensException(final String msg, final Object[] params) {
		super();
		this.mensagens = new HashMap<String, Object[]>();
		mensagens.put(msg, params);
	}
	
	/**
	 * @return the mensagens
	 */
	public Map<String,Object[]> getMensagens() {
		return mensagens;
	}
}