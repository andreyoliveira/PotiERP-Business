package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Banco.GET_ALL,
				    query = "FROM Banco ")
		}
)

@Entity
@Table(name="banco")
public class Banco extends BaseEntityPotiErp{

	private static final long serialVersionUID = 6966135523052090721L;
	
	/**
	 * NQ para buscar todos os Bancos.
	 */
	public static final String GET_ALL = "Banco.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String sigla;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return this.id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
	}
}