package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries({
		@NamedQuery(name = Cliente.GET_ALL, 
					query = "FROM Cliente "),
		@NamedQuery(name = Cliente.COUNT_ALL,
					query = "SELECT COUNT(c) from Cliente c"),
		@NamedQuery(name = Cliente.GET_BY_CPF_CNPJ, 
					query = "FROM Cliente c " +
						    "WHERE c.cpfCnpj = :cpfCnpj "), 
		@NamedQuery(name = Cliente.GET_BY_CODIGO, 
					query = "FROM Cliente c " +
							"WHERE c.codigo = :codigo "),
		@NamedQuery(name = Cliente.GET_ATIVOS, 
					query = "FROM Cliente c " +
							"WHERE c.dataFinalContrato is null or " +
							":data between c.dataInicioContrato and c.dataFinalContrato "),
		@NamedQuery(name = Cliente.GET_INATIVOS, 
					query = "FROM Cliente c " +
							"WHERE c.dataFinalContrato is not null and " +
							"c.dataFinalContrato < :data "),
		@NamedQuery(name = Cliente.GET_COUNT_ATIVOS, 
					query = "SELECT COUNT(c) FROM Cliente c " +
						"WHERE c.dataFinalContrato is null or " +
						":data between c.dataInicioContrato and c.dataFinalContrato "),
		@NamedQuery(name = Cliente.GET_COUNT_INATIVOS, 
					query = "SELECT COUNT(c) FROM Cliente c " +
							"WHERE c.dataFinalContrato is not null and " +
							"c.dataFinalContrato < :data ")
		})
@Entity
@Table(name="cliente")
public class Cliente extends BaseEntityPotiErp implements Comparable<Cliente>, Cloneable{

	private static final long serialVersionUID = -1505831407555172536L;

	/**
	 * NQ para buscar todos os Clientes.
	 */
	public static final String GET_ALL = "Cliente.getAll";
	
	/**
	 * NQ para buscar o total de Clientes.
	 */
	public static final String COUNT_ALL = "Cliente.countAll";
	
	/**
	 * NQ para buscar o total de Clientes Ativos.
	 */
	public static final String GET_COUNT_ATIVOS = "Cliente.getCountAtivos";
	
	/**
	 * NQ para buscar o total de Clientes Inativos.
	 */
	public static final String GET_COUNT_INATIVOS = "Cliente.getCountInativos";

	/**
	 * NQ para buscar Cliente de acordo com um Cpr/CNPj.
	 */
	public static final String GET_BY_CPF_CNPJ = "Cliente.getByCpfCnpj";
	
	/**
	 * NQ para buscar Cliente de acordo com o Código.
	 */
	public static final String GET_BY_CODIGO = "Cliente.getByCodigo";
	
	/**
	 * NQ para buscar Cliente Ativos.
	 */
	public static final String GET_ATIVOS = "Cliente.getAtivos";
	
	/**
	 * NQ para buscar Cliente Inativos.
	 */
	public static final String GET_INATIVOS = "Cliente.getInativos";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String codigo;

	@NotNull
	private String razaoSocial;

	@NotNull
	private String nomeFantasia;
	
	@NotNull
	private String tipoDocumento;
	
	@NotNull
	private String cpfCnpj;

	@NotNull
	private String tipoAtividade;

	@NotNull
	@Embedded
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;

	private Date dataInicioContrato;

	private Date dataFinalContrato;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContato1")
	private Pessoa contato1;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContato2")
	private Pessoa contato2;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTelefone1")
	private Telefone telefone1;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTelefone2")
	private Telefone telefone2;

	private String observacao;

	private Integer diaPagamento;
	
	private BigDecimal valorContrato;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "idMatriz")
	private Cliente matriz;
	
	@ManyToOne
	@JoinColumn(name = "idPeriodicidade")
	private Periodicidade periodicidade;

	@OneToMany (fetch=FetchType.LAZY)
	@JoinTable(name = "clientematriz_clientefilial", 
			   joinColumns = @JoinColumn(name = "idMatriz"), 
			   inverseJoinColumns = @JoinColumn(name = "idFilial"))
	private List<Cliente> filiais;
	
	@ManyToMany (fetch=FetchType.LAZY)
	@JoinTable(name ="cliente_setor",
			joinColumns = @JoinColumn(name="idCliente"),
			inverseJoinColumns = @JoinColumn(name="idSetor"))
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<Setor> setores;

	@ManyToMany (fetch=FetchType.LAZY)
	@JoinTable(name ="cliente_jornadatrabalho",
			joinColumns = @JoinColumn(name="idCliente"),
			inverseJoinColumns = @JoinColumn(name="idJornadaTrabalho"))
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<JornadaTrabalho> jornadasTrabalho;
	
	@OneToMany(mappedBy="cliente",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<ContratoCliente> contratosCliente;
	
	@OneToMany(mappedBy="cliente",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<ReajusteCliente> reajustesCliente;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;
	
	private Boolean isTrabalhaFeriado;
	
	private Boolean isSabadoDiaUtil;
	
	private Boolean isDomingoDiaUtil;

	@Override
	public Long getId() {
		if (Long.valueOf(0).equals(id)) {
			this.id = null;
		}
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(final String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(final String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(final Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(final Cidade cidade) {
		this.cidade = cidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getMatriz() {
		return matriz;
	}

	public void setMatriz(final Cliente matriz) {
		this.matriz = matriz;
	}

	public List<Cliente> getFiliais() {
		return filiais;
	}

	public void setFiliais(final List<Cliente> filiais) {
		this.filiais = filiais;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(final String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(final Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataFinalContrato() {
		return dataFinalContrato;
	}

	public void setDataFinalContrato(final Date dataFinalContrato) {
		this.dataFinalContrato = dataFinalContrato;
	}

	public Pessoa getContato1() {
		return contato1;
	}

	public void setContato1(final Pessoa contato1) {
		this.contato1 = contato1;
	}

	public Pessoa getContato2() {
		return contato2;
	}

	public void setContato2(final Pessoa contato2) {
		this.contato2 = contato2;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public Integer getDiaPagamento() {
		if(Integer.valueOf(0).equals(diaPagamento)){
			this.diaPagamento = null;
		}
		return diaPagamento;
	}

	public void setDiaPagamento(final Integer diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public Telefone getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(final Telefone telefone1) {
		this.telefone1 = telefone1;
	}

	public Telefone getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(final Telefone telefone2) {
		this.telefone2 = telefone2;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(final String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(final SituacaoEnum situacao) {
		this.situacao = situacao;
	}
	
	public Boolean getIsTrabalhaFeriado() {
		return isTrabalhaFeriado;
	}

	public void setIsTrabalhaFeriado(final Boolean isTrabalhaFeriado) {
		this.isTrabalhaFeriado = isTrabalhaFeriado;
	}
	
	public Boolean getIsSabadoDiaUtil() {
		return isSabadoDiaUtil;
	}

	public void setIsSabadoDiaUtil(final Boolean isSabadoDiaUtil) {
		this.isSabadoDiaUtil = isSabadoDiaUtil;
	}

	public Boolean getIsDomingoDiaUtil() {
		return isDomingoDiaUtil;
	}

	public void setIsDomingoDiaUtil(final Boolean isDomingoDiaUtil) {
		this.isDomingoDiaUtil = isDomingoDiaUtil;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(final List<Setor> setores) {
		this.setores = setores;
	}
	
	public List<JornadaTrabalho> getJornadasTrabalho() {
		return jornadasTrabalho;
	}

	public void setJornadasTrabalho(final List<JornadaTrabalho> jornadasTrabalho) {
		this.jornadasTrabalho = jornadasTrabalho;
	}
	
	/**
	 * @return the periodicidade
	 */
	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}
	
	/**
	 * @param periodicidade the periodicidade to set
	 */
	public void setPeriodicidade(final Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}
	
	public boolean isPeriodicidadeSemanal() {
		return this.periodicidade.getNome().equalsIgnoreCase("Semanal"); 
	}
	
	public boolean isPeriodicidadeQuinzenal() {
		return this.periodicidade.getNome().equalsIgnoreCase("Quinzenal"); 
	}
	
	public boolean isPeriodicidadeMensal() {
		return this.periodicidade.getNome().equalsIgnoreCase("Mensal"); 
	}
	
	/**
	 * @return the contratosCliente
	 */
	public List<ContratoCliente> getContratosCliente() {
		return contratosCliente;
	}

	/**
	 * @param contratosCliente the contratosCliente to set
	 */
	public void setContratosCliente(final List<ContratoCliente> contratosCliente) {
		this.contratosCliente = contratosCliente;
	}
	
	/**
	 * @return the valorContrato
	 */
	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	/**
	 * @param valorContrato the valorContrato to set
	 */
	public void setValorContrato(final BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}

	/**
	 * @return the reajustesCliente
	 */
	public List<ReajusteCliente> getReajustesCliente() {
		return reajustesCliente;
	}

	/**
	 * @param reajustesCliente the reajustesCliente to set
	 */
	public void setReajustesCliente(final List<ReajusteCliente> reajustesCliente) {
		this.reajustesCliente = reajustesCliente;
	}
	
	public void atualizarValorContrato(){
		Date dataAtual = null;
		for(ReajusteCliente reajuste : reajustesCliente){
			if(dataAtual == null || reajuste.getDataReajuste().compareTo(dataAtual) == 1){
				dataAtual = reajuste.getDataReajuste();
				valorContrato = reajuste.getValorReajustado();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime
				* result
				+ ((dataFinalContrato == null) ? 0 : dataFinalContrato
						.hashCode());
		result = prime
				* result
				+ ((dataInicioContrato == null) ? 0 : dataInicioContrato
						.hashCode());
		result = prime * result
				+ ((diaPagamento == null) ? 0 : diaPagamento.hashCode());
		result = prime
				* result
				+ ((isTrabalhaFeriado == null) ? 0 : isTrabalhaFeriado
						.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
		result = prime * result
				+ ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		result = prime * result
				+ ((valorContrato == null) ? 0 : valorContrato.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (dataFinalContrato == null) {
			if (other.dataFinalContrato != null)
				return false;
		} else if (!dataFinalContrato.equals(other.dataFinalContrato))
			return false;
		if (dataInicioContrato == null) {
			if (other.dataInicioContrato != null)
				return false;
		} else if (!dataInicioContrato.equals(other.dataInicioContrato))
			return false;
		if (diaPagamento == null) {
			if (other.diaPagamento != null)
				return false;
		} else if (!diaPagamento.equals(other.diaPagamento))
			return false;
		if (isTrabalhaFeriado == null) {
			if (other.isTrabalhaFeriado != null)
				return false;
		} else if (!isTrabalhaFeriado.equals(other.isTrabalhaFeriado))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (situacao != other.situacao)
			return false;
		if (tipoAtividade == null) {
			if (other.tipoAtividade != null)
				return false;
		} else if (!tipoAtividade.equals(other.tipoAtividade))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		if (valorContrato == null) {
			if (other.valorContrato != null)
				return false;
		} else if (!valorContrato.equals(other.valorContrato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", codigo=" + codigo + ", razaoSocial="
				+ razaoSocial + ", nomeFantasia=" + nomeFantasia
				+ ", tipoDocumento=" + tipoDocumento + ", cpfCnpj=" + cpfCnpj
				+ ", tipoAtividade=" + tipoAtividade + ", endereco=" + endereco
				+ ", cidade=" + cidade + ", dataInicioContrato="
				+ dataInicioContrato + ", dataFinalContrato="
				+ dataFinalContrato + ", contato1=" + contato1 + ", contato2="
				+ contato2 + ", telefone1=" + telefone1 + ", telefone2="
				+ telefone2 + ", observacao=" + observacao + ", diaPagamento="
				+ diaPagamento + ", empresa=" + empresa + ", matriz=" + matriz
				+ ", filiais=" + filiais + ", setores=" + setores
				+ ", situacao=" + situacao + ", isTrabalhaFeriado="
				+ isTrabalhaFeriado + "]";
	}

	@Override
	public Cliente clone() throws CloneNotSupportedException {
		return (Cliente)super.clone();
	}

	@Override
	public int compareTo(final Cliente outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.getCodigo(), outro.getCodigo());
	}
}