package br.com.potierp.business.rh.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.EnderecoRepositorioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.EnderecoRepositorio;

/**
 * @author renan
 */
@Stateless(mappedName="LogradouroService", name="LogradouroService")
@Local(LogradouroService.class)
@Interceptors(DAOInterceptor.class)
public class LogradouroServiceBean implements LogradouroService {
	
	@DAO
	private EnderecoRepositorioDao enderecoDao;
	
	public EnderecoRepositorio salvarEndereco(EnderecoRepositorio endereco) throws PotiErpException {
		try {
			if(endereco.isNew()){
				return incluirEndereco(endereco);
			}else{
				return alterarEndereco(endereco);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	private EnderecoRepositorio incluirEndereco(EnderecoRepositorio endereco) throws PotiErpException {
		try {
			return enderecoDao.salvarComFlush(endereco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Endereco"}, e);
		}
	}

	private EnderecoRepositorio alterarEndereco(EnderecoRepositorio endereco) throws PotiErpException {
		try {
			return enderecoDao.salvarComFlush(endereco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Endereco"}, e);
		}
	}

	public void excluirEndereco(EnderecoRepositorio endereco) throws PotiErpException {
		try {
			enderecoDao.excluir(endereco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Endereco"}, e);
		}
	}

	public List<EnderecoRepositorio> consultarEndereco(EnderecoRepositorio endereco)
			throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EnderecoRepositorio consultarEnderecoPorCep(String cep)throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
}