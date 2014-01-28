package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * @author Doug
 *
 */
@Stateless(mappedName = "SolicitacaoValeTransporteService", name="SolicitacaoValeTransporteService")
@Local(SolicitacaoValeTransporteService.class)
@Interceptors(DAOInterceptor.class)
public class SolicitacaoValeTransporteServiceBean implements
		SolicitacaoValeTransporteService {

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.SolicitacaoValeTransporteService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Solicitação de Vale Transporte"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("SolicitacaoValeTransporte.jasper");
			jasperReportUtilHelper.setParametros(new HashMap<String, Object>());
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}

}
