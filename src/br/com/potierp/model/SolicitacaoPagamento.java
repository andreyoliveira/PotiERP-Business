package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = SolicitacaoPagamento.GET_ALL, 
				query="FROM SolicitacaoPagamento")
		}
	)
@Entity
@Table(name="solicitacaoPagamento")
public class SolicitacaoPagamento extends BaseEntityPotiErp implements Cloneable, Comparable<SolicitacaoPagamento> {
	
	/**
	 * NQ para a busca de todas as Solicitações de Pagamento.
	 */
	public static final String GET_ALL = "SolicitacaoPagamento.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Long codigo;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public int compareTo(final SolicitacaoPagamento arg0) {
		// TODO [Batata] Implementar compareTo quando a entidade estiver completa.
		return 0;
	}
	


}
