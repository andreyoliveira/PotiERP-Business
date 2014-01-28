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
		@NamedQuery(name = TipoAdmissao.GET_ALL,
				    query = "FROM TipoAdmissao "),
	    @NamedQuery(name = TipoAdmissao.GET_BY_NOME,
			    query = "FROM TipoAdmissao a " +
			    		"WHERE a.nome = :nome ")
		}
)

@Entity
@Table(name="tipoadmissao")
public class TipoAdmissao extends BaseEntityPotiErp implements Comparable<TipoAdmissao>, Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os tipos de admissão.
	 */
	public static final String GET_ALL = "TipoAdmissao.getAll";
	
	/**
	 * NQ para buscar tipos de admissao de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "TipoAdmissao.getByNome";
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
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
		if (!(obj instanceof TipoAdmissao))
			return false;
		TipoAdmissao other = (TipoAdmissao) obj;
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
	public String toString() {
		return "TipoAdmissao [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + "]";
	}

	@Override
	public int compareTo(final TipoAdmissao outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.nome);
	}
	
	@Override
	public TipoAdmissao clone() throws CloneNotSupportedException {
		return (TipoAdmissao)super.clone();
	}
}