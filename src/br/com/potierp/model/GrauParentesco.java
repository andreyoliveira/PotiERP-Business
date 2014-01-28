package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = GrauParentesco.GET_ALL,
				    query = "FROM GrauParentesco gp " +
				    		"order by gp.descricao"),
		@NamedQuery(name=GrauParentesco.GET_POR_DESCRICAO,
					query = "FROM GrauParentesco gp " +
							"where gp.descricao = :descricao ")
		}
)
@Entity
@Table(name="grauparentesco")
public class GrauParentesco extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -1660705536357525725L;
	
	/**
	 * NQ para buscar todos os Graus de Parentesco.
	 */
	public static final String GET_ALL = "GrauParentesco.getAll";
	
	/**
	 * NQ para buscar Graus de Parentesco de acordo com a descricao.
	 */
	public static final String GET_POR_DESCRICAO = "GrauParentesco.getPorDescricao";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(final SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GrauParentesco))
			return false;
		GrauParentesco other = (GrauParentesco) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "GrauParentesco [id=" + id + ", descricao=" + descricao
				+ ", situacao=" + situacao + "]";
	}

	@Override
	public GrauParentesco clone() throws CloneNotSupportedException {
		return (GrauParentesco)super.clone();
	}
}