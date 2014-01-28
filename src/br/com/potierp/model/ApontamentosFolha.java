package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = ApontamentosFolha.GET_ALL,
				    query = "FROM ApontamentosFolha ")
		}
)

@Entity
@Table(name="apontamentosfolha")
public class ApontamentosFolha extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os tipos de cesta básica.
	 */
	public static final String GET_ALL = "ApontamentosFolha.getAll";
	
	/**
	 * NQ para buscar tipos de cesta básica de acordo com o codigo. 
	 */
	public static final String GET_BY_CODIGO = "ApontamentosFolha.getByCodigo";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "idVerba")
	private Verba verba;
	
	private String quantidadeVerba;
	
	private Date dataPeriodoFolha;
	
	private BigDecimal valor;
	
	private Boolean isAtestado;
	
	private Boolean isAbonar;
	
	private Long quantidadeDsr;
	
	private BigDecimal valorDsr;
	
	private BigDecimal valorVt;
	
	private BigDecimal valorVr;
	
	private Boolean isPerdeCesta;
	
	private BigDecimal valorCesta;
	
	private Date dataInclusao;
	
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
	 * @return the verba
	 */
	public Verba getVerba() {
		return verba;
	}

	/**
	 * @param verba the verba to set
	 */
	public void setVerba(final Verba verba) {
		this.verba = verba;
	}
	
	/**
	 * @return the quantidadeVerba
	 */
	public String getQuantidadeVerba() {
		return quantidadeVerba;
	}

	/**
	 * @param quantidadeVerba the quantidadeVerba to set
	 */
	public void setQuantidadeVerba(final String quantidadeVerba) {
		this.quantidadeVerba = quantidadeVerba;
	}

	/**
	 * @return the dataPeriodoFolha
	 */
	public Date getDataPeriodoFolha() {
		return dataPeriodoFolha;
	}

	/**
	 * @param dataPeriodoFolha the dataPeriodoFolha to set
	 */
	public void setDataPeriodoFolha(final Date dataPeriodoFolha) {
		this.dataPeriodoFolha = dataPeriodoFolha;
	}
	
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the isAtestado
	 */
	public Boolean getIsAtestado() {
		return isAtestado;
	}

	/**
	 * @param isAtestado the isAtestado to set
	 */
	public void setIsAtestado(final Boolean isAtestado) {
		this.isAtestado = isAtestado;
	}

	/**
	 * @return the isAbonar
	 */
	public Boolean getIsAbonar() {
		return isAbonar;
	}

	/**
	 * @param isAbonar the isAbonar to set
	 */
	public void setIsAbonar(final Boolean isAbonar) {
		this.isAbonar = isAbonar;
	}

	/**
	 * @return the quantidadeDsr
	 */
	public Long getQuantidadeDsr() {
		if(Long.valueOf(0).equals(quantidadeDsr)){
			this.quantidadeDsr = null;
		}
		return quantidadeDsr;
	}

	/**
	 * @param quantidadeDsr the quantidadeDsr to set
	 */
	public void setQuantidadeDsr(final Long quantidadeDsr) {
		this.quantidadeDsr = quantidadeDsr;
	}

	/**
	 * @return the valorDsr
	 */
	public BigDecimal getValorDsr() {
		return valorDsr;
	}

	/**
	 * @param valorDsr the valorDsr to set
	 */
	public void setValorDsr(final BigDecimal valorDsr) {
		this.valorDsr = valorDsr;
	}

	/**
	 * @return the valorVt
	 */
	public BigDecimal getValorVt() {
		return valorVt;
	}

	/**
	 * @param valorVt the valorVt to set
	 */
	public void setValorVt(final BigDecimal valorVt) {
		this.valorVt = valorVt;
	}

	/**
	 * @return the valorVr
	 */
	public BigDecimal getValorVr() {
		return valorVr;
	}

	/**
	 * @param valorVr the valorVr to set
	 */
	public void setValorVr(final BigDecimal valorVr) {
		this.valorVr = valorVr;
	}

	/**
	 * @return the isPerdeCesta
	 */
	public Boolean getIsPerdeCesta() {
		return isPerdeCesta;
	}

	/**
	 * @param isPerdeCesta the isPerdeCesta to set
	 */
	public void setIsPerdeCesta(final Boolean isPerdeCesta) {
		this.isPerdeCesta = isPerdeCesta;
	}

	/**
	 * @return the valorCesta
	 */
	public BigDecimal getValorCesta() {
		return valorCesta;
	}

	/**
	 * @param valorCesta the valorCesta to set
	 */
	public void setValorCesta(final BigDecimal valorCesta) {
		this.valorCesta = valorCesta;
	}

	/**
	 * @return the dataInclusao
	 */
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/**
	 * @param dataInclusao the dataInclusao to set
	 */
	public void setDataInclusao(final Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Override
	public ApontamentosFolha clone() throws CloneNotSupportedException {
		return (ApontamentosFolha)super.clone();
	}
}