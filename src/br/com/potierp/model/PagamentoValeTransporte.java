package br.com.potierp.model;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = PagamentoValeTransporte.GET_ALL,
				    query = "FROM PagamentoValeTransporte "),
		@NamedQuery(name = PagamentoValeTransporte.GET_POR_CALCULO,
				    query = "select p FROM PagamentoValeTransporte p " +
				    		"inner join p.calculoValeTransporte calc " +
				    		"inner join fetch p.funcionario func " +
				    		"inner join fetch p.tipoValeTransporte tvt " +
				    		"inner join fetch p.cliente cl " +
				    		"where calc.id = :idCalculo "),
		@NamedQuery(name = PagamentoValeTransporte.GET_RECIBO_VALE_TRANSPORTE,
					query = "select p.nome as nomeFuncionario, tvt.nome as onibus, pvt.rumoTransporte, tvt.valor as passagem, " +
							" count(*) as qtdVale " +
							"from PagamentoValeTransporte pvt " +
							"inner join pvt.funcionario f " +
							"inner join pvt.tipoValeTransporte tvt " +
							"inner join f.pessoa p " +
							"where pvt.dataValeTransporte between :dataInicial and :dataFinal " +
							"group by p.nome, tvt.nome, pvt.rumoTransporte, tvt.valor")
		}
)

@Entity
@Table(name="pagamentovaletransporte")
public class PagamentoValeTransporte extends BaseEntityPotiErp implements Cloneable, Comparable<PagamentoValeTransporte>{

	private static final long serialVersionUID = -8101089240880683457L;

	/**
	 * NQ para buscar todos os Pagamentos ValeTransporte.
	 */
	public static final String GET_ALL = "PagamentoValeTransporte.getAll";
	
	/**
	 * NQ para buscar os recibos de vale transporte.
	 */
	public static final String GET_RECIBO_VALE_TRANSPORTE = "PagamentoValeTransporte.getReciboValeTransporte";
	
	public static final String GET_POR_CALCULO = "PagamentoValeTransporte.getPorCalculo";
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCalculoValeTransporte")
	private CalculoValeTransporte calculoValeTransporte;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoValeTransporte")
	private TipoValeTransporte tipoValeTransporte;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private RumoTransporteEnum rumoTransporte;
	
	@Temporal(TemporalType.DATE)
	private Date dataValeTransporte;
	
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
	 * @return the calculoValeTransporte
	 */
	public CalculoValeTransporte getCalculoValeTransporte() {
		return calculoValeTransporte;
	}

	/**
	 * @param calculoValeTransporte the calculoValeTransporte to set
	 */
	public void setCalculoValeTransporte(final CalculoValeTransporte calculoValeTransporte) {
		this.calculoValeTransporte = calculoValeTransporte;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoValeTransporte getTipoValeTransporte() {
		return tipoValeTransporte;
	}

	public void setTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) {
		this.tipoValeTransporte = tipoValeTransporte;
	}

	public RumoTransporteEnum getRumoTransporte() {
		return rumoTransporte;
	}

	public void setRumoTransporte(final RumoTransporteEnum rumoTransporte) {
		this.rumoTransporte = rumoTransporte;
	}

	public Date getDataValeTransporte() {
		return dataValeTransporte;
	}

	public void setDataValeTransporte(final Date dataValeTransporte) {
		this.dataValeTransporte = dataValeTransporte;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataValeTransporte == null) ? 0 : dataValeTransporte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((rumoTransporte == null) ? 0 : rumoTransporte.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PagamentoValeTransporte)) {
			return false;
		}
		PagamentoValeTransporte other = (PagamentoValeTransporte) obj;
		if (dataValeTransporte == null) {
			if (other.dataValeTransporte != null) {
				return false;
			}
		} else if (!dataValeTransporte.equals(other.dataValeTransporte)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (rumoTransporte != other.rumoTransporte) {
			return false;
		}
		return true;
	}
	
	@Override
	public PagamentoValeTransporte clone() throws CloneNotSupportedException {
		return (PagamentoValeTransporte)super.clone();
	}
	
	@Override
	public int compareTo(final PagamentoValeTransporte outro) {
		
		int func = this.funcionario.getPessoa().compareTo(outro.getFuncionario().getPessoa());
		if(func == 0){
			int tipoVale = this.tipoValeTransporte.compareTo(outro.tipoValeTransporte);
			if(tipoVale == 0){
				int rumo = StringUtils.compareIgnoreAccentsAndCase(this.rumoTransporte.getRumo(), outro.rumoTransporte.getRumo());
				if(rumo == 0){
					return StringUtils.compareIgnoreAccentsAndCase(this.cliente.getNomeFantasia(), outro.getCliente().getNomeFantasia());
				}
				return rumo;
			}
			return tipoVale;
		}
		return func;
	}
}