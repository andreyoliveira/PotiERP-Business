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
		@NamedQuery(name = Setor.GET_ALL,
				    query = "FROM Setor "),
	    @NamedQuery(name = Setor.GET_BY_NOME,
			    query = "FROM Setor s " +
			    		"WHERE s.nome = :setor ")
		}
)

@Entity
@Table(name="setor")
public class Setor extends BaseEntityPotiErp implements Cloneable, Comparable<Setor>{

	private static final long serialVersionUID = 579852748414553976L;
	
	/**
	 * NQ para buscar todos os setores
	 */
	public static final String GET_ALL = "Setor.getAll";
	
	/**
	 * NQ para buscar setores por nome. 
	 */
	public static final String GET_BY_NOME = "Setor.getByNome";
	
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
		if (!(obj instanceof Setor))
			return false;
		Setor other = (Setor) obj;
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
		return "Setor [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + "]";
	}

	@Override
	public Setor clone() throws CloneNotSupportedException {
		return (Setor)super.clone();
	}
	
	@Override
	public int compareTo(final Setor outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.getNome(), outro.getNome());
	}
}