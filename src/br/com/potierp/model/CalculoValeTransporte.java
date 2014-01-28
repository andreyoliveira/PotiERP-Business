package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import br.com.potierp.business.rh.comparator.PagamentoValeTransporteComparator;
import br.com.potierp.business.rh.helper.AquisicaoValeTransporteHelper;
import br.com.potierp.business.rh.helper.FuncionarioValeTransporteHelper;
import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = CalculoValeTransporte.GET_ALL,
				    query = "select distinct calc FROM CalculoValeTransporte calc " +
				    		"left join fetch calc.pagamentosValeTransporte pgt " +
				    		"order by calc.dataInicio ")
		}
)

@Entity
@Table(name="calculovaletransporte")
public class CalculoValeTransporte extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -8101089240880683457L;

	/**
	 * NQ para buscar todos os Calculos ValeTransporte.
	 */
	public static final String GET_ALL = "CalculoValeTransporte.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	private String mesAno;
	
	@Temporal(TemporalType.DATE)
	private Date dataRecibo;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	
	private Integer quantidadeTotal;
	
	private BigDecimal valorTotal;
	
	@Enumerated(EnumType.STRING)
	private SituacaoValeTransporteEnum situacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipoCalculo")
	private TipoCalculoValeTransporteEnum tipoCalculoEnum;
	
	@OneToMany(mappedBy="calculoValeTransporte", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<PagamentoValeTransporte> pagamentosValeTransporte;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
	@Transient
	private List<FuncionarioValeTransporteHelper> funcionariosValeTransporteHelper;
	
	@Transient
	private List<AquisicaoValeTransporteHelper> aquisicoesValeTransporteHelper;
	
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

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return the dataRecibo
	 */
	public Date getDataRecibo() {
		return dataRecibo;
	}

	/**
	 * @param dataRecibo the dataRecibo to set
	 */
	public void setDataRecibo(final Date dataRecibo) {
		this.dataRecibo = dataRecibo;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(final Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(final Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the quantidadeTotal
	 */
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	/**
	 * @param quantidadeTotal the quantidadeTotal to set
	 */
	public void setQuantidadeTotal(final Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(final BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the situacao
	 */
	public SituacaoValeTransporteEnum getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(final SituacaoValeTransporteEnum situacao) {
		this.situacao = situacao;
	}
	
	/**
	 * @return the pagamentosValeTransporte
	 */
	public Collection<PagamentoValeTransporte> getPagamentosValeTransporte() {
		return pagamentosValeTransporte;
	}

	/**
	 * @param pagamentosValeTransporte the pagamentosValeTransporte to set
	 */
	public void setPagamentosValeTransporte(
			final Collection<PagamentoValeTransporte> pagamentosValeTransporte) {
		this.pagamentosValeTransporte = pagamentosValeTransporte;
	}
	
	/**
	 * @return the funcionariosValeTransporteHelper
	 */
	public List<FuncionarioValeTransporteHelper> getFuncionariosValeTransporteHelper() {
		return funcionariosValeTransporteHelper;
	}

	/**
	 * @param funcionariosValeTransporteHelper the funcionariosValeTransporteHelper to set
	 */
	public void setFuncionariosValeTransporteHelper(
			final List<FuncionarioValeTransporteHelper> funcionariosValeTransporteHelper) {
		this.funcionariosValeTransporteHelper = funcionariosValeTransporteHelper;
	}
	
	/**
	 * @return the mesAno
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno the mesAno to set
	 */
	public void setMesAno(final String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return the tipoCalculoEnum
	 */
	public TipoCalculoValeTransporteEnum getTipoCalculoEnum() {
		return tipoCalculoEnum;
	}

	/**
	 * @param tipoCalculoEnum the tipoCalculoEnum to set
	 */
	public void setTipoCalculoEnum(final TipoCalculoValeTransporteEnum tipoCalculoEnum) {
		this.tipoCalculoEnum = tipoCalculoEnum;
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
	 * @return the aquisicoesValeTransporteHelper
	 */
	public List<AquisicaoValeTransporteHelper> getAquisicoesValeTransporteHelper() {
		
		Collection<PagamentoValeTransporte> pagamentosValeTransporte = this.pagamentosValeTransporte;
		Collections.sort((List<PagamentoValeTransporte>) pagamentosValeTransporte, new PagamentoValeTransporteComparator());
		
		List<AquisicaoValeTransporteHelper> aquisicoes = new ArrayList<AquisicaoValeTransporteHelper>();
		AquisicaoValeTransporteHelper aquisicao = null;
		for (PagamentoValeTransporte pagamento : pagamentosValeTransporte) {
			if (aquisicao == null || aquisicao.getTipoValeTransporte() == null
					|| !aquisicao.getTipoValeTransporte().getNome().equals(pagamento.getTipoValeTransporte().getNome())) {
				aquisicao = new AquisicaoValeTransporteHelper();
				aquisicoes.add(aquisicao);
				aquisicao.setTipoValeTransporte(pagamento.getTipoValeTransporte());
			}
			aquisicao.setValorTotal(aquisicao.getValorTotal().add(pagamento.getTipoValeTransporte().getValor()));
			aquisicao.setQuantidade(aquisicao.getQuantidade() + 1);
		}
		this.aquisicoesValeTransporteHelper = aquisicoes;
		return this.aquisicoesValeTransporteHelper;
	}

	public String getReferencia(){
		String[] mesAno = this.mesAno.split("/");
		MesEnum[] meses = MesEnum.values();
		for(MesEnum mesEnum : meses){
			if(mesEnum.getCodigo().toString().equals(mesAno[0])){
				return mesEnum.getMes() + "-" + mesAno[1];
			}
		}
		return "";
	}

	public void somarPagamentos(){
		valorTotal = BigDecimal.ZERO;
		if(pagamentosValeTransporte != null && !pagamentosValeTransporte.isEmpty()){
			for(PagamentoValeTransporte pagamentoValeTransporte :pagamentosValeTransporte){
				this.valorTotal = this.valorTotal.add(pagamentoValeTransporte.getTipoValeTransporte().getValor());
			}
			this.quantidadeTotal = pagamentosValeTransporte.size();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataRecibo == null) ? 0 : dataRecibo.hashCode());
		result = prime * result	+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mesAno == null) ? 0 : mesAno.hashCode());
		result = prime * result
				+ ((quantidadeTotal == null) ? 0 : quantidadeTotal.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((tipoCalculoEnum == null) ? 0 : tipoCalculoEnum.hashCode());
		result = prime * result
				+ ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
		if (!(obj instanceof CalculoValeTransporte)) {
			return false;
		}
		CalculoValeTransporte other = (CalculoValeTransporte) obj;
		if (dataFim == null) {
			if (other.dataFim != null) {
				return false;
			}
		} else if (!dataFim.equals(other.dataFim)) {
			return false;
		}
		if (dataRecibo == null) {
			if (other.dataRecibo != null) {
				return false;
			}
		} else if (!dataRecibo.equals(other.dataRecibo)) {
			return false;
		}
		if (dataInicio == null) {
			if (other.dataInicio != null) {
				return false;
			}
		} else if (!dataInicio.equals(other.dataInicio)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mesAno == null) {
			if (other.mesAno != null) {
				return false;
			}
		} else if (!mesAno.equals(other.mesAno)) {
			return false;
		}
		if (quantidadeTotal == null) {
			if (other.quantidadeTotal != null) {
				return false;
			}
		} else if (!quantidadeTotal.equals(other.quantidadeTotal)) {
			return false;
		}
		if (situacao != other.situacao) {
			return false;
		}
		if (tipoCalculoEnum != other.tipoCalculoEnum) {
			return false;
		}
		if (valorTotal == null) {
			if (other.valorTotal != null) {
				return false;
			}
		} else if (!valorTotal.equals(other.valorTotal)) {
			return false;
		}
		return true;
	}

	@Override
	public CalculoValeTransporte clone() throws CloneNotSupportedException {
		return (CalculoValeTransporte)super.clone();
	}
}