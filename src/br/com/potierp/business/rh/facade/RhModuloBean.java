package br.com.potierp.business.rh.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import br.com.potierp.business.rh.service.AdmissaoService;
import br.com.potierp.business.rh.service.AfastamentoService;
import br.com.potierp.business.rh.service.AlteracaoClienteService;
import br.com.potierp.business.rh.service.AlteracaoSalarialService;
import br.com.potierp.business.rh.service.CargoService;
import br.com.potierp.business.rh.service.ClienteService;
import br.com.potierp.business.rh.service.DemissaoService;
import br.com.potierp.business.rh.service.DependenteService;
import br.com.potierp.business.rh.service.DescontoService;
import br.com.potierp.business.rh.service.EmpresaService;
import br.com.potierp.business.rh.service.EncargoService;
import br.com.potierp.business.rh.service.FeriadoService;
import br.com.potierp.business.rh.service.FeriasService;
import br.com.potierp.business.rh.service.FormaPagamentoService;
import br.com.potierp.business.rh.service.FornecedorService;
import br.com.potierp.business.rh.service.FuncionarioService;
import br.com.potierp.business.rh.service.GrauParentescoService;
import br.com.potierp.business.rh.service.HistoricoDemissaoService;
import br.com.potierp.business.rh.service.InstituicaoFinanceiraService;
import br.com.potierp.business.rh.service.JornadaTrabalhoService;
import br.com.potierp.business.rh.service.LocalTrabalhoService;
import br.com.potierp.business.rh.service.LogradouroService;
import br.com.potierp.business.rh.service.MedidaDisciplinarService;
import br.com.potierp.business.rh.service.ParametrosRhService;
import br.com.potierp.business.rh.service.ProventoService;
import br.com.potierp.business.rh.service.ResponsavelService;
import br.com.potierp.business.rh.service.SetorService;
import br.com.potierp.business.rh.service.SituacaoFuncionarioService;
import br.com.potierp.business.rh.service.TipoCestaBasicaService;
import br.com.potierp.business.rh.service.TipoValeRefeicaoService;
import br.com.potierp.business.rh.service.TipoValeTransporteService;
import br.com.potierp.business.rh.service.ValeRefeicaoService;
import br.com.potierp.business.rh.service.ValeTransporteService;
import br.com.potierp.business.rh.service.VerbaService;
import br.com.potierp.business.rh.service.VinculoEmpregaticioService;
import br.com.potierp.dao.PeriodicidadeDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
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

@Stateless(mappedName="RhModulo", name="RhModulo")
@Remote(RhModulo.class)
@Interceptors(DAOInterceptor.class)
public class RhModuloBean implements RhModulo {
	
	private static Logger log = Logger.getLogger(RhModuloBean.class);
	
	@EJB
	private LogradouroService logradouroService;
	
	@EJB
	private EmpresaService empresaService;
	
	@EJB
	private FornecedorService fornecedorService;
	
	@EJB
	private ClienteService clienteService;
	
	@EJB
	private JornadaTrabalhoService jornadaTrabalhoService;
	
	@EJB
	private ProventoService proventoService;
	
	@EJB
	private TipoValeTransporteService tipoValeTransporteService;
	
	@EJB
	private TipoValeRefeicaoService tipoValeRefeicaoService;
	
	@EJB
	private ValeTransporteService valeTransporteService;
	
	@EJB
	private ValeRefeicaoService valeRefeicaoService;
	
	@EJB
	private TipoCestaBasicaService tipoCestaBasicaService;
	
	@EJB
	private VerbaService verbaService;
	
	@EJB
	private GrauParentescoService grauParentescoService;
	
	@EJB
	private DescontoService descontoService;
	
	@EJB
	private DemissaoService demissaoService;
	
	@EJB
	private AdmissaoService admissaoService;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	private DependenteService dependenteService;
	
	@EJB
	private InstituicaoFinanceiraService instituicaoFinanceiraService;
	
	@EJB
	private EncargoService encargoService;
	
	@EJB
	private CargoService cargoService;
	
	@EJB
	private VinculoEmpregaticioService vinculoEmpregaticioService;
	
	@EJB
	private SituacaoFuncionarioService situacaoFuncionarioService;

	@EJB
	private FormaPagamentoService formaPagamentoService;
	
	@EJB
	private SetorService setorService;
	
	@EJB
	private FeriadoService feriadoService; 
	
	@EJB
	private ParametrosRhService parametrosRhService;
	
	@EJB
	private ResponsavelService responsavelService;
	@EJB
	private AlteracaoSalarialService alteracaoSalarialService;
	
	@EJB
	private AlteracaoClienteService alteracaoClienteService;
	
	@EJB
	private LocalTrabalhoService localTrabalhoService;
	
	@EJB
	private FeriasService feriasService;
	
	@EJB
	private HistoricoDemissaoService historicoDemissaoService;
	
	@EJB
	private AfastamentoService afastamentoService;
	
	@EJB
	private MedidaDisciplinarService medidaDisciplinarService;
	
	@DAO
	private PeriodicidadeDao periodicidadeDao;
	
	@Override
	public Cliente salvarCliente(final Cliente cliente) throws PotiErpMensagensException, PotiErpException {
		try {
			return clienteService.salvar(cliente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirCliente(final Cliente cliente) throws PotiErpMensagensException, PotiErpException {
		try{
			clienteService.excluir(cliente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaCliente(final List<Cliente> clientes, final TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException{
		try{
			clienteService.excluirLista(clientes);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Cliente consultarClientePorId(final Long idCliente) throws PotiErpException {
		try {
			return clienteService.consultar(idCliente);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Cliente> consultarTodosClientes() throws PotiErpException {
		try{
			return clienteService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Cliente> consultarClientesAtivos() throws PotiErpException{
		try{
			return clienteService.consultarAtivos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Cliente> consultarClientesInativos() throws PotiErpException{
		try{
			return clienteService.consultarInativos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Empresa salvarEmpresa(final Empresa empresa) throws PotiErpMensagensException, PotiErpException {
		try{
			return empresaService.salvar(empresa);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirEmpresa(final Empresa empresa) throws PotiErpMensagensException, PotiErpException {
		try{
			empresaService.excluir(empresa);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaEmpresa(final List<Empresa> empresas)throws PotiErpMensagensException, PotiErpException{
		try{
			empresaService.excluirLista(empresas);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Empresa> consultarEmpresa(final Empresa empresa) throws PotiErpException {
		try{
			return empresaService.consultar(empresa);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Empresa> consultarTodasEmpresas() throws PotiErpException {
		try{
			return empresaService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Fornecedor salvarFornecedor(final Fornecedor fornecedor) throws PotiErpMensagensException, PotiErpException {
		try{
			return fornecedorService.salvar(fornecedor);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirFornecedor(final Fornecedor fornecedor) throws PotiErpMensagensException, PotiErpException {
		try{
			fornecedorService.excluir(fornecedor);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaFornecedor(final List<Fornecedor> fornecedores)throws PotiErpMensagensException, PotiErpException{
		try{
			fornecedorService.excluirLista(fornecedores);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Fornecedor> consultarFornecedor(final Fornecedor fornecedor) throws PotiErpException {
		try{
			return fornecedorService.consultar(fornecedor);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Fornecedor> consultarTodosFornecedores() throws PotiErpException {
		try{
			return fornecedorService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public EnderecoRepositorio salvarEndereco(final EnderecoRepositorio endereco) throws PotiErpMensagensException, PotiErpException {
		try{
			return logradouroService.salvarEndereco(endereco);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirEndereco(final EnderecoRepositorio endereco) throws PotiErpMensagensException, PotiErpException {
		try{
			logradouroService.excluirEndereco(endereco);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<EnderecoRepositorio> consultarEndereco(final EnderecoRepositorio endereco)throws PotiErpException {
		try{
			return logradouroService.consultarEndereco(endereco);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public EnderecoRepositorio consultarEnderecoPorCep(final String cep)throws PotiErpException {
		try{
			return logradouroService.consultarEnderecoPorCep(cep);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public JornadaTrabalho salvarJornadaTrabalho(final JornadaTrabalho jornadaTrabalho)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return jornadaTrabalhoService.salvarJornadaTrabalho(jornadaTrabalho);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirJornadaTrabalho(final JornadaTrabalho jornadaTrabalho)
			throws PotiErpMensagensException, PotiErpException {
		try{
			jornadaTrabalhoService.excluirJornadaTrabalho(jornadaTrabalho);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaJornadaTrabalho(final List<JornadaTrabalho> jornadasTrabalho)
			throws PotiErpMensagensException, PotiErpException {
		try {
			jornadaTrabalhoService.excluirListaJornadaTrabalho(jornadasTrabalho);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<JornadaTrabalho> consultarJornadaTrabalho(
			final JornadaTrabalho jornadaTrabalho) throws PotiErpException {
		try{
			return jornadaTrabalhoService.consultarJornadaTrabalho(jornadaTrabalho);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<JornadaTrabalho> consultarTodasJornadasTrabalho()
			throws PotiErpException {
		try {
			return jornadaTrabalhoService.consultarTodasJornadasTrabalho();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public HorariosJornada salvarHorariosJornada(final HorariosJornada horariosJornada)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return jornadaTrabalhoService.salvarHorariosJornada(horariosJornada);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirHorariosJornada(final HorariosJornada horariosJornada)
			throws PotiErpMensagensException, PotiErpException {
		try{
			jornadaTrabalhoService.excluirHorariosJornada(horariosJornada);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<HorariosJornada> consultarHorariosJornada(
			final HorariosJornada horariosJornada) throws PotiErpException {
		try{
			return jornadaTrabalhoService.consultarHorariosJornada(horariosJornada);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Demissao salvarDemissao(final Demissao demissao) throws PotiErpMensagensException, PotiErpException {
		try{
			return demissaoService.salvarDemissao(demissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirDemissao(final Demissao demissao) throws PotiErpMensagensException, PotiErpException {
		try{
			demissaoService.excluirDemissao(demissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Demissao> consultarDemissao(final Demissao demissao)
			throws PotiErpException {
		try{
			return demissaoService.consultarDemissao(demissao);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Demissao> consultarTodasDemissoes()
			throws PotiErpException {
		try {
			return demissaoService.consultarTodasDemissoes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public TipoAdmissao salvarTipoAdmissao(final TipoAdmissao tipoAdmissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			return admissaoService.salvarTipoAdmissao(tipoAdmissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirTipoAdmissao(final TipoAdmissao tipoAdmissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			admissaoService.excluirTipoAdmissao(tipoAdmissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaTipoAdmissao(final List<TipoAdmissao> tiposAdmissoes)
			throws PotiErpMensagensException, PotiErpException {
		try {
			admissaoService.excluirListaTipoAdmissao(tiposAdmissoes);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TipoAdmissao> consultarTipoAdmissao(final TipoAdmissao tipoAdmissao)
			throws PotiErpException {
		try {
			return admissaoService.consultarTipoAdmissao(tipoAdmissao);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TipoAdmissao> consultarTodosTiposAdmissoes()
			throws PotiErpException {
		try {
			return admissaoService.consultarTodosTiposAdmissoes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public TipoDemissao salvarTipoDemissao(final TipoDemissao tipoDemissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			return demissaoService.salvarTipoDemissao(tipoDemissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirTipoDemissao(final TipoDemissao tipoDemissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			demissaoService.excluirTipoDemissao(tipoDemissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaTipoDemissao(final List<TipoDemissao> tiposDemissoes)
			throws PotiErpMensagensException, PotiErpException {
		try {
			demissaoService.excluirListaTipoDemissao(tiposDemissoes);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TipoDemissao> consultarTipoDemissao(final TipoDemissao tipoDemissao)
			throws PotiErpException {
		try {
			return demissaoService.consultarTipoDemissao(tipoDemissao);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TipoDemissao> consultarTodosTiposDemissoes()
			throws PotiErpException {
		try {
			return demissaoService.consultarTodosTiposDemissoes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}	

	@Override
	public Funcionario salvarFuncionario(final Funcionario funcionario,
			final TraceInfo traceInfo) throws PotiErpMensagensException,
			PotiErpException {
		try{
			return funcionarioService.salvar(funcionario);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirFuncionario(final Funcionario funcionario, final TraceInfo traceInfo) throws PotiErpMensagensException, PotiErpException {
		try{
			funcionarioService.excluir(funcionario);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaFuncionario(final List<Funcionario> funcionarios, final TraceInfo traceInfo)throws PotiErpMensagensException, PotiErpException{
		try{
			funcionarioService.excluirLista(funcionarios);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Funcionario> consultarFuncionario(final Funcionario funcionario) throws PotiErpException {
		try{
			return funcionarioService.consultar(funcionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Funcionario> consultarFuncionario(final String prefixo) throws PotiErpException {
		try {
			return funcionarioService.consultar(prefixo);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Funcionario consultarFuncionario(final Long idFuncionario,
			final TraceInfo traceInfo) throws PotiErpException {
		try {
			return funcionarioService.consultar(idFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Funcionario> consultarTodosFuncionarios() throws PotiErpException {
		try{
			return funcionarioService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Funcionario> consultarTodosFuncionarios(final int firstRow, final int lastRow) throws PotiErpException {
		try{
			return funcionarioService.consultarTodos(firstRow, lastRow);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Funcionario> consultarFuncionariosPesquisa(
			final Cliente cliente, final SituacaoFuncionario situacaoFuncionario)
			throws PotiErpException {
		try{
			return funcionarioService.consultarPesquisa(cliente, situacaoFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Funcionario consultarFuncionarioPorRe(final Funcionario funcionario) throws PotiErpException {
		try{
			return funcionarioService.consultarPorRe(funcionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Dependente salvarDependente(final Dependente dependente)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return dependenteService.salvarDependente(dependente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirDependente(final Dependente dependente)
			throws PotiErpMensagensException, PotiErpException {
		try{
			dependenteService.excluirDependente(dependente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaDependente(final List<Dependente> dependentes)throws PotiErpMensagensException, PotiErpException{
		try{
			dependenteService.excluirListaDependente(dependentes);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Dependente> consultarDependente(final Dependente dependente)
			throws PotiErpException {
		try{
			return dependenteService.consultarDependente(dependente);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
 	
	@Override
	public List<Dependente> consultarTodosDependentes() throws PotiErpException {
		try{
			return dependenteService.consultarTodosDependentes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Banco salvarBanco(final Banco banco) throws PotiErpMensagensException, PotiErpException {
		try{
			return instituicaoFinanceiraService.salvarBanco(banco);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirBanco(final Banco banco) throws PotiErpMensagensException, PotiErpException {
		try{
			instituicaoFinanceiraService.excluirBanco(banco);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Banco> consultarBanco(final Banco banco) throws PotiErpException {
		try{
			return instituicaoFinanceiraService.consultarBanco(banco);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Agencia salvarAgencia(final Agencia agencia) throws PotiErpMensagensException, PotiErpException {
		try{
			return instituicaoFinanceiraService.salvarAgencia(agencia);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirAgencia(final Agencia agencia) throws PotiErpMensagensException, PotiErpException {
		try{
			instituicaoFinanceiraService.excluirAgencia(agencia);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Agencia> consultarAgencia(final Agencia agencia)
			throws PotiErpException {
		try{
			return instituicaoFinanceiraService.consultarAgencia(agencia);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Desconto salvarDesconto(final Desconto desconto) throws PotiErpMensagensException, PotiErpException {
		try {
			return descontoService.salvarDesconto(desconto);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirDesconto(final Desconto desconto) throws PotiErpMensagensException, PotiErpException {
		try {
			descontoService.excluirDesconto(desconto);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaDesconto(final List<Desconto> descontos) throws PotiErpMensagensException, PotiErpException {
		try {
			descontoService.excluirListaDesconto(descontos);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Desconto> consultarDesconto(final Desconto desconto) throws PotiErpException {
		try {
			return descontoService.consultarDesconto(desconto);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Desconto> consultarTodosDescontos() throws PotiErpException {
		try {
			return descontoService.consultarTodosDescontos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Adicional salvarAdicional(final Adicional adicional) throws PotiErpMensagensException, PotiErpException {
		try {
			return proventoService.salvarAdicional(adicional);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirAdicional(final Adicional adicional) throws PotiErpMensagensException, PotiErpException {
		try {
			proventoService.excluirAdicional(adicional);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaAdicional(final List<Adicional> adicionais) throws PotiErpMensagensException, PotiErpException {
		try {
			proventoService.excluirListaAdicional(adicionais);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Adicional> consultarAdicional(final Adicional adicional) throws PotiErpException {
		try {
			return proventoService.consultarAdicional(adicional);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Adicional> consultarTodosAdicionais() throws PotiErpException {
		try {
			return proventoService.consultarTodosAdicionais();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public TipoValeTransporte salvarTipoValeTransporte(
			final TipoValeTransporte tipoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		return tipoValeTransporteService.salvarTipoValeTransporte(tipoValeTransporte);
	}
	
	@Override
	public void excluirTipoValeTransporte(final TipoValeTransporte tipoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		tipoValeTransporteService.excluirTipoValeTransporte(tipoValeTransporte);
		
	}
	
	@Override
	public void excluirListaTipoValeTransporte(
			final List<TipoValeTransporte> tiposValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		tipoValeTransporteService.excluirListaTipoValeTransporte(tiposValeTransporte);
	}
	
	@Override
	public List<TipoValeTransporte> consultarTodosTiposValeTransporte()
			throws PotiErpException {
		return tipoValeTransporteService.consultarTodosTiposValeTransporte();
	}
	
	@Override
	public List<TipoValeTransporte> consultarTipoValeTransporte(
			final TipoValeTransporte tipoValeTransporte) throws PotiErpException {
		return tipoValeTransporteService.consultarTipoValeTransporte(tipoValeTransporte);
	}
	
	@Override
	public CalculoValeTransporte calcularValeTransporte(
			final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		return valeTransporteService.calcular(calculoValeTransporte);
	}
	
	@Override
	public void gravarValeTransporte(
			final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		valeTransporteService.gravar(calculoValeTransporte);
	}
	
	@Override
	public void excluirCalculoValeTransporte(
			final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		valeTransporteService.excluirCalculo(calculoValeTransporte);
	}
	
	@Override
	public List<CalculoValeTransporte> consultarTodosCalculosValeTransporte()
			throws PotiErpException {
		return valeTransporteService.consultarTodos();
	}
	
	@Override
	public TipoValeRefeicao salvarTipoValeRefeicao(
			final TipoValeRefeicao tipoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		return tipoValeRefeicaoService.salvarTipoValeRefeicao(tipoValeRefeicao);
	}
	
	@Override
	public void excluirTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		tipoValeRefeicaoService.excluirTipoValeRefeicao(tipoValeRefeicao);
		
	}
	
	@Override
	public void excluirListaTipoValeRefeicao(
			final List<TipoValeRefeicao> tiposValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		tipoValeRefeicaoService.excluirListaTipoValeRefeicao(tiposValeRefeicao);
	}
	
	@Override
	public List<TipoValeRefeicao> consultarTodosTiposValeRefeicao()
			throws PotiErpException {
		return tipoValeRefeicaoService.consultarTodosTiposValeRefeicao();
	}
	
	@Override
	public List<TipoValeRefeicao> consultarTipoValeRefeicao(
			final TipoValeRefeicao tipoValeRefeicao) throws PotiErpException {
		return tipoValeRefeicaoService.consultarTipoValeRefeicao(tipoValeRefeicao);
	}
	
	@Override
	public CalculoValeRefeicao calcularValeRefeicao(
			final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		return valeRefeicaoService.calcular(calculoValeRefeicao);
	}
	
	@Override
	public List<CalculoValeRefeicao> consultarTodosCalculosValeRefeicao()
			throws PotiErpException {
		return valeRefeicaoService.consultarTodos();
	}
	
	@Override
	public void excluirCalculoValeRefeicao(
			final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		valeRefeicaoService.excluirCalculo(calculoValeRefeicao);
	}
	
	@Override
	public void gravarValeRefeicao(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		valeRefeicaoService.gravar(calculoValeRefeicao);
	}
	
	@Override
	public TipoCestaBasica salvarTipoCestaBasica(
			final TipoCestaBasica tipoCestaBasica)
			throws PotiErpMensagensException, PotiErpException {
		return tipoCestaBasicaService.salvarTipoCestaBasica(tipoCestaBasica);
	}
	
	@Override
	public void excluirTipoCestaBasica(final TipoCestaBasica tipoCestaBasica)
			throws PotiErpMensagensException, PotiErpException {
		tipoCestaBasicaService.excluirTipoCestaBasica(tipoCestaBasica);
		
	}
	
	@Override
	public void excluirListaTipoCestaBasica(
			final List<TipoCestaBasica> tiposCestaBasica)
			throws PotiErpMensagensException, PotiErpException {
		tipoCestaBasicaService.excluirListaTipoCestaBasica(tiposCestaBasica);
	}
	
	@Override
	public List<TipoCestaBasica> consultarTodosTiposCestaBasica()
			throws PotiErpException {
		return tipoCestaBasicaService.consultarTodosTiposCestaBasica();
	}
	
	@Override
	public List<TipoCestaBasica> consultarTipoCestaBasica(
			final TipoCestaBasica tipoCestaBasica) throws PotiErpException {
		return tipoCestaBasicaService.consultarTipoCestaBasica(tipoCestaBasica);
	}
	
	@Override
	public Verba salvarVerba(
			final Verba verba)
			throws PotiErpMensagensException, PotiErpException {
		return verbaService.salvarVerba(verba);
	}
	
	@Override
	public void excluirVerba(final Verba verba)
			throws PotiErpMensagensException, PotiErpException {
		verbaService.excluirVerba(verba);
		
	}
	
	@Override
	public void excluirListaVerba(
			final List<Verba> verbas)
			throws PotiErpMensagensException, PotiErpException {
		verbaService.excluirListaVerba(verbas);
	}
	
	@Override
	public List<Verba> consultarTodasVerbas()
			throws PotiErpException {
		return verbaService.consultarTodasVerbas();
	}
	
	@Override
	public List<Verba> consultarVerba(
			final Verba verba) throws PotiErpException {
		return verbaService.consultarVerba(verba);
	}

	@Override
	public GrauParentesco salvarGrauParentesco(
			final GrauParentesco grauParentesco)
			throws PotiErpMensagensException, PotiErpException {
		return grauParentescoService.salvarGrauParentesco(grauParentesco);
	}
	
	@Override
	public void excluirGrauParentesco(final GrauParentesco grauParentesco)
			throws PotiErpMensagensException, PotiErpException {
		grauParentescoService.excluirGrauParentesco(grauParentesco);
	}
	
	@Override
	public void excluirListaGrauParentesco(
			final List<GrauParentesco> valesRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		grauParentescoService.excluirListaGrauParentesco(valesRefeicao);
	}
	
	@Override
	public List<GrauParentesco> consultarTodosGrauParentescos()
			throws PotiErpException {
		return grauParentescoService.consultarTodosGrauParentescos();
	}
	
	@Override
	public List<GrauParentesco> consultarGrauParentesco(
			final GrauParentesco grauParentesco) throws PotiErpException {
		return grauParentescoService.consultarGrauParentesco(grauParentesco);
	}
	
	@Override
	public Setor salvarSetor(final Setor setor) throws PotiErpMensagensException, PotiErpException {
		try {
			return setorService.salvarSetor(setor);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirSetor(final Setor setor) throws PotiErpMensagensException, PotiErpException {
		try {
			setorService.excluirSetor(setor);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaSetor(final List<Setor> setores) throws PotiErpMensagensException, PotiErpException {
		try {
			setorService.excluirListaSetor(setores);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Setor> consultarSetor(final Setor setor) throws PotiErpException {
		try {
			return setorService.consultarSetor(setor);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Setor> consultarTodosSetores() throws PotiErpException {
		try {
			return setorService.consultarTodosSetores();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Beneficio salvarBeneficio(final Beneficio beneficio) throws PotiErpMensagensException, PotiErpException {
		try {
			return proventoService.salvarBeneficio(beneficio);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirBeneficio(final Beneficio beneficio) throws PotiErpMensagensException, PotiErpException {
		try {
			proventoService.excluirBeneficio(beneficio);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaBeneficio(final List<Beneficio> beneficios) throws PotiErpMensagensException, PotiErpException {
		try {
			proventoService.excluirListaBeneficio(beneficios);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Beneficio> consultarBeneficio(final Beneficio beneficio) throws PotiErpException {
		try {
			return proventoService.consultarBeneficio(beneficio);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Beneficio> consultarTodosBeneficios() throws PotiErpException {
		try {
			return proventoService.consultarTodosBeneficios();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Cargo salvarCargo(final Cargo cargo) throws PotiErpMensagensException, PotiErpException {
		try {
			return cargoService.salvarCargo(cargo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirCargo(final Cargo cargo) throws PotiErpMensagensException, PotiErpException {
		try {
			cargoService.excluirCargo(cargo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaCargo(final List<Cargo> cargos) throws PotiErpMensagensException, PotiErpException {
		try {
			cargoService.excluirListaCargo(cargos);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Cargo consultarCargo(final Cargo cargo) throws PotiErpException {
		try {
			return cargoService.consultarCargo(cargo);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cargo> consultarTodosCargos() throws PotiErpException {
		try {
			return cargoService.consultarTodosCargos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public VinculoEmpregaticio salvarVinculoEmpregaticio(final VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpMensagensException, PotiErpException {
		try {
			return vinculoEmpregaticioService.salvarVinculoEmpregaticio(vinculoEmpregaticio);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirVinculoEmpregaticio(final VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpMensagensException, PotiErpException {
		try {
			vinculoEmpregaticioService.excluirVinculoEmpregaticio(vinculoEmpregaticio);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaVinculoEmpregaticio(final List<VinculoEmpregaticio> vinculosEmpregaticio) throws PotiErpMensagensException, PotiErpException {
		try {
			vinculoEmpregaticioService.excluirListaVinculoEmpregaticio(vinculosEmpregaticio);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<VinculoEmpregaticio> consultarVinculoEmpregaticio(final VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpException {
		try {
			return vinculoEmpregaticioService.consultarVinculoEmpregaticio(vinculoEmpregaticio);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<VinculoEmpregaticio> consultarTodosVinculosEmpregaticio() throws PotiErpException {
		try {
			return vinculoEmpregaticioService.consultarTodosVinculosEmpregaticio();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public SituacaoFuncionario salvarSituacaoFuncionario(final SituacaoFuncionario situacaoFuncionario) throws PotiErpMensagensException, PotiErpException {
		try {
			return situacaoFuncionarioService.salvarSituacaoFuncionario(situacaoFuncionario);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirSituacaoFuncionario(final SituacaoFuncionario situacaoFuncionario) throws PotiErpMensagensException, PotiErpException {
		try {
			situacaoFuncionarioService.excluirSituacaoFuncionario(situacaoFuncionario);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaSituacaoFuncionario(final List<SituacaoFuncionario> situacoesFuncionario) throws PotiErpMensagensException, PotiErpException {
		try {
			situacaoFuncionarioService.excluirListaSituacaoFuncionario(situacoesFuncionario);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<SituacaoFuncionario> consultarSituacaoFuncionario(final SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		try {
			return situacaoFuncionarioService.consultarSituacaoFuncionario(situacaoFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<SituacaoFuncionario> consultarTodasSituacoesFuncionario() throws PotiErpException {
		try {
			return situacaoFuncionarioService.consultarTodasSituacoesFuncionario();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public FormaPagamento salvarFormaPagamento(final FormaPagamento formaPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			return formaPagamentoService.salvarFormaPagamento(formaPagamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirFormaPagamento(final FormaPagamento formaPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			formaPagamentoService.excluirFormaPagamento(formaPagamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaFormaPagamento(final List<FormaPagamento> formasPagamentos) throws PotiErpMensagensException, PotiErpException {
		try {
			formaPagamentoService.excluirListaFormaPagamento(formasPagamentos);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<FormaPagamento> consultarFormaPagamento(final FormaPagamento formaPagamento) throws PotiErpException {
		try {
			return formaPagamentoService.consultarFormaPagamento(formaPagamento);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<FormaPagamento> consultarTodasFormasPagamentos() throws PotiErpException {
		try {
			return formaPagamentoService.consultarTodasFormasPagamentos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Encargo salvarEncargo(final Encargo encargo)
			throws PotiErpMensagensException, PotiErpException {
		try {
			return encargoService.salvarEncargo(encargo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirEncargo(final Encargo encargo)
			throws PotiErpMensagensException, PotiErpException {
		try {
			encargoService.excluirEncargo(encargo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaEncargo(final List<Encargo> encargos)
			throws PotiErpMensagensException, PotiErpException {
		try {
			encargoService.excluirListaEncargo(encargos);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Encargo> consultarEncargo(final Encargo encargo)
			throws PotiErpException {
		try {
			return encargoService.consultarEncargo(encargo);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Encargo> consultarTodosEncargos()
			throws PotiErpException {
		try {
			return encargoService.consultarTodosEncargos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cliente> consultarTodosClientes(final int firstRow, final int lastRow) throws PotiErpException {
		try{
			return clienteService.consultarTodos(firstRow, lastRow);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cliente> consultarClientesAtivos(final int firstRow, final int lastRow) throws PotiErpException {
		try{
			return clienteService.consultarAtivos(firstRow, lastRow);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cliente> consultarClientesInativos(final int firstRow, final int lastRow) throws PotiErpException {
		try{
			return clienteService.consultarInativos(firstRow, lastRow);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Number consultarTotalClientesAtivos() throws PotiErpException{
		try{
			return clienteService.consultarTotalAtivos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Number consultarTotalClientesInativos() throws PotiErpException{
		try{
			return clienteService.consultarTotalInativos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Number consultarTotalClientes() throws PotiErpException{
		try{
			return clienteService.consultarTotalClientes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Number consultarTotalFuncionarios() throws PotiErpException{
		try{
			return funcionarioService.consultarTotalFuncionarios();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Number consultarTotalFuncionariosPesquisa(final Cliente cliente,
			final SituacaoFuncionario situacaoFuncionario, final TraceInfo traceInfo)
			throws PotiErpException {
		try{
			return funcionarioService.consultarTotalFuncionariosPesquisa(cliente, situacaoFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Feriado salvarFeriado(final Feriado feriado)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return feriadoService.salvar(feriado);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Feriado> consultarTodosFeriados()
			throws PotiErpException {
		try{
			return feriadoService.consultarTodosFeriados();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Feriado consultarFeriadoPorId(final Long idFeriado) throws PotiErpException {
		try {
			return feriadoService.consultar(idFeriado);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirFeriado(final Feriado feriado)
			throws PotiErpMensagensException, PotiErpException {
		try{
			feriadoService.excluir(feriado);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.facade.RhModulo#excluirListaFeriado(java.util.List)
	 */
	@Override
	public void excluirListaFeriado(final List<Feriado> feriados)
			throws PotiErpMensagensException, PotiErpException {
		try{
			feriadoService.excluirLista(feriados);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<ParametrosRh> consultarTodosParametrosRh()
			throws PotiErpException {
		try{
			return parametrosRhService.consultarTodosParametrosRh();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public ParametrosRh salvarParametrosRh(final ParametrosRh parametrosRh)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return parametrosRhService.salvarParametrosRh(parametrosRh);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public JornadaTrabalho consultarPorIdComIntervalos(final Long idJornada)
			throws PotiErpException {
		try {
			return jornadaTrabalhoService.consultarPorIdComIntervalos(idJornada);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.facade.RhModulo#consultarFuncionarioPorDataAdmissao(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Funcionario> consultarFuncionarioPorDataAdmissao(
			final Date dataInicial, final Date dataFinal, final Cidade cidade,
			final Collection<Cliente> clientes) throws PotiErpException {
		try{
			return funcionarioService.consultarPorDataAdmissao(dataInicial, dataFinal, cidade, clientes);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Funcionario> consultarTodosFuncionariosComNomeRE() throws PotiErpException{
		try{
			return funcionarioService.consultarTodosComNomeRE();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Funcionario consultarFuncionarioPorCalculoValeTransporte(final Long idCalculoValeTransporte) throws PotiErpException{
		try{
			return funcionarioService.consultarPorCalculoValeTransporte(idCalculoValeTransporte);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.facade.RhModulo#consultarTodosResponsaveis()
	 */
	@Override
	public List<Responsavel> consultarTodosResponsaveis()
			throws PotiErpException {
		try{
			return responsavelService.consultarTodosResponsaveis();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Responsavel> consultarResponsavelPorFuncionario(
			final Long idFuncionario) throws PotiErpException {
		try{
			return responsavelService.buscarPorFuncionario(idFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Responsavel salvarResponsavel(final Responsavel responsavel) throws PotiErpException{
		try{
			return responsavelService.salvar(responsavel);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.facade.RhModulo#excluirListaResponsavel(java.util.List)
	 */
	@Override
	public void excluirListaResponsavel(final List<Responsavel> responsaveis)
			throws PotiErpException {
		try{
			responsavelService.excluirLista(responsaveis);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.facade.RhModulo#excluirResponsavel(br.com.potierp.model.Responsavel)
	 */
	@Override
	public void excluirResponsavel(final Responsavel responsavel)
			throws PotiErpException {
		try{
			responsavelService.excluir(responsavel);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<AlteracaoSalarial> consultarTodosAlteracaoSalarial()
			throws PotiErpException {
		try{
			return alteracaoSalarialService.consultarTodosAlteracaoSalarial();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public AlteracaoSalarial salvarAlteracaoSalarial(final AlteracaoSalarial alteracaoSalarial) throws PotiErpException{
		try{
			return alteracaoSalarialService.salvar(alteracaoSalarial);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaAlteracaoSalarial(final List<AlteracaoSalarial> alteracoesSalarial)
			throws PotiErpException {
		try{
			alteracaoSalarialService.excluirLista(alteracoesSalarial);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirAlteracaoSalarial(final AlteracaoSalarial alteracaoSalarial)
			throws PotiErpException {
		try{
			alteracaoSalarialService.excluir(alteracaoSalarial);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<AlteracaoCliente> consultarTodosAlteracaoCliente()
			throws PotiErpException {
		try{
			return alteracaoClienteService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public AlteracaoCliente salvarAlteracaoCliente(final AlteracaoCliente alteracaoCliente) throws PotiErpException{
		try{
			return alteracaoClienteService.salvar(alteracaoCliente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaAlteracaoCliente(final List<AlteracaoCliente> alteracoesCliente)
			throws PotiErpException {
		try{
			alteracaoClienteService.excluirLista(alteracoesCliente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirAlteracaoCliente(final AlteracaoCliente alteracaoCliente)
			throws PotiErpException {
		try{
			alteracaoClienteService.excluir(alteracaoCliente);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<LocalTrabalho> getLocaisTrabalhoPorFuncionario(final Funcionario funcionario)
			throws PotiErpException {
		try{
			return localTrabalhoService.getPorReComSetores(funcionario.getCodigoRegistro());
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Ferias> consultarTodasFerias(final TraceInfo traceInfo) throws PotiErpException {
		try {
			return feriasService.consultarTodasFerias();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Ferias> consultarFeriasPorFuncionario(final Long idFuncionario, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return feriasService.buscarPorFuncionario(idFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Ferias salvarFerias(final Ferias ferias, final TraceInfo traceInfo) throws PotiErpException {
		try {
			return feriasService.salvar(ferias);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirFerias(final Ferias ferias, final TraceInfo traceInfo) throws PotiErpException {
		try {
			feriasService.excluir(ferias);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void excluirListaFerias(final List<Ferias> listFerias, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			feriasService.excluirLista(listFerias);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public List<HistoricoDemissao> consultarTodosHistoricosDemissoes(final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return historicoDemissaoService.consultarTodosHistoricosDemissoes();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<HistoricoDemissao> consultarHistoricosDemissoesPorFuncionario(
			final Long idFuncionario, final TraceInfo traceInfo) throws PotiErpException {
		try {
			return historicoDemissaoService.buscarPorFuncionario(idFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public HistoricoDemissao salvarHistoricoDemissao(
			final HistoricoDemissao historicoDemissao, final TraceInfo traceInfo) throws PotiErpException {
		try {
			return historicoDemissaoService.salvar(historicoDemissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirHistoricoDemissao(final HistoricoDemissao historicoDemissao, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			historicoDemissaoService.excluir(historicoDemissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void excluirListaHistoricoDemissao(
			final List<HistoricoDemissao> listHistoricoDemissao, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			historicoDemissaoService.excluirLista(listHistoricoDemissao);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Afastamento salvarAfastamento(final Afastamento afastamento, final TraceInfo traceInfo) throws PotiErpException {
		try {
			return afastamentoService.salvar(afastamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Afastamento> consultarTodosAfastamento(final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return afastamentoService.consultarTodosAfastamento();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void excluirListaAfastamento(final List<Afastamento> afastamentos, final TraceInfo traceInfo)
			throws PotiErpException {
		try{
			afastamentoService.excluirLista(afastamentos);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<PagamentoValeTransporte> consultarPagamentosValeTransportePorCalculo(final Long idCalculo)
			throws PotiErpException {
		try{
			return valeTransporteService.getPagamentosPorCalculo(idCalculo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<PagamentoValeRefeicao> consultarPagamentosValeRefeicaoPorCalculo(final Long idCalculo)
			throws PotiErpException {
		try{
			return valeRefeicaoService.getPagamentosPorCalculo(idCalculo);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public MedidaDisciplinar salvarMedidaDisciplinar(
			final MedidaDisciplinar medidaDisciplinar, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return medidaDisciplinarService.salvar(medidaDisciplinar);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<MedidaDisciplinar> consultarTodasMedidasDisciplinares(
			final TraceInfo traceInfo) throws PotiErpException {
		try {
			return medidaDisciplinarService.consultarTodasMedidasDisciplinares();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaMedidasDisciplinares(
			final List<MedidaDisciplinar> medidas, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			medidaDisciplinarService.excluirLista(medidas);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Periodicidade> getAllPeriodicidade() throws PotiErpException {
		try{
			Collection<Periodicidade> periodicidades = periodicidadeDao.getAll(); 
			return new ArrayList<Periodicidade>(periodicidades);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e);
		}
	}
}