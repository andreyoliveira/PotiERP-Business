package br.com.potierp.infra.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.log.TraceInfo;
import br.com.potierp.util.EntityManagerUtil;
import br.com.potierp.util.SessionBDInterceptor;
import br.com.potierp.util.TraceInfoUtil;

/**
 * Interceptor de Facade do Logos, que configura a variável de Session no Banco de Dados.
 *
 * @author 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class SessionBDInterceptorPoti extends SessionBDInterceptor {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(SessionBDInterceptorPoti.class);

	/**
	 * Executando.
	 */
	private static final String EXECUTANDO = "executando ";

	/**
	 * [Bean].
	 */
	private static final String BEAN = " [Bean] ";

	/**
	 * Nova linha.
	 */
	private static final String NOVA_LINHA = "\n";

	/**
	 * EntityManager.
	 */
	@PersistenceContext(unitName="unitErpBusiness")
	private EntityManager em;


	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.util.interceptor.SessionBDInterceptor#aroundInvoke(javax.interceptor.InvocationContext)
	 */
	@Override
	@AroundInvoke
	public Object aroundInvoke(final InvocationContext invocation) throws Exception {
		TraceInfo trace = InterceptorUtil.getTraceInfo(invocation);
		String traceInfoLogMessage = getTraceInfoLogMessage(invocation, trace);

		try {
			if(trace != null) {
				TraceInfoUtil.setCurrentTraceInfo(trace);
				EntityManagerUtil.setCurrentEntityManager(em);
			}
			return super.aroundInvoke(invocation);
		} catch (PotiErpMensagensException e) {
			logOperacao(traceInfoLogMessage);
			throw e;
		} catch (PotiErpException el) {
			logOperacao(traceInfoLogMessage);
			throw el;
		} catch (Exception ex) {
			logOperacao(traceInfoLogMessage);
			throw new PotiErpException(ex.getMessage(), ex);
		}finally{
			TraceInfoUtil.clean();
			EntityManagerUtil.clean();
		}
	}

	/**
	 * @param traceInfoLogMessage
	 */
	private void logOperacao(final String traceInfoLogMessage) {
		log.error("ERRO DURANTE A EXECU��O DA OPERA��O");
		log.error(traceInfoLogMessage);
	}

	/**
	 * Obtem a mensagem de log do TraceInfo.
	 * @param invocation
	 * @param trace
	 * @return
	 */
	private String getTraceInfoLogMessage(final InvocationContext invocation, final TraceInfo trace) {
		StringBuilder info = new StringBuilder();
		if(trace == null){
			info.append("TraceInfo nao informado");
		}else {
			info.append(NOVA_LINHA);
			info.append("USUARIO        => " + trace.getUsuario().getUsername()).append(NOVA_LINHA);
			info.append("IP             => " + trace.getIp()).append(NOVA_LINHA);
			info.append("BROWSER        => " + trace.getBrowser()).append(NOVA_LINHA);
			info.append("TIPO DE ACESSO => " + trace.getSiglaPerfil()).append(NOVA_LINHA);
		}

		info.append(EXECUTANDO);
		info.append(invocation.getMethod().getName());
		info.append(BEAN);
		info.append(invocation.getTarget().getClass().getName());
		return info.toString();
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.util.interceptor.SessionBDInterceptor#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.util.interceptor.SessionBDInterceptor#getTraceInfo(javax.interceptor.InvocationContext)
	 */
	@Override
	protected TraceInfo getTraceInfo(final InvocationContext invocation) {
		return InterceptorUtil.getTraceInfo(invocation);
	}
}
