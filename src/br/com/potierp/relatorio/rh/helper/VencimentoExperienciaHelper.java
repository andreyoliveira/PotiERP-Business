package br.com.potierp.relatorio.rh.helper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;

/**
 * @author Doug
 *
 */
public class VencimentoExperienciaHelper implements Serializable{

	private static final long serialVersionUID = -3885425436433313664L;
	
	private Collection<Cliente> clientes = new HashSet<Cliente>();
	
	private Date dataPrimeiroVencimento;
	
	private Date dataSegundoVencimento;
	
	private Funcionario funcionario;

	/**
	 * @return the clientes
	 */
	public Collection<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the dataPrimeiroVencimento
	 */
	public Date getDataPrimeiroVencimento() {
		return dataPrimeiroVencimento;
	}

	/**
	 * @param dataPrimeiroVencimento the dataPrimeiroVencimento to set
	 */
	public void setDataPrimeiroVencimento(Date dataPrimeiroVencimento) {
		this.dataPrimeiroVencimento = dataPrimeiroVencimento;
	}

	/**
	 * @return the dataSegundoVencimento
	 */
	public Date getDataSegundoVencimento() {
		return dataSegundoVencimento;
	}

	/**
	 * @param dataSegundoVencimento the dataSegundoVencimento to set
	 */
	public void setDataSegundoVencimento(Date dataSegundoVencimento) {
		this.dataSegundoVencimento = dataSegundoVencimento;
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
