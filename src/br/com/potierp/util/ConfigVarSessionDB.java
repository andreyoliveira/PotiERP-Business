package br.com.potierp.util;

import javax.persistence.EntityManager;

import br.com.potierp.infra.log.TraceInfo;

/**
 * Seta var�avel na session do Banco de Dados.
 * 
 * @author eder.magalhaes
 *         <p>
 *         $LastChangedBy: eder.magalhaes@UMESP.EDU.DTI $
 *         <p>
 *         $LastChangedDate: 2008-10-29 13:06:21 -0300 (qua, 29 out 2008) $
 */
public final class ConfigVarSessionDB {

	/**
	 * Entity Manager.
	 */
	private EntityManager em;

	/**
	 * Construtor recebe <code>EntityManager</code>.
	 * @param em
	 */
	public ConfigVarSessionDB(final EntityManager em) {
		this.em = em;
	}

	/**
	 * Coloca a var na Session BD.
	 * @param traceInfo
	 */
	public void execute(final TraceInfo traceInfo) {
		//String valor = traceInfo.toString();
		/*if (valor.length() > 64)
			valor = valor.substring(0, 64);
		Query query = em.createNativeQuery("select F_SET_CLIENT_INFO('"+valor+"') from dual");
		query.getResultList();*/
	}

}
