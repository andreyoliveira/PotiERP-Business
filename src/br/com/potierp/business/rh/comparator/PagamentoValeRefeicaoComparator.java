package br.com.potierp.business.rh.comparator;

import java.util.Comparator;

import br.com.potierp.model.PagamentoValeRefeicao;

/**
 * @author Doug
 *
 */
public class PagamentoValeRefeicaoComparator implements Comparator<PagamentoValeRefeicao>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final PagamentoValeRefeicao pagamento1, final PagamentoValeRefeicao pagamento2) {
		return pagamento1.getTipoValeRefeicao().getNome().compareTo(pagamento2.getTipoValeRefeicao().getNome());
	}
}