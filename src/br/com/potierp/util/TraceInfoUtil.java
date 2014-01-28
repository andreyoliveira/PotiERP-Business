package br.com.potierp.util;

import br.com.potierp.infra.log.TraceInfo;


/**
 *  Classe utilitaria para o gerenciamento do TraceInfo.
 * @author
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public class TraceInfoUtil {
	
	/**
	 * ThreadLocal.
	 */
	private static ThreadLocal<TraceInfo> currentTraceInfo = new ThreadLocal<TraceInfo>();
	
	/**
	 * Construtor privado.
	 */
	private TraceInfoUtil(){}
	
	
	/**
	 * @param traceInfo
	 */
	public static void setCurrentTraceInfo(final TraceInfo traceInfo){
		currentTraceInfo.set(traceInfo);
	}
	
	/**
	 * @return
	 */
	public static TraceInfo getCurrentTraceInfo(){
		return currentTraceInfo.get();
	}
	
	/**
	 * remove o trace.
	 */
	public static void clean() {
		currentTraceInfo.remove();
	}
}
