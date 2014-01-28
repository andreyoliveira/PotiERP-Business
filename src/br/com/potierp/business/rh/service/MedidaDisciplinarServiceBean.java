package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.MedidaDisciplinarDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.MedidaDisciplinar;

@Stateless(mappedName="MedidaDisciplinarService", name="MedidaDisciplinarService")
@Local(MedidaDisciplinarService.class)
@Interceptors(DAOInterceptor.class)
public class MedidaDisciplinarServiceBean implements MedidaDisciplinarService {
	
	@DAO
	private MedidaDisciplinarDao medidaDisciplinarDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;

	@Override
	public MedidaDisciplinar salvar(MedidaDisciplinar medidaDisciplinar)
			throws PotiErpException {
		try {
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(medidaDisciplinar.getFuncionario().getId());
			medidaDisciplinar.setFuncionario(funcionario);
			return medidaDisciplinarDao.salvar(medidaDisciplinar);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"MedidaDisciplinar"}, e);
		}
	}

	@Override
	public List<MedidaDisciplinar> consultarTodasMedidasDisciplinares()
			throws PotiErpException {
		try {
			Collection<MedidaDisciplinar> medidas = medidaDisciplinarDao.getAll();
			return new ArrayList<MedidaDisciplinar>(medidas);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"MedidaDisciplinar"}, e);
		}
	}

	@Override
	public void excluirLista(List<MedidaDisciplinar> medidas)
			throws PotiErpException {
		try {
			for(MedidaDisciplinar medida : medidas) {
				medidaDisciplinarDao.excluir(medida);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"MedidaDisciplinar"}, e);
		}
	}

}
