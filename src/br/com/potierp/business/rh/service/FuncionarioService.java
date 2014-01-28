package br.com.potierp.business.rh.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.SituacaoFuncionario;

public interface FuncionarioService {
	
	Funcionario salvar(Funcionario funcionario)throws PotiErpException;
	
	void excluir(Funcionario funcionario)throws PotiErpException;
	
	void excluirLista(List<Funcionario> funcionarios)throws PotiErpException;
	
	List<Funcionario> consultar(Funcionario funcionario)throws PotiErpException;
	
	List<Funcionario> consultar(final String prefixo) throws PotiErpException;
	
	Funcionario consultar(Long idFuncionario)throws PotiErpException;
	
	List<Funcionario> consultarTodos()throws PotiErpException;
	
	List<Funcionario> consultarTodos(int firstRow, int lastRow)throws PotiErpException;
	
	List<Funcionario> consultarPesquisa(Cliente cliente,
			SituacaoFuncionario situacaoFuncionario) throws PotiErpException;
	
	List<Funcionario> consultarPorDataAdmissao(Date dataInicial,
			Date dataFinal, Cidade cidade, Collection<Cliente> clientes)
			throws PotiErpException;
	
	Funcionario consultarPorRe(Funcionario funcionario) throws PotiErpException;
	
	List<Funcionario> consultarTodosComNomeRE() throws PotiErpException;
	
	Funcionario consultarPorCalculoValeTransporte(final Long idCalculoValeTransporte) throws PotiErpException;
	
	Number consultarTotalFuncionarios() throws PotiErpException;
	
	Number consultarTotalFuncionariosPesquisa(Cliente cliente,
			SituacaoFuncionario situacaoFuncionario) throws PotiErpException;
}
