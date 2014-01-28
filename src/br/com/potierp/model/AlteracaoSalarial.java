package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Andrey Oliveira
 */

@NamedQueries(
		{
		@NamedQuery(name = AlteracaoSalarial.GET_ALL,
					query = "FROM AlteracaoSalarial ")	
		}
)
@Entity
@Table(name="alteracaosalarial")
public class AlteracaoSalarial extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 5835321714091821877L;

	public static final String GET_ALL = "AlteracaoSalarial.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	private Date dataAlteracao;
	
	private BigDecimal salarioAnterior;
	
	private BigDecimal reajuste;
	
	private BigDecimal salarioAtual;
	
	@ManyToOne
	@JoinColumn(name = "idCargoAnterior")
	private Cargo cargoAnterior;
	
	@ManyToOne
	@JoinColumn(name = "idCargo")
	private Cargo cargoAtual;
	
	@Enumerated(EnumType.STRING)
	private MotivoEnum motivo;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(final Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public BigDecimal getSalarioAnterior() {
		return salarioAnterior;
	}

	public void setSalarioAnterior(final BigDecimal salarioAnterior) {
		this.salarioAnterior = salarioAnterior;
	}

	public BigDecimal getReajuste() {
		return reajuste;
	}

	public void setReajuste(final BigDecimal reajuste) {
		this.reajuste = reajuste;
	}

	public BigDecimal getSalarioAtual() {
		return salarioAtual;
	}

	public void setSalarioAtual(final BigDecimal salarioAtual) {
		this.salarioAtual = salarioAtual;
	}

	public Cargo getCargoAtual() {
		return cargoAtual;
	}

	public void setCargoAtual(final Cargo cargoAtual) {
		this.cargoAtual = cargoAtual;
	}
	
	/**
	 * @return the cargoAnterior
	 */
	public Cargo getCargoAnterior() {
		return cargoAnterior;
	}

	/**
	 * @param cargoAnterior the cargoAnterior to set
	 */
	public void setCargoAnterior(final Cargo cargoAnterior) {
		this.cargoAnterior = cargoAnterior;
	}

	/**
	 * @return the motivo
	 */
	public MotivoEnum getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(final MotivoEnum motivo) {
		this.motivo = motivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result
				+ ((reajuste == null) ? 0 : reajuste.hashCode());
		result = prime * result
				+ ((salarioAnterior == null) ? 0 : salarioAnterior.hashCode());
		result = prime * result
				+ ((salarioAtual == null) ? 0 : salarioAtual.hashCode());
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
		AlteracaoSalarial other = (AlteracaoSalarial) obj;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (reajuste == null) {
			if (other.reajuste != null)
				return false;
		} else if (!reajuste.equals(other.reajuste))
			return false;
		if (salarioAnterior == null) {
			if (other.salarioAnterior != null)
				return false;
		} else if (!salarioAnterior.equals(other.salarioAnterior))
			return false;
		if (salarioAtual == null) {
			if (other.salarioAtual != null)
				return false;
		} else if (!salarioAtual.equals(other.salarioAtual))
			return false;
		return true;
	}

	@Override
	public AlteracaoSalarial clone() throws CloneNotSupportedException {
		return (AlteracaoSalarial)super.clone();
	}	
}
