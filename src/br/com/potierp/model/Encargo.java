package br.com.potierp.model;

import java.math.BigDecimal;

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
		@NamedQuery(name = Encargo.GET_ALL,
				    query = "FROM Encargo "),
	    @NamedQuery(name = Encargo.GET_BY_NOME,
			    query = "FROM Encargo e " +
			    		"WHERE e.nome = :nome ")
		}
)

@Entity
@Table(name="encargo")
public class Encargo extends BaseEntityPotiErp implements Comparable<Encargo>, Cloneable{

	private static final long serialVersionUID = 4928688151010830869L;
	
	/**
	 * NQ para buscar todos os Encargos.
	 */
	public static final String GET_ALL = "Encargo.getAll";
	
	/**
	 * NQ para buscar Encargos de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "Encargo.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private BigDecimal valor;
	
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
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Encargo))
			return false;
		Encargo other = (Encargo) obj;
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public int compareTo(final Encargo outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.nome);
	}
	
	@Override
	public Encargo clone() throws CloneNotSupportedException {
		return (Encargo)super.clone();
	}
}