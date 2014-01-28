package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang.time.DateUtils;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Dependente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.util.DateUtil;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "SalarioFamiliaService", name="SalarioFamiliaService")
@Local(SalarioFamiliaService.class)
@Interceptors(DAOInterceptor.class)
public class SalarioFamiliaServiceBean implements SalarioFamiliaService {

	@Override
	public byte[] getRelatorio(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Salário Familia"}, e);
		}
	}

	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			Collection<Dependente> dependentes = new ArrayList<Dependente>();
			for (Dependente dependente : funcionario.getDependentes()) {
				Date dataAtual = new Date();
				if(dependente.getDataBaixa() != null) {
					if(dataAtual.before(dependente.getDataBaixa())) {
						dependentes.add(dependente);
					}
				} else if(dependente.getDataNascimento() != null) {
					Date dataQuatorzeAnos = DateUtils.addYears(dependente.getDataNascimento(), 14);
					if(dataAtual.before(dataQuatorzeAnos)){
						dependentes.add(dependente);
					}
				}
				
			}
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataExtenso", DateUtil.convertDateToExtenso(funcionario.getDataAdmissao()));
			parametros.put("dependentesAptos", dependentes);
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("SalarioFamilia.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}

}
