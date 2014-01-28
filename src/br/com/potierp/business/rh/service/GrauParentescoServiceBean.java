package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.GrauParentescoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.GrauParentesco;

/**
 * @author renan
 */
@Stateless(mappedName="GrauParentescoService", name="GrauParentescoService")
@Local(GrauParentescoService.class)
@Interceptors(DAOInterceptor.class)
public class GrauParentescoServiceBean implements GrauParentescoService {
	
	@DAO
	private GrauParentescoDao grauParentescoDao;
	
	@Override
	public GrauParentesco salvarGrauParentesco(final GrauParentesco grauParentesco) throws PotiErpMensagensException, PotiErpException {
		try{
			verificarDuplicidade(grauParentesco);
			if (grauParentesco.isNew()) {
				return incluirGrauParentesco(grauParentesco);
			} else {
				return alterarGrauParentesco(grauParentesco);
			}
		}catch(DaoException de){
			throw new PotiErpException(de);
		}
	}

	private void verificarDuplicidade(final GrauParentesco grauParentesco) throws DaoException, PotiErpMensagensException {
		GrauParentesco grauParentescoDesc = grauParentescoDao.getPorDescricao(grauParentesco.getDescricao().trim());
		if(grauParentescoDesc != null && !grauParentescoDesc.getId().equals(grauParentesco.getId())){
			throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_GRAUPARENTESCO_COM_ESTA_DESCRICAO.getMsg());
		}		
	}

	private GrauParentesco incluirGrauParentesco(final GrauParentesco grauParentesco) throws PotiErpException {
		try {
			return grauParentescoDao.salvar(grauParentesco);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "GrauParentesco" }, e);
		}
	}

	private GrauParentesco alterarGrauParentesco(final GrauParentesco grauParentesco) throws PotiErpException {
		try {
			return grauParentescoDao.salvar(grauParentesco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "GrauParentesco" }, e);
		}
	}

	@Override
	public void excluirGrauParentesco(final GrauParentesco grauParentesco) throws PotiErpException {
		try {
			grauParentescoDao.excluir(grauParentesco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "GrauParentesco" }, e);
		}
	}

	@Override
	public void excluirListaGrauParentesco(final List<GrauParentesco> grausParentescos) throws PotiErpException {
		try {
			for (GrauParentesco grauParentesco : grausParentescos){
				grauParentescoDao.excluir(grauParentesco);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "GrauParentesco" }, e);
		}
	}

	@Override
	public List<GrauParentesco> consultarGrauParentesco(final GrauParentesco grauParentesco) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrauParentesco> consultarTodosGrauParentescos() throws PotiErpException {
		try {
			Collection<GrauParentesco> collectionGrauParentesco = grauParentescoDao.getAll();
			return new ArrayList<GrauParentesco>(collectionGrauParentesco);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "GrauParentesco" }, e);
		}
	}
}