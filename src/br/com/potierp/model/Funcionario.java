package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;

import br.com.potierp.auditoria.Auditavel;
import br.com.potierp.auditoria.AuditoriaBuilder;
import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author renan.rodrigues
 *
 */
@NamedQueries(
		{
		@NamedQuery(name = Funcionario.GET_ALL,
				    query = "FROM Funcionario f " +
				    		"LEFT JOIN fetch f.locaisTrabalho l " +
				    		"ORDER BY f.codigoRegistro "),
		@NamedQuery(name = Funcionario.COUNT_ALL,
					query = "SELECT COUNT(f.codigoRegistro) from Funcionario f"),				    		
	    @NamedQuery(name = Funcionario.GET_BY_RE, 
					query = "FROM Funcionario f " +
					    "WHERE f.codigoRegistro = :re " +
						"ORDER BY f.codigoRegistro "),
	    @NamedQuery(name = Funcionario.GET_APTOS_VALETRANSPORTE,
					query = "select distinct f FROM Funcionario f " +
							"inner join f.situacaoFuncionario sf " +
							"inner join f.valesTransporte vt " +
					    	"WHERE sf.codigo = 1 " +
					    	"and vt is not null"),
		@NamedQuery(name = Funcionario.GET_APTOS_VALEREFEICAO,
					query = "select distinct f FROM Funcionario f " +
							"inner join f.situacaoFuncionario sf " +
							"inner join f.valesRefeicao vr " +
					    	"WHERE sf.codigo = 1 " +
					    	"and vr is not null"),
		@NamedQuery(name = Funcionario.GET_BY_DATA_ADMISSAO,
					query = "select f FROM Funcionario f " +
							"inner join f.pessoa p " +
							"inner join f.situacaoFuncionario sf " +
							"WHERE f.dataAdmissao between :dataInicial and :dataFinal " +
							" and sf.codigo = 1 " +
							"ORDER BY f.dataAdmissao "),
		@NamedQuery(name = Funcionario.GET_POR_NOME,
					query = "select f FROM Funcionario f " +
							"inner join f.pessoa p " +
							"WHERE Upper(p.nome) like Upper(:nome) ")
		}
)

@Entity
@Table(name="funcionario")
public class Funcionario extends BaseEntityPotiErp implements Auditavel, Cloneable{

	private static final long serialVersionUID = -6766418533242823598L;
	
	/**
	 * NQ para buscar todos os Funcionarios.
	 */
	public static final String GET_ALL = "Funcionario.getAll";
	
	/**
	 * NQ para buscar o total de Funcionarios.
	 */
	public static final String COUNT_ALL = "Funcionario.countAll";
	
	/**
	 * NQ para buscar o total de Funcionarios por id Cliente.
	 */
	public static final String GET_COUNT_BY_CLIENTE = "Funcionario.countByCliente";
	
	/**
	 * NQ para buscar Funcionarios de acordo com o re. 
	 */
	public static final String GET_BY_RE = "Funcionario.getByRe";
	
	/**
	 * NQ para buscar Funcionarios pela data de admissao.
	 */
	public static final String GET_BY_DATA_ADMISSAO = "Funcionario.getByDataAdmissao";
	
	/**
	 * NQ para buscar Funcionarios de acordo com o id do Cliente. 
	 */
	public static final String GET_BY_CLIENTE = "Funcionario.getByIdCliente";
	
	public static final String GET_APTOS_VALETRANSPORTE = "Funcionario.getAptosValeTransporte";
	
	public static final String GET_APTOS_VALEREFEICAO = "Funcionario.getAptosValeRefeicao";
	
	/**
	 * NQ para buscar Funcionarios por nome.
	 */
	public static final String GET_POR_NOME = "Funcionario.getPorNome";

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;
	
	private Long codigoRegistro;
	
	private String nomePai;
	
	private String nomeMae;
	
	private String nomeConjuge;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimentoConjuge;
	
	private BigDecimal salario;
	
	@Temporal(TemporalType.DATE)
	private Date dataExameMedico;
	
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivilEnum estadoCivil;
	
	private String naturalidade;
	
	private String ufNaturalidade;
	
	private String nascionalidade;
	
	@Enumerated(EnumType.STRING)
	private TipoContratoEnum tipoContrato;
	
	@Embedded
	private Rg rg;
	
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private EscolaridadeEnum escolaridade;
	
	@Embedded
	private Ctps ctps;
	
	@Embedded
	private Pis pis;
	
	@Embedded
	private CarteiraHabilitacao carteiraHabilitacao;
	
	@Embedded
	private DocumentoMilitar documentoMilitar;
	
	@Enumerated(EnumType.STRING)
	private CertificadoReservistaEnum certificadoReservista;
	
	@Embedded
	private TituloEleitor tituloEleitor;
	
	@Embedded
	private SituacaoFgts situacaoFgts;
	
	@Embedded
	private CtpsEstrangeiro ctpsEstrangeiro;
	
	@Embedded
	private RneEstrangeiro rneEstrangeiro;
	
	private Boolean naturalizado;
	
	private Boolean deficienteFisico;
	
	@Temporal(TemporalType.DATE)
	private Date dataNaturalizacao;
	
	private Boolean adicionalNoturno;
	
	private Boolean adicionalPericulosidade;
	
	private Boolean adicionalInsalubridade;
	
	@Embedded
	private ConselhoRegional conselhoRegional;
	
	@Embedded
	private ContaBancaria contaBancaria;
	
	@ManyToOne
	@JoinColumn(name = "idFormaPagamento")
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy="funcionario",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<LocalTrabalho> locaisTrabalho;
	
	@OneToMany(mappedBy="funcionario",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<Dependente> dependentes;
	
	@OneToMany(mappedBy="funcionario",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<ValeTransporte> valesTransporte;
	
	@OneToMany(mappedBy="funcionario",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<ValeRefeicao> valesRefeicao;
	
	@OneToOne(mappedBy="funcionario",
		       fetch=FetchType.LAZY,
		       cascade=CascadeType.ALL)
	private CestaBasica cestaBasica;

	private Long horasSemanais;

	private Long horasMensais;
	
	@ManyToOne
	@JoinColumn(name="idCargo")
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name="idTipoAdmissao")
	private TipoAdmissao tipoAdmissao;
	
	@ManyToOne
	@JoinColumn(name="idVinculoEmpregaticio")
	private VinculoEmpregaticio vinculoEmpregaticio;
	
	@ManyToOne
	@JoinColumn(name="idSituacaoFuncionario")
	private SituacaoFuncionario situacaoFuncionario;
	
	private String indicacao;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	
	@Enumerated(EnumType.STRING)
	private RacaEnum raca;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneResidencial")
	private Telefone telefoneResidencial;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneCelular")
	private Telefone telefoneCelular;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneRecado1")
	private Telefone telefoneRecado1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneCelularRecado1")
	private Telefone telefoneCelularRecado1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneRecado2")
	private Telefone telefoneRecado2;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefoneCelularRecado2")
	private Telefone telefoneCelularRecado2;
	
	private Boolean isPrimeiroEmprego;
	
	@Version
	private Long version;
	
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

	public Long getCodigoRegistro() {
		if(Long.valueOf(0).equals(codigoRegistro)){
			this.codigoRegistro = null;
		}
		return codigoRegistro;
	}

	public void setCodigoRegistro(final Long codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(final Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(final String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(final String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(final String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public Date getDataNascimentoConjuge() {
		return dataNascimentoConjuge;
	}

	public void setDataNascimentoConjuge(final Date dataNascimentoConjuge) {
		this.dataNascimentoConjuge = dataNascimentoConjuge;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(final BigDecimal salario) {
		this.salario = salario;
	}

	public Date getDataExameMedico() {
		return dataExameMedico;
	}

	public void setDataExameMedico(final Date dataExameMedico) {
		this.dataExameMedico = dataExameMedico;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(final Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(final EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Rg getRg() {
		return rg;
	}

	public void setRg(final Rg rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	public EscolaridadeEnum getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(final EscolaridadeEnum escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Ctps getCtps() {
		return ctps;
	}

	public void setCtps(final Ctps ctps) {
		this.ctps = ctps;
	}

	public Pis getPis() {
		return pis;
	}

	public void setPis(final Pis pis) {
		this.pis = pis;
	}

	public CarteiraHabilitacao getCarteiraHabilitacao() {
		return carteiraHabilitacao;
	}

	public void setCarteiraHabilitacao(final CarteiraHabilitacao carteiraHabilitacao) {
		this.carteiraHabilitacao = carteiraHabilitacao;
	}

	public DocumentoMilitar getDocumentoMilitar() {
		return documentoMilitar;
	}

	public void setDocumentoMilitar(final DocumentoMilitar documentoMilitar) {
		this.documentoMilitar = documentoMilitar;
	}

	public TituloEleitor getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(final TituloEleitor tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public CtpsEstrangeiro getCtpsEstrangeiro() {
		return ctpsEstrangeiro;
	}

	public void setCtpsEstrangeiro(final CtpsEstrangeiro ctpsEstrangeiro) {
		this.ctpsEstrangeiro = ctpsEstrangeiro;
	}

	public RneEstrangeiro getRneEstrangeiro() {
		return rneEstrangeiro;
	}

	public void setRneEstrangeiro(final RneEstrangeiro rneEstrangeiro) {
		this.rneEstrangeiro = rneEstrangeiro;
	}

	public SituacaoFgts getSituacaoFgts() {
		return situacaoFgts;
	}

	public void setSituacaoFgts(final SituacaoFgts situacaoFgts) {
		this.situacaoFgts = situacaoFgts;
	}

	public ConselhoRegional getConselhoRegional() {
		return conselhoRegional;
	}

	public void setConselhoRegional(final ConselhoRegional conselhoRegional) {
		this.conselhoRegional = conselhoRegional;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final Empresa empresa) {
		this.empresa = empresa;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(final FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getHorasSemanais() {
		if(Long.valueOf(0).equals(horasSemanais)){
			this.horasSemanais = null;
		}
		return horasSemanais;
	}

	public void setHorasSemanais(final Long horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	public Long getHorasMensais() {
		if(Long.valueOf(0).equals(horasMensais)){
			this.horasMensais = null;
		}
		return horasMensais;
	}

	public void setHorasMensais(final Long horasMensais) {
		this.horasMensais = horasMensais;
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(final String indicacao) {
		this.indicacao = indicacao;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(final String naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	public String getUfNaturalidade() {
		return ufNaturalidade;
	}

	public void setUfNaturalidade(final String ufNaturalidade) {
		this.ufNaturalidade = ufNaturalidade;
	}

	public String getNascionalidade() {
		return nascionalidade;
	}

	public void setNascionalidade(final String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(final Cargo cargo) {
		this.cargo = cargo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}
	
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(final ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public TipoContratoEnum getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(final TipoContratoEnum tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	public TipoAdmissao getTipoAdmissao() {
		return tipoAdmissao;
	}

	public void setTipoAdmissao(final TipoAdmissao tipoAdmissao) {
		this.tipoAdmissao = tipoAdmissao;
	}
	
	public VinculoEmpregaticio getVinculoEmpregaticio() {
		return vinculoEmpregaticio;
	}

	public void setVinculoEmpregaticio(final VinculoEmpregaticio vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}

	public SituacaoFuncionario getSituacaoFuncionario() {
		return situacaoFuncionario;
	}

	public void setSituacaoFuncionario(final SituacaoFuncionario situacaoFuncionario) {
		this.situacaoFuncionario = situacaoFuncionario;
	}
	
	public RacaEnum getRaca() {
		return raca;
	}

	public Boolean getNaturalizado() {
		return naturalizado;
	}

	public void setNaturalizado(final Boolean naturalizado) {
		this.naturalizado = naturalizado;
	}

	public Boolean getDeficienteFisico() {
		return deficienteFisico;
	}

	public void setDeficienteFisico(final Boolean deficienteFisico) {
		this.deficienteFisico = deficienteFisico;
	}
	
	/**
	 * @return the adicionalNoturno
	 */
	public Boolean getAdicionalNoturno() {
		return adicionalNoturno;
	}

	/**
	 * @param adicionalNoturno the adicionalNoturno to set
	 */
	public void setAdicionalNoturno(final Boolean adicionalNoturno) {
		this.adicionalNoturno = adicionalNoturno;
	}

	/**
	 * @return the adicionalPericulosidade
	 */
	public Boolean getAdicionalPericulosidade() {
		return adicionalPericulosidade;
	}

	/**
	 * @param adicionalPericulosidade the adicionalPericulosidade to set
	 */
	public void setAdicionalPericulosidade(final Boolean adicionalPericulosidade) {
		this.adicionalPericulosidade = adicionalPericulosidade;
	}

	/**
	 * @return the adicionalInsalubridade
	 */
	public Boolean getAdicionalInsalubridade() {
		return adicionalInsalubridade;
	}

	/**
	 * @param adicionalInsalubridade the adicionalInsalubridade to set
	 */
	public void setAdicionalInsalubridade(final Boolean adicionalInsalubridade) {
		this.adicionalInsalubridade = adicionalInsalubridade;
	}

	public Date getDataNaturalizacao() {
		return dataNaturalizacao;
	}

	public void setDataNaturalizacao(final Date dataNaturalizacao) {
		this.dataNaturalizacao = dataNaturalizacao;
	}

	public void setRaca(final RacaEnum raca) {
		this.raca = raca;
	}

	public List<LocalTrabalho> getLocaisTrabalho() {
		return locaisTrabalho;
	}

	public void setLocaisTrabalho(final List<LocalTrabalho> locaisTrabalho) {
		this.locaisTrabalho = locaisTrabalho;
	}

	public Collection<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(final Collection<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Collection<ValeTransporte> getValesTransporte() {
		return valesTransporte;
	}

	public void setValesTransporte(final Collection<ValeTransporte> valesTransporte) {
		this.valesTransporte = valesTransporte;
	}
	
	public Collection<ValeRefeicao> getValesRefeicao() {
		return valesRefeicao;
	}

	public void setValesRefeicao(final Collection<ValeRefeicao> valesRefeicao) {
		this.valesRefeicao = valesRefeicao;
	}
	
	/**
	 * @return the cestaBasica
	 */
	public CestaBasica getCestaBasica() {
		return cestaBasica;
	}

	/**
	 * @param cestaBasica the cestaBasica to set
	 */
	public void setCestaBasica(final CestaBasica cestaBasica) {
		this.cestaBasica = cestaBasica;
	}

	public Telefone getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(final Telefone telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public Telefone getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(final Telefone telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Telefone getTelefoneRecado1() {
		return telefoneRecado1;
	}

	public void setTelefoneRecado1(final Telefone telefoneRecado1) {
		this.telefoneRecado1 = telefoneRecado1;
	}

	public Telefone getTelefoneCelularRecado1() {
		return telefoneCelularRecado1;
	}

	public void setTelefoneCelularRecado1(final Telefone telefoneCelularRecado1) {
		this.telefoneCelularRecado1 = telefoneCelularRecado1;
	}

	public Telefone getTelefoneRecado2() {
		return telefoneRecado2;
	}

	public void setTelefoneRecado2(final Telefone telefoneRecado2) {
		this.telefoneRecado2 = telefoneRecado2;
	}

	public Telefone getTelefoneCelularRecado2() {
		return telefoneCelularRecado2;
	}

	public void setTelefoneCelularRecado2(final Telefone telefoneCelularRecado2) {
		this.telefoneCelularRecado2 = telefoneCelularRecado2;
	}
	
	public CertificadoReservistaEnum getCertificadoReservista() {
		return certificadoReservista;
	}

	public void setCertificadoReservista(
			final CertificadoReservistaEnum certificadoReservista) {
		this.certificadoReservista = certificadoReservista;
	}

	public Boolean getIsPrimeiroEmprego() {
		return isPrimeiroEmprego;
	}

	public void setIsPrimeiroEmprego(final Boolean isPrimeiroEmprego) {
		this.isPrimeiroEmprego = isPrimeiroEmprego;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((codigoRegistro == null) ? 0 : codigoRegistro.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result	+ ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result	+ ((dataExameMedico == null) ? 0 : dataExameMedico.hashCode());
		result = prime * result + ((dataNascimentoConjuge == null) ? 0 : dataNascimentoConjuge.hashCode());
		result = prime * result	+ ((dataNaturalizacao == null) ? 0 : dataNaturalizacao.hashCode());
		result = prime * result	+ ((deficienteFisico == null) ? 0 : deficienteFisico.hashCode());
		result = prime * result	+ ((escolaridade == null) ? 0 : escolaridade.hashCode());
		result = prime * result	+ ((indicacao == null) ? 0 : indicacao.hashCode());
		result = prime * result	+ ((nascionalidade == null) ? 0 : nascionalidade.hashCode());
		result = prime * result	+ ((naturalidade == null) ? 0 : naturalidade.hashCode());
		result = prime * result	+ ((naturalizado == null) ? 0 : naturalizado.hashCode());
		result = prime * result	+ ((nomeConjuge == null) ? 0 : nomeConjuge.hashCode());
		result = prime * result + ((nomeMae == null) ? 0 : nomeMae.hashCode());
		result = prime * result + ((nomePai == null) ? 0 : nomePai.hashCode());
		result = prime * result	+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		result = prime * result + ((ufNaturalidade == null) ? 0 : ufNaturalidade.hashCode());
		result = prime * result + ((horasSemanais == null) ? 0 : horasSemanais.hashCode());
		result = prime * result + ((horasMensais == null) ? 0 : horasMensais.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario other = (Funcionario) obj;
		if (codigoRegistro == null) {
			if (other.codigoRegistro != null)
				return false;
		} else if (!codigoRegistro.equals(other.codigoRegistro))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (dataExameMedico == null) {
			if (other.dataExameMedico != null)
				return false;
		} else if (!dataExameMedico.equals(other.dataExameMedico))
			return false;
		if (dataNascimentoConjuge == null) {
			if (other.dataNascimentoConjuge != null)
				return false;
		} else if (!dataNascimentoConjuge.equals(other.dataNascimentoConjuge))
			return false;
		if (dataNaturalizacao == null) {
			if (other.dataNaturalizacao != null)
				return false;
		} else if (!dataNaturalizacao.equals(other.dataNaturalizacao))
			return false;
		if (deficienteFisico == null) {
			if (other.deficienteFisico != null)
				return false;
		} else if (!deficienteFisico.equals(other.deficienteFisico))
			return false;
		if (escolaridade != other.escolaridade)
			return false;
		if (indicacao == null) {
			if (other.indicacao != null)
				return false;
		} else if (!indicacao.equals(other.indicacao))
			return false;
		if (nascionalidade == null) {
			if (other.nascionalidade != null)
				return false;
		} else if (!nascionalidade.equals(other.nascionalidade))
			return false;
		if (naturalidade == null) {
			if (other.naturalidade != null)
				return false;
		} else if (!naturalidade.equals(other.naturalidade))
			return false;
		if (naturalizado == null) {
			if (other.naturalizado != null)
				return false;
		} else if (!naturalizado.equals(other.naturalizado))
			return false;
		if (nomeConjuge == null) {
			if (other.nomeConjuge != null)
				return false;
		} else if (!nomeConjuge.equals(other.nomeConjuge))
			return false;
		if (nomeMae == null) {
			if (other.nomeMae != null)
				return false;
		} else if (!nomeMae.equals(other.nomeMae))
			return false;
		if (nomePai == null) {
			if (other.nomePai != null)
				return false;
		} else if (!nomePai.equals(other.nomePai))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		if (ufNaturalidade == null) {
			if (other.ufNaturalidade != null)
				return false;
		} else if (!ufNaturalidade.equals(other.ufNaturalidade))
			return false;
		if (horasSemanais == null) {
			if (other.horasSemanais != null)
				return false;
		} else if (!horasSemanais.equals(other.horasSemanais))
			return false;
		if (horasMensais == null) {
			if (other.horasMensais != null)
				return false;
		} else if (!horasMensais.equals(other.horasMensais))
			return false;
		return true;
	}

	@Override
	public String auditoria() {
		return new AuditoriaBuilder()
		.append("ToString",this.toString()).toAuditoria();
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", codigoRegistro=" + codigoRegistro
				+ ", nomePai=" + nomePai + ", nomeMae=" + nomeMae
				+ ", nomeConjuge=" + nomeConjuge + ", dataNascimentoConjuge="
				+ dataNascimentoConjuge + ", salario=" + salario
				+ ", dataExameMedico=" + dataExameMedico + ", dataAdmissao="
				+ dataAdmissao + ", estadoCivil=" + estadoCivil
				+ ", naturalidade=" + naturalidade + ", ufNaturalidade="
				+ ufNaturalidade + ", nascionalidade=" + nascionalidade
				+ ", dataNaturalizacao=" + dataNaturalizacao
				+ ", horasSemanais=" + horasSemanais + ", horasMensais="
				+ horasMensais + ", cargo=" + cargo + ", indicacao="
				+ indicacao + ", observacao=" + observacao + "]";
	}

	@Override
	public String getTableName() {
		return "Funcionario";
	}

	@Override
	public Funcionario clone() throws CloneNotSupportedException {
		return (Funcionario)super.clone();
	}
}