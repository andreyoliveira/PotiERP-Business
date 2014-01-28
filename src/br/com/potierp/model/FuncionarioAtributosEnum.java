package br.com.potierp.model;


public enum FuncionarioAtributosEnum {
 
	RE("Re", "codigoRegistro", 1),
	
	NOME("Nome", "nome", 2),
	
	DATANASCIMENTO("Data de Nascimento", "dataNascimento", 3),
	
	SEXO("Sexo", "sexo", 4),
	
	EMAIL("E-mail", "email", 5),
	
	NOMEPAI("Nome do Pai", "nomePai", 6),
	
	NOMEMAE("Nome da Mãe", "nomeMae", 7),
	
	NOMECONJUGE("Nome do Conjuge", "nomeConjuge", 8),
	
	DATANASCIMENTOCONJUGE("Data de Nascimento do Conjuge", "dataNascimentoConjuge", 9),
	
	SALARIO("Salário", "salario", 10),
	
	DATAEXAMEMEDICO("Data Exame Médico", "dataExameMedico", 11);

	/*private Date dataExameMedico;
	
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
	
	private Boolean isPrimeiroEmprego;*/
	
	private String nomeColuna;
	
	private String nomeAtributo;
	
	private Integer codigoColuna;
	
	private FuncionarioAtributosEnum(final String nomeColuna, final String nomeAtributo, final Integer codigoColuna) {
		this.nomeColuna = nomeColuna;
		this.nomeAtributo = nomeAtributo;
		this.codigoColuna = codigoColuna;
	}

	/**
	 * @return the nomeColuna
	 */
	public String getNomeColuna() {
		return nomeColuna;
	}
	
	/**
	 * @return the nomeAtributo
	 */
	public String getNomeAtributo() {
		return nomeAtributo;
	}

	/**
	 * @return the codigoColuna
	 */
	public Integer getCodigoColuna() {
		return codigoColuna;
	}
}