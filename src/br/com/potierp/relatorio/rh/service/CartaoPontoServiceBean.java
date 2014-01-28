package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionario;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Andrey Oliveira
 */
@Stateless(mappedName = "CartaoPontoService", name="CartaoPontoService")
@Local(CartaoPontoService.class)
@Interceptors(DAOInterceptor.class)
public class CartaoPontoServiceBean implements CartaoPontoService {

	@Override
	public byte[] getRelatorio(List<Funcionario> listFuncionario, String mes, String ano)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario, mes, ano);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(
					MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO,
					new Object[] { "Cartão de Ponto" }, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(final List<Funcionario> listFuncionario,
										final String mes, final String ano) {
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("mes", mes);
			parametros.put("ano", ano);
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("CartaoPonto.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}

}
