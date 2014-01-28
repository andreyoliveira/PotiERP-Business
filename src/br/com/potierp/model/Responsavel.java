package br.com.potierp.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Doug
 *
 */
@NamedQueries( 
		{
			@NamedQuery(name = Responsavel.GET_ALL,
						query = "SELECT distinct resp " +
								"FROM Responsavel resp " +
								" join fetch resp.funcionario func" +
								" left join fetch resp.clientes cli "),
			@NamedQuery(name = Responsavel.GET_POR_FUNCIONARIO,
						query = "SELECT distinct resp " +
								"FROM Responsavel resp " +
								"WHERE resp.funcionario.id = :idFuncionario ")
		}
)
@Entity
@Table(name="responsavel")
public class Responsavel extends BaseEntityPotiErp implements Cloneable{

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -569592314093509941L;
	
	public static final String GET_ALL = "Responsavel.getAll";
	
	/**
	 * NQ para buscar um responsavel por funcionario.
	 */
	public static final String GET_POR_FUNCIONARIO = "Responsavel.getPorFuncionario";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@OneToMany
	@JoinTable(name ="responsavel_cliente",
			joinColumns = @JoinColumn(name="idResponsavel"),
			inverseJoinColumns = @JoinColumn(name="idCliente"))
	private Collection<Cliente> clientes;
	
	private Boolean isSupervisor;

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the clientes
	 */
	public Collection<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(final Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the isSupervisor
	 */
	public Boolean getIsSupervisor() {
		return isSupervisor;
	}

	/**
	 * @param isSupervisor the isSupervisor to set
	 */
	public void setIsSupervisor(final Boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Responsavel other = (Responsavel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.infra.bd.BaseEntityPotiErp#clone()
	 */
	@Override
	public Responsavel clone() throws CloneNotSupportedException {
		return (Responsavel) super.clone();
	}

}
