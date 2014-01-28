package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = TipoDemissao.GET_ALL,
				    query = "FROM TipoDemissao "),
	    @NamedQuery(name = TipoDemissao.GET_BY_NOME,
			    query = "FROM TipoDemissao td " +
			    		"WHERE td.nome = :nome ")
		}
)

@Entity
@Table(name="tipodemissao")
public class TipoDemissao extends BaseEntityPotiErp implements Comparable<TipoDemissao>, Cloneable{

	private static final long serialVersionUID = 6133205769920380595L;
	
	/**
	 * NQ para buscar todos os Tipos de demissão.
	 */
	public static final String GET_ALL = "TipoDemissao.getAll";
	
	/**
	 * NQ para buscar Tipos de demissões de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "TipoDemissao.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao;

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

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TipoDemissao))
			return false;
		TipoDemissao other = (TipoDemissao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(final TipoDemissao outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.nome);
	}
	
	@Override
	public TipoDemissao clone() throws CloneNotSupportedException {
		return (TipoDemissao)super.clone();
	}
}