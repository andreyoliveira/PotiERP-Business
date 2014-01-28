package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.Collections;
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
@Stateless(mappedName = "FichaRegistroService", name="FichaRegistroService")
@Local(FichaRegistroService.class)
@Interceptors(DAOInterceptor.class)
public class FichaRegistroServiceBean implements FichaRegistroService {
	
	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.FichaRegistroService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Ficha de Registro"}, e);
		}
	}

	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			Collections.sort(funcionario.getLocaisTrabalho());
			listFunc.add(funcionario);
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("FichaRegistro.jasper");
			jasperReportUtilHelper.setParametros(new HashMap<String, Object>());
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}
}