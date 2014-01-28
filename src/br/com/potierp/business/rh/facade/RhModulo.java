package br.com.potierp.business.rh.facade;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.log.TraceInfo;
import br.com.potierp.model.Adicional;
import br.com.potierp.model.Afastamento;
import br.com.potierp.model.Agencia;
import br.com.potierp.model.AlteracaoCliente;
import br.com.potierp.model.AlteracaoSalarial;
import br.com.potierp.model.Banco;
import br.com.potierp.model.Beneficio;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.Cargo;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Demissao;
import br.com.potierp.model.Dependente;
import br.com.potierp.model.Desconto;
import br.com.potierp.model.Empresa;
import br.com.potierp.model.Encargo;
import br.com.potierp.model.EnderecoRepositorio;
import br.com.potierp.model.Feriado;
import br.com.potierp.model.Ferias;
import br.com.potierp.model.FormaPagamento;
import br.com.potierp.model.Fornecedor;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.GrauParentesco;
import br.com.potierp.model.HistoricoDemissao;
import br.com.potierp.model.HorariosJornada;
import br.com.potierp.model.JornadaTrabalho;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.model.MedidaDisciplinar;
import br.com.potierp.model.PagamentoValeRefeicao;
import br.com.potierp.model.PagamentoValeTransporte;
import br.com.potierp.model.ParametrosRh;
import br.com.potierp.model.Periodicidade;
import br.com.potierp.model.Responsavel;
import br.com.potierp.model.Setor;
import br.com.potierp.model.SituacaoFuncionario;
import br.com.potierp.model.TipoAdmissao;
import br.com.potierp.model.TipoCestaBasica;
import br.com.potierp.model.TipoDemissao;
import br.com.potierp.model.TipoValeRefeicao;
import br.com.potierp.model.TipoValeTransporte;
import br.com.potierp.model.Verba;
import br.com.potierp.model.VinculoEmpregaticio;

/**
 * @author renan
 */
public interface RhModulo {
	
	Cliente salvarCliente(Cliente cliente)throws PotiErpMensagensException, PotiErpException; 
	
	void excluirCliente(Cliente cliente)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaCliente(final List<Cliente> clientes, final TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException;
	
	Cliente consultarClientePorId(Long idCliente)throws PotiErpException;
	
	List<Cliente> consultarTodosClientes() throws PotiErpException;
	
	List<Cliente> consultarClientesAtivos() throws PotiErpException;
	
	List<Cliente> consultarClientesInativos() throws PotiErpException;
	
	List<Cliente> consultarTodosClientes(int firstRow, int lastRow) throws PotiErpException;
	
	List<Cliente> consultarClientesAtivos(int firstRow, int lastRow) throws PotiErpException;
	
	List<Cliente> consultarClientesInativos(int firstRow, int lastRow) throws PotiErpException;
	
	Empresa salvarEmpresa(Empresa empresa)throws PotiErpMensagensException, PotiErpException;
	
	void excluirEmpresa(Empresa empresa)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaEmpresa(List<Empresa> empresas)throws PotiErpMensagensException, PotiErpException;
	
	List<Empresa> consultarEmpresa(Empresa empresa)throws PotiErpException;
	
	List<Empresa> consultarTodasEmpresas() throws PotiErpException;
	
	Funcionario salvarFuncionario(Funcionario funcionario, TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirFuncionario(Funcionario funcionario, TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaFuncionario(List<Funcionario> funcionarios, TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException;
	
	List<Funcionario> consultarFuncionario(Funcionario funcionario)throws PotiErpException;
	
	List<Funcionario> consultarFuncionario(String prefixo) throws PotiErpException;
	
	Funcionario consultarFuncionario(Long idFuncionario, TraceInfo traceInfo)throws PotiErpException;
	
	List<Funcionario> consultarTodosFuncionarios() throws PotiErpException;
	
	List<Funcionario> consultarTodosFuncionarios(int firstRow, int lastRow) throws PotiErpException;
	
	List<Funcionario> consultarFuncionariosPesquisa(Cliente cliente,
			SituacaoFuncionario situacaoFuncionario) throws PotiErpException;
	
	Funcionario consultarFuncionarioPorRe(Funcionario funcionario) throws PotiErpException;
	
	List<Funcionario> consultarFuncionarioPorDataAdmissao(Date dataInicial,
			Date dataFinal, Cidade cidade, Collection<Cliente> clientes)
			throws PotiErpException;
	
	List<Funcionario> consultarTodosFuncionariosComNomeRE() throws PotiErpException;
	
	Funcionario consultarFuncionarioPorCalculoValeTransporte(final Long idCalculoValeTransporte) throws PotiErpException;
	
	Fornecedor salvarFornecedor(Fornecedor fornecedor)throws PotiErpMensagensException, PotiErpException;
	
	Feriado salvarFeriado(Feriado feriado) throws PotiErpMensagensException, PotiErpException;
	
	void excluirFornecedor(Fornecedor fornecedor)throws PotiErpMensagensException, PotiErpException;
	
	void excluirFeriado(Feriado feriado) throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaFornecedor(List<Fornecedor> fornecedores)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaFeriado(List<Feriado> feriados) throws PotiErpMensagensException, PotiErpException;
	
	List<Fornecedor> consultarFornecedor(Fornecedor fornecedor)throws PotiErpException;
	
	List<Fornecedor> consultarTodosFornecedores() throws PotiErpException;
	
	List<Feriado> consultarTodosFeriados() throws PotiErpException, PotiErpMensagensException;
	
	Feriado consultarFeriadoPorId(Long idFeriado)throws PotiErpException;
	
	EnderecoRepositorio salvarEndereco(EnderecoRepositorio endereco)throws PotiErpException;
	
	void excluirEndereco(EnderecoRepositorio endereco)throws PotiErpMensagensException, PotiErpException;
	
	List<EnderecoRepositorio> consultarEndereco(EnderecoRepositorio endereco)throws PotiErpException;
	
	EnderecoRepositorio consultarEnderecoPorCep(String cep)throws PotiErpException;
	
	JornadaTrabalho salvarJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpMensagensException, PotiErpException;
	
	void excluirJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaJornadaTrabalho(List<JornadaTrabalho> jornadasTrabalho)throws PotiErpMensagensException, PotiErpException;
	
	List<JornadaTrabalho> consultarJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpException;
	
	List<JornadaTrabalho> consultarTodasJornadasTrabalho() throws PotiErpException;
	
	HorariosJornada salvarHorariosJornada(HorariosJornada horariosJornada)throws PotiErpMensagensException, PotiErpException;
	
	void excluirHorariosJornada(HorariosJornada horariosJornada)throws PotiErpMensagensException, PotiErpException;
	
	List<HorariosJornada> consultarHorariosJornada(HorariosJornada horariosJornada)throws PotiErpException;
	
	JornadaTrabalho consultarPorIdComIntervalos(Long idJornada) throws PotiErpException;
	
	Demissao salvarDemissao(Demissao demissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirDemissao(Demissao demissao)throws PotiErpMensagensException, PotiErpException;
	
	List<Demissao> consultarDemissao(Demissao demissao)throws PotiErpException;
	
	List<Demissao> consultarTodasDemissoes() throws PotiErpException;
	
	TipoDemissao salvarTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaTipoDemissao(List<TipoDemissao> tiposDemissoes)throws PotiErpMensagensException, PotiErpException;
	
	List<TipoDemissao> consultarTipoDemissao(TipoDemissao tipoDemissao)throws PotiErpException;
	
	List<TipoDemissao> consultarTodosTiposDemissoes() throws PotiErpException;
	
	TipoAdmissao salvarTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaTipoAdmissao(List<TipoAdmissao> tiposAdmissoes)throws PotiErpMensagensException, PotiErpException;
	
	List<TipoAdmissao> consultarTipoAdmissao(TipoAdmissao tipoAdmissao)throws PotiErpException;
	
	List<TipoAdmissao> consultarTodosTiposAdmissoes() throws PotiErpException;
	
	Dependente salvarDependente(Dependente dependente)throws PotiErpMensagensException, PotiErpException;
	
	void excluirDependente(Dependente dependente)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaDependente(List<Dependente> dependentes)throws PotiErpMensagensException, PotiErpException;
	
	List<Dependente> consultarDependente(Dependente dependente)throws PotiErpException;
	
	List<Dependente> consultarTodosDependentes() throws PotiErpException;
	
	Banco salvarBanco(Banco banco)throws PotiErpMensagensException, PotiErpException;
	
	void excluirBanco(Banco banco)throws PotiErpMensagensException, PotiErpException;
	
	List<Banco> consultarBanco(Banco banco)throws PotiErpException;
	
	Agencia salvarAgencia(Agencia agencia)throws PotiErpMensagensException, PotiErpException;
	
	void excluirAgencia(Agencia agencia)throws PotiErpMensagensException, PotiErpException;
	
	List<Agencia> consultarAgencia(Agencia agencia)throws PotiErpException;
	
	Adicional salvarAdicional(Adicional adicional)throws PotiErpMensagensException, PotiErpException;
	
	void excluirAdicional(Adicional adicional)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaAdicional(List<Adicional> adicionais)throws PotiErpMensagensException, PotiErpException;
	
	List<Adicional> consultarAdicional(Adicional adicional)throws PotiErpException;
	
	List<Adicional> consultarTodosAdicionais() throws PotiErpException;
	
	TipoValeTransporte salvarTipoValeTransporte(TipoValeTransporte tipoValeTransporte)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoValeTransporte(TipoValeTransporte tipoValeTransporte)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaTipoValeTransporte(List<TipoValeTransporte> tiposValeTransporte)throws PotiErpMensagensException, PotiErpException;

	List<TipoValeTransporte> consultarTipoValeTransporte(TipoValeTransporte tipoValeTransporte)throws PotiErpException;
	
	List<TipoValeTransporte> consultarTodosTiposValeTransporte() throws PotiErpException;
	
	CalculoValeTransporte calcularValeTransporte(
			final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	void gravarValeTransporte(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	void excluirCalculoValeTransporte(
			final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	List<CalculoValeTransporte> consultarTodosCalculosValeTransporte() throws PotiErpException;
	
	CalculoValeRefeicao calcularValeRefeicao(
			final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	void gravarValeRefeicao(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	void excluirCalculoValeRefeicao(
			final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	List<CalculoValeRefeicao> consultarTodosCalculosValeRefeicao() throws PotiErpException;
	
	TipoValeRefeicao salvarTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaTipoValeRefeicao(List<TipoValeRefeicao> tiposValeRefeicao)throws PotiErpMensagensException, PotiErpException;
	
	List<TipoValeRefeicao> consultarTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao)throws PotiErpException;
	
	List<TipoValeRefeicao> consultarTodosTiposValeRefeicao() throws PotiErpException;
	
	TipoCestaBasica salvarTipoCestaBasica(TipoCestaBasica tipoCestaBasica)throws PotiErpMensagensException, PotiErpException;
	
	void excluirTipoCestaBasica(TipoCestaBasica tipoCestaBasica)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaTipoCestaBasica(List<TipoCestaBasica> tiposCestaBasica)throws PotiErpMensagensException, PotiErpException;
	
	List<TipoCestaBasica> consultarTipoCestaBasica(TipoCestaBasica tipoCestaBasica)throws PotiErpException;
	
	List<TipoCestaBasica> consultarTodosTiposCestaBasica() throws PotiErpException;
	
	Verba salvarVerba(Verba verba)throws PotiErpMensagensException, PotiErpException;
	
	void excluirVerba(Verba verba)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaVerba(List<Verba> verbas)throws PotiErpMensagensException, PotiErpException;
	
	List<Verba> consultarVerba(Verba verba)throws PotiErpException;
	
	List<Verba> consultarTodasVerbas() throws PotiErpException;
	
	GrauParentesco salvarGrauParentesco(GrauParentesco grauParentesco)throws PotiErpMensagensException, PotiErpException;
	
	void excluirGrauParentesco(GrauParentesco grauParentesco)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaGrauParentesco(List<GrauParentesco> valeRefeicoes)throws PotiErpMensagensException, PotiErpException;
	
	List<GrauParentesco> consultarGrauParentesco(GrauParentesco grauParentesco)throws PotiErpException;
	
	List<GrauParentesco> consultarTodosGrauParentescos() throws PotiErpException;
	
	Setor salvarSetor(Setor setor)throws PotiErpMensagensException, PotiErpException;
	
	void excluirSetor(Setor setor)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaSetor(List<Setor> setores)throws PotiErpMensagensException, PotiErpException;
	
	List<Setor> consultarSetor(Setor setor)throws PotiErpException;
	
	List<Setor> consultarTodosSetores() throws PotiErpException;
	
	Beneficio salvarBeneficio(Beneficio beneficio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirBeneficio(Beneficio beneficio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaBeneficio(List<Beneficio> beneficios)throws PotiErpMensagensException, PotiErpException;
	
	List<Beneficio> consultarBeneficio(Beneficio beneficio)throws PotiErpException;
	
	List<Beneficio> consultarTodosBeneficios() throws PotiErpException;
	
	Cargo salvarCargo(Cargo cargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirCargo(Cargo cargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaCargo(List<Cargo> cargos)throws PotiErpMensagensException, PotiErpException;
	
	Cargo consultarCargo(Cargo cargo)throws PotiErpException;
	
	List<Cargo> consultarTodosCargos() throws PotiErpException;
	
	VinculoEmpregaticio salvarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaVinculoEmpregaticio(List<VinculoEmpregaticio> vinculosEmpregaticio)throws PotiErpMensagensException, PotiErpException;
	
	List<VinculoEmpregaticio> consultarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpException;
	
	List<VinculoEmpregaticio> consultarTodosVinculosEmpregaticio() throws PotiErpException;
	
	SituacaoFuncionario salvarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpMensagensException, PotiErpException;
	
	void excluirSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaSituacaoFuncionario(List<SituacaoFuncionario> situacoesFuncionario)throws PotiErpMensagensException, PotiErpException;
	
	List<SituacaoFuncionario> consultarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpException;
	
	List<SituacaoFuncionario> consultarTodasSituacoesFuncionario() throws PotiErpException;
	
	FormaPagamento salvarFormaPagamento(FormaPagamento formaPagamento)throws PotiErpMensagensException, PotiErpException;
	
	void excluirFormaPagamento(FormaPagamento formaPagamento)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaFormaPagamento(List<FormaPagamento> formasPagamentos)throws PotiErpMensagensException, PotiErpException;
	
	List<FormaPagamento> consultarFormaPagamento(FormaPagamento formaPagamento)throws PotiErpException;
	
	List<FormaPagamento> consultarTodasFormasPagamentos() throws PotiErpException;
	
	Desconto salvarDesconto(Desconto desconto)throws PotiErpMensagensException, PotiErpException;
	
	void excluirDesconto(Desconto desconto)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaDesconto(List<Desconto> descontos)throws PotiErpMensagensException, PotiErpException;
	
	List<Desconto> consultarDesconto(Desconto desconto)throws PotiErpException;
	
	List<Desconto> consultarTodosDescontos() throws PotiErpException;
	
	Encargo salvarEncargo(Encargo encargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirEncargo(Encargo Encargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirListaEncargo(List<Encargo> encargos)throws PotiErpMensagensException, PotiErpException;
	
	List<Encargo> consultarEncargo(Encargo Encargo)throws PotiErpException;
	
	List<Encargo> consultarTodosEncargos() throws PotiErpException;

	Number consultarTotalClientesAtivos() throws PotiErpException;

	Number consultarTotalClientesInativos() throws PotiErpException;

	Number consultarTotalClientes() throws PotiErpException;
	
	Number consultarTotalFuncionarios() throws PotiErpException;
	
	Number consultarTotalFuncionariosPesquisa(Cliente cliente,
			SituacaoFuncionario situacaoFuncionario, TraceInfo traceInfo) throws PotiErpException;
	
	ParametrosRh salvarParametrosRh(ParametrosRh parametrosRh)throws PotiErpMensagensException, PotiErpException;
	
	List<ParametrosRh> consultarTodosParametrosRh() throws PotiErpException;
	
	List<Responsavel> consultarTodosResponsaveis() throws PotiErpException;
	
	List<Responsavel> consultarResponsavelPorFuncionario(Long idFuncionario) throws PotiErpException;
	
	Responsavel salvarResponsavel(Responsavel responsavel) throws PotiErpException;
	
	void excluirListaResponsavel(List<Responsavel> responsaveis) throws PotiErpException;
	
	void excluirResponsavel(Responsavel responsavel) throws PotiErpException;

	List<AlteracaoSalarial> consultarTodosAlteracaoSalarial() throws PotiErpException;
	
	AlteracaoSalarial salvarAlteracaoSalarial(AlteracaoSalarial alteracaoSalarial) throws PotiErpException;
	
	void excluirListaAlteracaoSalarial(List<AlteracaoSalarial> alteracoesSalarial) throws PotiErpException;
	
	void excluirAlteracaoSalarial(AlteracaoSalarial alteracaoSalarial) throws PotiErpException;
	
	List<AlteracaoCliente> consultarTodosAlteracaoCliente() throws PotiErpException;
	
	AlteracaoCliente salvarAlteracaoCliente(AlteracaoCliente alteracaoCliente) throws PotiErpException;
	
	void excluirListaAlteracaoCliente(List<AlteracaoCliente> alteracoesClientes) throws PotiErpException;
	
	void excluirAlteracaoCliente(AlteracaoCliente alteracaoCliente) throws PotiErpException;
	
	List<LocalTrabalho> getLocaisTrabalhoPorFuncionario(
			final Funcionario funcionario) throws PotiErpException;
	
	List<Ferias> consultarTodasFerias(final TraceInfo traceInfo) throws PotiErpException;
	
	List<Ferias> consultarFeriasPorFuncionario(final Long idFuncionario, final TraceInfo traceInfo) throws PotiErpException;
	
	Ferias salvarFerias(final Ferias ferias, final TraceInfo traceInfo) throws PotiErpException;
	
	void excluirFerias(final Ferias ferias, final TraceInfo traceInfo) throws PotiErpException;
	
	void excluirListaFerias(final List<Ferias> listFerias, final TraceInfo traceInfo) throws PotiErpException;
	
	List<HistoricoDemissao> consultarTodosHistoricosDemissoes(final TraceInfo traceInfo) throws PotiErpException;
	
	List<HistoricoDemissao> consultarHistoricosDemissoesPorFuncionario(final Long idFuncionario, final TraceInfo traceInfo) throws PotiErpException;
	
	HistoricoDemissao salvarHistoricoDemissao(final HistoricoDemissao historicoDemissao, final TraceInfo traceInfo) throws PotiErpException;
	
	void excluirHistoricoDemissao(final HistoricoDemissao historicoDemissao, final TraceInfo traceInfo) throws PotiErpException;
	
	void excluirListaHistoricoDemissao(final List<HistoricoDemissao> listHistoricoDemissao, final TraceInfo traceInfo) throws PotiErpException;

    Afastamento salvarAfastamento(Afastamento afastamento, TraceInfo traceInfo) throws PotiErpException;

    List<Afastamento> consultarTodosAfastamento(TraceInfo traceInfo) throws PotiErpException;

	void excluirListaAfastamento(List<Afastamento> afastamentos, TraceInfo traceInfo) throws PotiErpException;
	
	List<PagamentoValeTransporte> consultarPagamentosValeTransportePorCalculo(
			final Long idCalculo) throws PotiErpException;
	
	List<PagamentoValeRefeicao> consultarPagamentosValeRefeicaoPorCalculo(
			final Long idCalculo) throws PotiErpException;
	
	MedidaDisciplinar salvarMedidaDisciplinar(MedidaDisciplinar medidaDisciplinar, TraceInfo traceInfo) throws PotiErpException;
	
	List<MedidaDisciplinar> consultarTodasMedidasDisciplinares(TraceInfo traceInfo) throws PotiErpException;
	
	void excluirListaMedidasDisciplinares(List<MedidaDisciplinar> medidas, TraceInfo traceInfo) throws PotiErpException;
	
	List<Periodicidade> getAllPeriodicidade() throws PotiErpException;
	
}