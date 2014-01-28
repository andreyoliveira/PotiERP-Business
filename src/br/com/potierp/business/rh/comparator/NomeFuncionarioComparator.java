package br.com.potierp.business.rh.comparator;

import java.util.Comparator;

import br.com.potierp.model.Funcionario;

/**
 * @author Doug
 *
 */
public class NomeFuncionarioComparator implements Comparator<Funcionario>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Funcionario func1, Funcionario func2) {
		return func1.getPessoa().getNome().compareTo(func2.getPessoa().getNome());
	}

}
