package br.com.potierp.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Doug
 *
 */
@NamedQueries(
		{
			@NamedQuery(name=Afastamento.GET_ALL, 
						query="FROM Afastamento ")
		}
)
@Entity
@Table(name="afastamento")
public class Afastamento extends BaseEntityPotiErp implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5892263054727037731L;
	
	public static final String GET_ALL = "Afastamento.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataUltimoDiaTrabalho;
	 
	@Temporal(TemporalType.DATE)
	private Date dataAfastamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataRetorno;
	
	@ManyToOne
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
	@Enumerated(EnumType.STRING)
	private MotivoAfastamentoEnum motivo;
	
	private boolean afastamentoComEstabilidade;

	/* (non-Javadoc)
	 * @see br.com.potierp.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @return the dataUltimoDiaTrabalho
	 */
	public Date getDataUltimoDiaTrabalho() {
		return dataUltimoDiaTrabalho;
	}

	/**
	 * @param dataUltimoDiaTrabalho the dataUltimoDiaTrabalho to set
	 */
	public void setDataUltimoDiaTrabalho(final Date dataUltimoDiaTrabalho) {
		this.dataUltimoDiaTrabalho = dataUltimoDiaTrabalho;
	}

	/**
	 * @return the dataAfastamento
	 */
	public Date getDataAfastamento() {
		return dataAfastamento;
	}

	/**
	 * @param dataAfastamento the dataAfastamento to set
	 */
	public void setDataAfastamento(final Date dataAfastamento) {
		this.dataAfastamento = dataAfastamento;
	}

	/**
	 * @return the dataRetorno
	 */
	public Date getDataRetorno() {
		return dataRetorno;
	}

	/**
	 * @param dataRetorno the dataRetorno to set
	 */
	public void setDataRetorno(final Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public MotivoAfastamentoEnum getMotivo() {
		return motivo;
	}

	public void setMotivo(final MotivoAfastamentoEnum motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the afastamentoComEstabilidade
	 */
	public boolean isAfastamentoComEstabilidade() {
		return afastamentoComEstabilidade;
	}

	/**
	 * @param afastamentoComEstabilidade the afastamentoComEstabilidade to set
	 */
	public void setAfastamentoComEstabilidade(final boolean afastamentoComEstabilidade) {
		this.afastamentoComEstabilidade = afastamentoComEstabilidade;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Override
	public Afastamento clone() throws CloneNotSupportedException {
		return (Afastamento)super.clone();
	}
	
}
