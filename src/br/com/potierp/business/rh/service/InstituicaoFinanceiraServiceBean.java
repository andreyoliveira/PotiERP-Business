package br.com.potierp.business.rh.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.AgenciaDao;
import br.com.potierp.dao.BancoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Agencia;
import br.com.potierp.model.Banco;

/**
 * @author renan
 */
@Stateless(mappedName="InstituicaoFinanceiraService", name="InstituicaoFinanceiraService")
@Local(InstituicaoFinanceiraService.class)
@Interceptors(DAOInterceptor.class)
public class InstituicaoFinanceiraServiceBean implements InstituicaoFinanceiraService {
	
	@DAO
	private BancoDao bancoDao;
	
	@DAO
	private AgenciaDao agenciaDao;
	
	public Banco salvarBanco(Banco banco) throws PotiErpException {
		try {
			if(banco.isNew()){
				return incluirBanco(banco);
			}else{
				return alterarBanco(banco);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	private Banco incluirBanco(Banco banco) throws PotiErpException {
		try {
			return bancoDao.salvar(banco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Banco"}, e);		
		}
	}

	private Banco alterarBanco(Banco banco) throws PotiErpException {
		try {
			return bancoDao.salvar(banco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Banco"}, e);		
		}
	}
	
	public void excluirBanco(Banco banco) throws PotiErpException {
		try {
			bancoDao.excluir(banco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Banco"}, e);		
		}		
	}
	
	public List<Banco> consultarBanco(Banco banco) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Agencia salvarAgencia(Agencia agencia) throws PotiErpException {
		try {
			if(agencia.isNew()){
				return incluirAgencia(agencia);
			}else{
				return alterarAgencia(agencia);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	private Agencia incluirAgencia(Agencia agencia) throws PotiErpException {
		try {
			return agenciaDao.salvar(agencia);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Agencia"}, e);		
		}
	}

	private Agencia alterarAgencia(Agencia agencia) throws PotiErpException {
		try {
			return agenciaDao.salvar(agencia);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Agencia"}, e);		
		}
	}
	
	public void excluirAgencia(Agencia agencia) throws PotiErpException {
		try {
			agenciaDao.excluir(agencia);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Agencia"}, e);		
		}
	}
	
	public List<Agencia> consultarAgencia(Agencia agencia)
			throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
}