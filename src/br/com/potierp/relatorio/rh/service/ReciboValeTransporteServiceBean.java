package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.business.rh.helper.FuncionarioValeTransporteHelper;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "ReciboValeTransporteService", name="ReciboValeTransporteService")
@Local(ReciboValeTransporteService.class)
@Interceptors(DAOInterceptor.class)
public class ReciboValeTransporteServiceBean implements ReciboValeTransporteService{
	
	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.ReciboValeTransporteService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(
			final List<CalculoValeTransporte> listCalculoValeTransporte)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listCalculoValeTransporte);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Recibo de vale transporte"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<CalculoValeTransporte> listCalculoValeTransporte) throws Exception{
		
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(CalculoValeTransporte calculo : listCalculoValeTransporte){
			
			int quantidadeFuncionario = 0;
			int totalFuncionarios = calculo.getFuncionariosValeTransporteHelper().size();
			int quantidadeFuncionariosHelper = 0;
			List<FuncionarioValeTransporteHelper> listFuncionarioValeTransporteHelper = new ArrayList<FuncionarioValeTransporteHelper>();
			for (FuncionarioValeTransporteHelper funcionarioValeTransporteHelper : calculo.getFuncionariosValeTransporteHelper()) {
				listFuncionarioValeTransporteHelper.add(funcionarioValeTransporteHelper);
				listFuncionarioValeTransporteHelper.add(funcionarioValeTransporteHelper);
				quantidadeFuncionariosHelper++;
				quantidadeFuncionario++;

				if(quantidadeFuncionario == 2 || totalFuncionarios == quantidadeFuncionariosHelper) {
					Map<String, Object> parametros = new HashMap<String, Object>();
					parametros.put("dataInicio", calculo.getDataInicio());
					parametros.put("dataFim", calculo.getDataFim());
					parametros.put("dataRecibo", calculo.getDataRecibo());
					JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
					jasperReportUtilHelper.setDataSource(listFuncionarioValeTransporteHelper);
					jasperReportUtilHelper.setOrigem("ReciboValeTransporteMultiplo.jasper");
					jasperReportUtilHelper.setParametros(parametros);
					listJasperReportUtilHelpers.add(jasperReportUtilHelper);
					
					quantidadeFuncionario = 0;
					listFuncionarioValeTransporteHelper = new ArrayList<FuncionarioValeTransporteHelper>();
				}
			}
		}
		return listJasperReportUtilHelpers;
	}
}