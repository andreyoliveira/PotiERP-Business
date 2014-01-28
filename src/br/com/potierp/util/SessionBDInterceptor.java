package br.com.potierp.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import br.com.potierp.infra.log.TraceInfo;

/**
 * Define operacoes comum ao Interceptador que loga o traceinfo na session.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public abstract class SessionBDInterceptor {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(SessionBDInterceptor.class);

	/**
	 * Intercepta a chamada aos metodos do Bean.
	 * 
	 * @param invocation
	 * @return
	 * @throws Exception
	 * 			Lan�a a exce��o caso n�o exista um <code>TraceInfo</code>.
	 */
	@AroundInvoke
	public Object aroundInvoke(final InvocationContext invocation)
			throws Exception {
		TraceInfo trace = null;
		try {
			trace = getTraceInfo(invocation);
			if (trace == null) {
				log.error("TraceInfo nao informado! Nao eh possivel configurar Session no BD!");
				log.error("Metodo: "+invocation.getMethod().getName());
				return invocation.proceed();
			} else {
				log.info("TraceInfo logado ["+trace+"]");
			}
			configuraSessionBD(trace);
			return invocation.proceed();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @return Devolve o TraceInfo.
	 */
	protected abstract TraceInfo getTraceInfo(InvocationContext invocation);

	/**
	 * Chama o respons�vel por configurar a vari�vel de Session do Banco (Log.).
	 * 
	 * @param traceInfo
	 *            Informa��es do usu�rio.
	 */
	private void configuraSessionBD(final TraceInfo traceInfo) {
		ConfigVarSessionDB sessionDB = new ConfigVarSessionDB(getEntityManager());
		sessionDB.execute(traceInfo);
	}

	/**
	 * Obtem o EntityManager.
	 * @return
	 */
	protected abstract EntityManager getEntityManager();
}