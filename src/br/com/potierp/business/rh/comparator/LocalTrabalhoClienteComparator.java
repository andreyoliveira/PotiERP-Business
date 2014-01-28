package br.com.potierp.business.rh.comparator;

import java.util.Comparator;

import br.com.potierp.model.LocalTrabalho;

/**
 * @author Doug
 *
 */
public class LocalTrabalhoClienteComparator implements Comparator<LocalTrabalho> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(LocalTrabalho localTrabalho1, LocalTrabalho localTrabalho2) {
		return localTrabalho1.getCliente().getNomeFantasia().compareTo(localTrabalho2.getCliente().getNomeFantasia());
	}

}
