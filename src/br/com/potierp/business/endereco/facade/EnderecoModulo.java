package br.com.potierp.business.endereco.facade;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Pais;


public interface EnderecoModulo{

	List<Pais> buscarTodosPaises() throws PotiErpException;
	
	List<Estado> buscarEstadosPorPais(Pais pais) throws PotiErpException;
	
	Pais buscarPaisPorSigla(String sigla) throws PotiErpException;
	
	Cidade salvarCidade(Cidade cidade)throws PotiErpMensagensException, PotiErpException;
	
	void excluirCidade(Cidade cidade)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaCidade(List<Cidade> cidades)throws PotiErpMensagensException, PotiErpException;
	
	List<Cidade> consultarCidade(Cidade cidade)throws PotiErpException;
	
	List<Cidade> consultarTodasCidades() throws PotiErpException;
	
	List<Cidade> consultarPorEstado(Estado estado) throws PotiErpException;
}