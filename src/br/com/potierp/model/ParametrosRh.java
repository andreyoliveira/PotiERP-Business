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
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = ParametrosRh.GET_ALL,
				    query = "FROM ParametrosRh "),
	    @NamedQuery(name = ParametrosRh.GET_BY_NOME,
			    query = "FROM ParametrosRh pa " +
			    		"WHERE pa.nome = :nome ")
		}
)

@Entity
@Table(name="parametrosrh")
public class ParametrosRh extends BaseEntityPotiErp implements Comparable<ParametrosRh>, Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os parametros do módulo RH.
	 */
	public static final String GET_ALL = "ParametrosRh.getAll";
	
	/**
	 * NQ para buscar parametrosRH de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "ParametrosRh.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoParametroEnum tipoParametro;
	
	private String valor;

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

	/**
	 * @return the tipoParametro
	 */
	public TipoParametroEnum getTipoParametro() {
		return tipoParametro;
	}

	/**
	 * @param tipoParametro the tipoParametro to set
	 */
	public void setTipoParametro(final TipoParametroEnum tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(final String valor) {
		this.valor = valor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ParametrosRh)) {
			return false;
		}
		ParametrosRh other = (ParametrosRh) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final ParametrosRh outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.nome);
	}
	
	@Override
	public ParametrosRh clone() throws CloneNotSupportedException {
		return (ParametrosRh)super.clone();
	}
}