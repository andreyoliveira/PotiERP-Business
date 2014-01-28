package br.com.potierp.util;

import br.com.potierp.infra.log.TraceInfo;

/**
 * Encapsula uma <b>ThreadLocal</b> para <b><i>TraceInfo</i></b>.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public final class TraceUtil {

	/**
	 * ThreadLocal.
	 */
	private static ThreadLocal<TraceInfo> currentUser = new ThreadLocal<TraceInfo>();

	/**
	 * Construtor privado.
	 */
	private TraceUtil(){
	}

	/**
	 * @param trace
	 */
	public static void setCurrentTraceInfo(final TraceInfo trace) {
		currentUser.set(trace);
	}

	/**
	 * @return
	 */
	public static TraceInfo getCurrentTraceInfo() {
          return currentUser.get();
	}

	/**
	 * Libera o Trace associado com a Thread.
	 */
	public static void clean() {
		currentUser.remove();
	}

}