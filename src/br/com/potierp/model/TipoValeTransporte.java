package br.com.potierp.model;

import java.math.BigDecimal;

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
		@NamedQuery(name = TipoValeTransporte.GET_ALL,
				    query = "FROM TipoValeTransporte "),
	    @NamedQuery(name = TipoValeTransporte.GET_BY_CODIGO,
			    query = "FROM TipoValeTransporte t " +
			    		"WHERE t.codigo = :codigo ")
		}
)

@Entity
@Table(name="tipovaletransporte")
public class TipoValeTransporte extends BaseEntityPotiErp implements Cloneable, Comparable<TipoValeTransporte>{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os tipos de vale transporte.
	 */
	public static final String GET_ALL = "TipoValeTransporte.getAll";
	
	/**
	 * NQ para buscar tipos de vale transporte de acordo com o codigo. 
	 */
	public static final String GET_BY_CODIGO = "TipoValeTransporte.getByCodigo";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String codigo;
	
	private String nome;
	
	private BigDecimal valor;
	
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
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TipoValeTransporte))
			return false;
		TipoValeTransporte other = (TipoValeTransporte) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
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
		if (situacao != other.situacao)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public TipoValeTransporte clone() throws CloneNotSupportedException {
		return (TipoValeTransporte)super.clone();
	}
	
	@Override
	public int compareTo(final TipoValeTransporte outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.getNome());
	}
}