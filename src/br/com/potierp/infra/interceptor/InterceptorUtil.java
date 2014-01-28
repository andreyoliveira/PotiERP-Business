package br.com.potierp.infra.interceptor;

import javax.interceptor.InvocationContext;

import br.com.potierp.infra.log.TraceInfo;

/**
 * Classe Utilit�ria, com m�todos utilizados por Interceptors do aplicativo.
 *
 * @author 
 * 16/07/2010
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public final class InterceptorUtil {

	/**
	 * Construtor default, privado.
	 */
	private InterceptorUtil(){}

	/**
	 * Percorre os par�metros no <code>InvocationContext</code> em busca do
	 * TraceInfo.
	 *
	 * @param invocation
	 * @return
	 */
	public static TraceInfo getTraceInfo(final InvocationContext invocation) {
		TraceInfo traceInfo = null;
		Object[] params = invocation.getParameters();
		if (params != null && params.length > 0) {
			for (Object param : params) {
				if (param instanceof TraceInfo) {
					traceInfo = (TraceInfo) param;
					break;
				}
			}
		}
		return traceInfo;
	}

}
