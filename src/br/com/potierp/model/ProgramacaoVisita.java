package br.com.potierp.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Ralph
 */
@NamedQueries( 
	{
		@NamedQuery(name = ProgramacaoVisita.GET_ALL,
					query = "select distinct progvis from ProgramacaoVisita progvis " +
							"join fetch progvis.responsavel resp" +
							"left join fetch progvis.datasProgramacaoVisitas datas "),
		
		@NamedQuery(name = ProgramacaoVisita.GET_POR_RESPONSAVEL,
					query = "select distinct progvis from ProgramacaoVisita progvis " +
							"join fetch progvis.responsavel resp" +
							"left join fetch progvis.datasProgramacaoVisitas datas " +
							"where progvis.responsavel.id = :idResponsavel "),
							
		@NamedQuery(name = ProgramacaoVisita.GET_POR_RESPONSAVEL_E_CLIENTE,
					query = "select distinct progvis from ProgramacaoVisita progvis " +
							"join fetch progvis.responsavel resp " +
							"left join fetch progvis.datasProgramacaoVisitas datas " +
							"where progvis.responsavel.id = :idResponsavel " +
							"and progvis.idCliente = :idCliente "),
		@NamedQuery(name = ProgramacaoVisita.GET_POR_DATAPROGRAMADA,
				query = "select distinct progvis from ProgramacaoVisita progvis " +
						"inner join fetch progvis.responsavel resp " +
						"inner join fetch progvis.datasProgramacaoVisitas datas " +
						"where datas.dataProgramada = :dataProgramada " +
						"order by resp.id ")						
	}
)
@Entity
@Table(name="programacaovisitas")
public class ProgramacaoVisita extends BaseEntityPotiErp implements Cloneable {

	private static final long serialVersionUID = 1999062361721711859L;
	
	public static final String GET_ALL = "ProgramacaoVisita.getAll";
	
	public static final String GET_POR_RESPONSAVEL = "ProgramacaoVisita.getPorResponsavel";
	
	public static final String GET_POR_RESPONSAVEL_E_CLIENTE = "ProgramacaoVisita.getPorResponsavelECliente";
	
	public static final String GET_POR_DATAPROGRAMADA = "ProgramacaoVisita.getPordataProgramada";

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Responsavel responsavel;
	
	@NotNull
	@Column(name="cliente_id")
	private Long idCliente;
	
	@OneToMany(mappedBy="programacaoVisita", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<DataProgramacaoVisita> datasProgramacaoVisitas;
	
	@ManyToOne
	@JoinColumn(name = "idTipoServico")
	private TipoServico tipoServico;
	
	@Transient
	private Cliente cliente;
	
	private void carregarCliente() {
		if(idCliente != null) {
			for(Cliente cli : responsavel.getClientes()) {
				if(cli.getId().equals(idCliente)) {
					this.cliente = cli;
					break;
				}
			}
		}
	}

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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(final Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	
	/**
	 * @return the tipoServico
	 */
	public TipoServico getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico the tipoServico to set
	 */
	public void setTipoServico(final TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(final Long idCliente) {
		this.idCliente = idCliente;
	}

	public Collection<DataProgramacaoVisita> getDatasProgramacaoVisitas() {
		return datasProgramacaoVisitas;
	}

	public void setDatasProgramacaoVisitas(final Collection<DataProgramacaoVisita> datasProgramacaoVisitas) {
		this.datasProgramacaoVisitas = datasProgramacaoVisitas;
	}

	public Cliente getCliente() {
		if(cliente == null)
			carregarCliente();
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramacaoVisita other = (ProgramacaoVisita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}

	@Override
	public ProgramacaoVisita clone() throws CloneNotSupportedException {
		return (ProgramacaoVisita)super.clone();
	}
}
