package br.com.potierp.business.rh.comparator;

import java.util.Comparator;

import br.com.potierp.model.PagamentoValeTransporte;

/**
 * @author Doug
 *
 */
public class PagamentoValeTransporteComparator implements Comparator<PagamentoValeTransporte>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final PagamentoValeTransporte pagamento1, final PagamentoValeTransporte pagamento2) {
		return pagamento1.getTipoValeTransporte().getNome().compareTo(pagamento2.getTipoValeTransporte().getNome());
	}

}
