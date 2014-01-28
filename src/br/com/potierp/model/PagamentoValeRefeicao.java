package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
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

@NamedQueries(
		{
		@NamedQuery(name = PagamentoValeRefeicao.GET_ALL,
				    query = "FROM PagamentoValeRefeicao "),
		@NamedQuery(name = PagamentoValeRefeicao.GET_POR_CALCULO,
				    query = "select p FROM PagamentoValeRefeicao p " +
				    		"inner join p.calculoValeRefeicao calc " +
				    		"inner join fetch p.funcionario func " +
				    		"inner join fetch p.tipoValeRefeicao tvt " +
				    		"where calc.id = :idCalculo "),
		@NamedQuery(name = PagamentoValeRefeicao.GET_RECIBO_VALE_REFEICAO,
					query = "select p.nome as nomeFuncionario, tvr.nome as nomeVale, tvr.valor as valor, " +
							" count(*) as qtdVale " +
							"from PagamentoValeRefeicao pvr " +
							"inner join pvr.funcionario f " +
							"inner join pvr.tipoValeRefeicao tvr " +
							"inner join f.pessoa p " +
							"where pvr.dataValeRefeicao between :dataInicial and :dataFinal " +
							"group by p.nome, tvr.nome, tvr.valor")
		}
)

@Entity
@Table(name="pagamentovalerefeicao")
public class PagamentoValeRefeicao extends BaseEntityPotiErp implements Cloneable, Comparable<PagamentoValeRefeicao>{

	private static final long serialVersionUID = -8101089240880683457L;

	/**
	 * NQ para buscar todos os Pagamentos ValeRefeicao.
	 */
	public static final String GET_ALL = "PagamentoValeRefeicao.getAll";
	
	/**
	 * NQ para buscar os recibos de vale Refeicao.
	 */
	public static final String GET_RECIBO_VALE_REFEICAO = "PagamentoValeRefeicao.getReciboValeRefeicao";
	
	public static final String GET_POR_CALCULO = "PagamentoValeRefeicao.getPorCalculo";
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCalculoValeRefeicao")
	private CalculoValeRefeicao calculoValeRefeicao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoValeRefeicao")
	private TipoValeRefeicao tipoValeRefeicao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
	@Temporal(TemporalType.DATE)
	private Date dataValeRefeicao;
	
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
	 * @return the calculoValeRefeicao
	 */
	public CalculoValeRefeicao getCalculoValeRefeicao() {
		return calculoValeRefeicao;
	}

	/**
	 * @param calculoValeRefeicao the calculoValeRefeicao to set
	 */
	public void setCalculoValeRefeicao(final CalculoValeRefeicao calculoValeRefeicao) {
		this.calculoValeRefeicao = calculoValeRefeicao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoValeRefeicao getTipoValeRefeicao() {
		return tipoValeRefeicao;
	}

	public void setTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) {
		this.tipoValeRefeicao = tipoValeRefeicao;
	}

	public Date getDataValeRefeicao() {
		return dataValeRefeicao;
	}

	public void setDataValeRefeicao(final Date dataValeRefeicao) {
		this.dataValeRefeicao = dataValeRefeicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataValeRefeicao == null) ? 0 : dataValeRefeicao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof PagamentoValeRefeicao)) {
			return false;
		}
		PagamentoValeRefeicao other = (PagamentoValeRefeicao) obj;
		if (dataValeRefeicao == null) {
			if (other.dataValeRefeicao != null) {
				return false;
			}
		} else if (!dataValeRefeicao.equals(other.dataValeRefeicao)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	public PagamentoValeRefeicao clone() throws CloneNotSupportedException {
		return (PagamentoValeRefeicao)super.clone();
	}
	
	@Override
	public int compareTo(final PagamentoValeRefeicao outro) {
		int func = this.funcionario.getPessoa().compareTo(outro.getFuncionario().getPessoa());
		if(func == 0){
			return this.tipoValeRefeicao.compareTo(outro.tipoValeRefeicao);
		}
		return func;
	}
}