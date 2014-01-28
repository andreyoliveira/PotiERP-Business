package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * Classe que representa a tabela de log de auditoria.
 * @author
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
@NamedQueries(
		{
		@NamedQuery(name = AuditoriaCadastro.GET_ALL,
				    query = "FROM AuditoriaCadastro ")
		}
)
@Entity
@Table(name="auditoriacadastro")
public class AuditoriaCadastro extends BaseEntityPotiErp {
	
	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 7828115541637463993L;
	
	/**
	 * NQ para busca de todos os registros logados.
	 */
	public static final String GET_ALL = "AuditoriaCadastro.getAll";

	/**
	 * Id.
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Usuario que realizou a operacao.
	 */
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	/**
	 * IP.
	 */
	private String ip;
	
	/**
	 * Data da operação.
	 */
	private Date dataOperacao;
	
	/**
	 * Tipo de operação executada na base.
	 */
	private String tipoOperacao;
	
	/**
	 * Nome da tabela que foi alterada.
	 */
	private String nomeTabela;
	
	/**
	 * Descrição dos campos que foram alterados na base.
	 */
	private String descricaoOperacao;
	
	/**
	 * Construtor default.
	 */
	public AuditoriaCadastro(){}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void addUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * @return the dataOperação
	 */
	public Date getDataOperacao() {
		return dataOperacao;
	}

	/**
	 * @param dataOperacao the dataOperacao to set
	 */
	public void setDataOperacao(final Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	/**
	 * @return the tipoOperacao
	 */
	public String getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * @param tipoOperacao the tipoOperacao to set
	 */
	public void setTipoOperacao(final String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/**
	 * @return the nomeTabela
	 */
	public String getNomeTabela() {
		return nomeTabela;
	}

	/**
	 * @param nomeTabela the nomeTabela to set
	 */
	public void setNomeTabela(final String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	/**
	 * @return the descricaoOperacao
	 */
	public String getDescricaoOperacao() {
		return descricaoOperacao;
	}

	/**
	 * @param descricaoOperacao the descricaoOperacao to set
	 */
	public void setDescricaoOperacao(final String descricaoOperacao) {
		this.descricaoOperacao = descricaoOperacao;
	}
	
	/** (non-Javadoc).
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(41, 83)
		.append(this.getId())
		.append(this.getDataOperacao())
		.append(this.getDescricaoOperacao())
		.append(this.getIp())
		.append(this.getNomeTabela())
		.append(this.getTipoOperacao()).toHashCode();
	}

	/** (non-Javadoc).
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
		if (getClass() != obj.getClass()) {
			return false;
		}

		final AuditoriaCadastro outro = (AuditoriaCadastro) obj;
		 return new EqualsBuilder()
		 .append(this.getId(), outro.getId())
		 .append(this.getDataOperacao(), outro.getDataOperacao())
		 .append(this.getDescricaoOperacao(), outro.getDescricaoOperacao())
		 .append(this.getIp(), outro.getIp())
		 .append(this.getNomeTabela(), outro.getNomeTabela())
		 .append(this.getTipoOperacao(), outro.getTipoOperacao()).isEquals();
	}

	/** (non-Javadoc).
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append(this.getId())
		.append(this.getDataOperacao())
		.append(this.getDescricaoOperacao())
		.append(this.getIp())
		.append(this.getNomeTabela())
		.append(this.getTipoOperacao()).toString();
	}
}