package br.com.potierp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Cargo;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.CorEnum;
import br.com.potierp.model.Ctps;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.model.Pessoa;
import br.com.potierp.model.Rg;
import br.com.potierp.model.SituacaoFuncionario;
import br.com.potierp.util.CriteriaUtils;
import br.com.potierp.util.DateUtil;
import br.com.potierp.util.EntityUtils;

public class FuncionarioDao extends BaseDao {

	public FuncionarioDao() throws DaoException {
		super();
	}
	
	public Funcionario getPorRe(final Funcionario funcionario) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Funcionario.GET_BY_RE);
			query.setParameter("re", funcionario.getCodigoRegistro());
			return carregarListasFunc((Funcionario)getSingleResult(query));
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO", "RE"}, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> getPorNome(final String prefixo) throws DaoException{
		try {
			Query query = getEntityManager().createNamedQuery(Funcionario.GET_POR_NOME);
			query.setParameter("nome", prefixo + "%");
			return carregarListas(query.getResultList());
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"FUNCIONARIO", "NOME"}, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> getPorDataAdmissao(final Date dataInicial,
			final Date dataFinal, final Cidade cidade,
			final Collection<Cliente> clientes) throws DaoException {
		try {
			Criteria c = createCriteria(Funcionario.class);
			c.createAlias("situacaoFuncionario", "sf", Criteria.INNER_JOIN);
			c.createAlias("locaisTrabalho", "loc", Criteria.LEFT_JOIN);
			c.createAlias("loc.cliente", "cli", Criteria.LEFT_JOIN);
			CriteriaUtils.addEq(c, "sf.codigo", 1L);
			CriteriaUtils.addIn(c, "loc.cliente", clientes);
			CriteriaUtils.addEq(c, "cli.cidade", cidade);
			CriteriaUtils.addBetween(c, "dataAdmissao", DateUtil.addDias(dataInicial, -44), DateUtil.addDias(dataFinal, -89));
			c.addOrder(Order.asc("dataAdmissao"));
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return carregarListas(c.list());
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[] {"FUNCIONARIO", "DATA ADMISSAO"}, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> getPorCriterios(final Cliente cliente, final SituacaoFuncionario situacaoFuncionario, 
			final int firstRow, final int lastRow) throws DaoException{
		try{
			Criteria c = createCriteria(Funcionario.class);
			c.createAlias("locaisTrabalho", "loc", Criteria.LEFT_JOIN);
			CriteriaUtils.addEq(c, "loc.cliente.id", cliente !=null?cliente.getId():null);
			CriteriaUtils.addEq(c, "situacaoFuncionario.id", situacaoFuncionario!=null?situacaoFuncionario.getId():null);
			c.addOrder(Order.asc("codigoRegistro"));
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return carregarListas(c.list());
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Funcionario> getPorCriteriosPesq(final Cliente cliente, final SituacaoFuncionario situacaoFuncionario) throws DaoException{
		try{
			Criteria c = createCriteria(Funcionario.class);
			c.createAlias("locaisTrabalho", "loc", Criteria.LEFT_JOIN);
			CriteriaUtils.addEq(c, "loc.cliente.id", cliente !=null?cliente.getId():null);
			CriteriaUtils.addEq(c, "situacaoFuncionario.id", situacaoFuncionario!=null?situacaoFuncionario.getId():null);
			c.addOrder(Order.asc("codigoRegistro"));
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return c.list();
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public Collection<Funcionario> getAllComNomeRe() throws DaoException{
		try {
			String nativeQuerySugestao = getNativeQueryGetAllComNomeRe();
			Query query = getEntityManager().createNativeQuery(nativeQuerySugestao);
			List<Object[]> result = query.getResultList();
			List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
			for (Object[] object : result) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(EntityUtils.getLong(object[0]));
				funcionario.setCodigoRegistro(EntityUtils.getLong(object[1]));
				funcionario.setPessoa(new Pessoa());
				funcionario.getPessoa().setNome(EntityUtils.getString(object[2]));
				funcionario.setSalario(new BigDecimal(object[3].toString()));
				funcionario.setCargo(new Cargo());
				funcionario.getCargo().setId(EntityUtils.getLong(object[4]));
				listaFuncionario.add(funcionario);
			}
			return listaFuncionario;
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Funcionario> getByCalculoValeTransporte(final Long idCalculoValeTransporte) throws DaoException{
		try {
			String nativeQuerySugestao = getNativeQueryGetByCalculoValeTransporte(idCalculoValeTransporte);
			Query query = getEntityManager().createNativeQuery(nativeQuerySugestao);
			List<Object[]> result = query.getResultList();
			List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
			for (Object[] object : result) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(EntityUtils.getLong(object[0]));
				funcionario.setCodigoRegistro(EntityUtils.getLong(object[1]));
				funcionario.setPessoa(new Pessoa());
				funcionario.getPessoa().setNome(EntityUtils.getString(object[2]));
				funcionario.setSalario(new BigDecimal(object[3].toString()));
				funcionario.setCargo(new Cargo());
				funcionario.getCargo().setId(EntityUtils.getLong(object[4]));
				listaFuncionario.add(funcionario);
			}
			return listaFuncionario;
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Funcionario> getPorCriteriosPesq(final Cliente cliente, final SituacaoFuncionario situacaoFuncionario) throws DaoException{
		try {
			String nativeQueryTurmaFechamento = getNativeQueryFuncionarios(cliente, situacaoFuncionario);			
			Query query = getEntityManager().createNativeQuery(nativeQueryTurmaFechamento);
			List<Object[]> result = query.getResultList();
			List<Funcionario> listaFechamentoTurma = new ArrayList<Funcionario>();
			for(Object[] object: result){
				Funcionario funcionario = new Funcionario();
				funcionario.setId(EntityUtils.getLong(object[0]));
				funcionario.setCodigoRegistro(EntityUtils.getLong(object[1]));
				funcionario.setPessoa(new Pessoa());
				funcionario.getPessoa().setNome(EntityUtils.getString(object[2]));
				funcionario.setCtps(new Ctps());
				funcionario.getCtps().setCtps(EntityUtils.getLong(object[3]));
				funcionario.getCtps().setSerie(EntityUtils.getLong(object[4]));
				funcionario.setRg(new Rg());
				funcionario.getRg().setRg(EntityUtils.getString(object[5]));
				funcionario.setCpf(EntityUtils.getString(object[6]));
				funcionario.setCargo(new Cargo());
				funcionario.getCargo().setCargo(EntityUtils.getString(object[7]));
				funcionario.setDataAdmissao((Date)object[8]);
				if(object[9] != null){
					funcionario.setSituacaoFuncionario(new SituacaoFuncionario());
					funcionario.getSituacaoFuncionario().setCorEnum(CorEnum.values()[EntityUtils.getLong(object[9]).intValue()]);
				}
				listaFechamentoTurma.add(funcionario);
			}
			return listaFechamentoTurma;
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}
	
	private String getNativeQueryFuncionarios(final Cliente cliente, final SituacaoFuncionario situacaoFuncionario) {
		
		StringBuffer nativeQuery = new StringBuffer();
		String barraN = "\n";
		
		nativeQuery.append("SELECT ").append(barraN);
		nativeQuery.append("distinct f.id, f.codigoRegistro, p.nome, f.ctps, f.serieCtps, f.rg, f.cpf, c.cargo, f.dataAdmissao, s.codigoCor ").append(barraN);
		nativeQuery.append("FROM ").append(barraN);
		nativeQuery.append("funcionario f inner join pessoa p on f.idPessoa = p.id left join localtrabalho l on f.id = l.idFuncionario left join cargo c on f.idCargo = c.id left join situacaofuncionario s on f.idSituacaoFuncionario = s.id ").append(barraN);
		if(cliente != null && cliente.getId() != null && situacaoFuncionario != null && situacaoFuncionario.getId() != null){
			nativeQuery.append("WHERE ").append(barraN);
			nativeQuery.append(" l.idCliente = "+ cliente.getId()).append(barraN);
			nativeQuery.append(" AND ").append(barraN);
			nativeQuery.append(" s.id = "+ situacaoFuncionario.getId()).append(barraN);
		} else if(cliente != null && cliente.getId() != null){
			nativeQuery.append("WHERE ").append(barraN);
			nativeQuery.append(" l.idCliente = "+ cliente.getId()).append(barraN);
		}else if(situacaoFuncionario != null && situacaoFuncionario.getId() != null){
			nativeQuery.append("WHERE ").append(barraN);
			nativeQuery.append(" s.id = "+ situacaoFuncionario.getId()).append(barraN);
		}
		nativeQuery.append(" ORDER BY f.codigoRegistro");
		return nativeQuery.toString();
	}
	
	private String getNativeQueryGetAllComNomeRe() {
		
		StringBuffer nativeQuery = new StringBuffer();
		String barraN = "\n";
		
		nativeQuery.append("SELECT ").append(barraN);
		nativeQuery.append(" f.id, f.codigoRegistro, p.nome, f.salario, f.idCargo ").append(barraN);
		nativeQuery.append("FROM ").append(barraN);
		nativeQuery.append("funcionario f inner join pessoa p on f.idPessoa = p.id ").append(barraN);
		
		return nativeQuery.toString();
	}
	
	private String getNativeQueryGetByCalculoValeTransporte(final Long idCalculoValeTransporte){
		StringBuffer nativeQuery = new StringBuffer();
		String barraN = "\n";
		
		nativeQuery.append("SELECT ").append(barraN);
		nativeQuery.append(" f.id, f.codigoRegistro, p.nome, f.salario, f.idCargo ").append(barraN);
		nativeQuery.append("FROM ").append(barraN);
		nativeQuery.append("funcionario f inner join pessoa p on f.idPessoa = p.id ").append(barraN);
		nativeQuery.append("inner join calculovaletransporte c on c.idfuncionario = f.id and c.id = "+ idCalculoValeTransporte).append(barraN);
		
		return nativeQuery.toString();
	}

	
	private List<Funcionario> carregarListas(final List<Funcionario> funcionarios) {
		for(Funcionario funcionario : funcionarios){
			for(LocalTrabalho localTrabalho : funcionario.getLocaisTrabalho()) {
				localTrabalho.getJornadaTrabalho().getIntervalosJornada().size();
				localTrabalho.getCliente().getEmpresa().getFiliais().size();
				localTrabalho.getCliente().getFiliais().size();
			}
			funcionario.getDependentes().size();
			funcionario.getValesTransporte().size();
			funcionario.getValesRefeicao().size();
			if(funcionario.getCestaBasica() != null)
				funcionario.getCestaBasica().getId();
		}
		
		return funcionarios;
	}
	
	public Funcionario getFullPorId(final Long idFuncionario) throws DaoException{
		try{
			Funcionario funcionario = this.getByPrimaryKey(idFuncionario);
			return carregarListasFunc(funcionario);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO", "ID FUNCIONARIO"}, ex);
		}
	}
	
	private Funcionario carregarListasFunc(final Funcionario funcionario) {
		if(funcionario != null){
			Collections.sort(funcionario.getLocaisTrabalho());
			for(LocalTrabalho localTrabalho : funcionario.getLocaisTrabalho()) {
				localTrabalho.getJornadaTrabalho().getIntervalosJornada().size();
				localTrabalho.getCliente().getEmpresa().getFiliais().size();
				localTrabalho.getCliente().getFiliais().size();
			}
			funcionario.getDependentes().size();
			funcionario.getValesTransporte().size();
			funcionario.getValesRefeicao().size();
			if(funcionario.getCestaBasica() != null)
				funcionario.getCestaBasica().getId();
		}
		return funcionario;
	}

	public Number getCountPorCriterios(final Cliente cliente, final SituacaoFuncionario situacaoFuncionario) throws DaoException{
		try{
			List<Funcionario> listFuncionarios = getPorCriteriosPesq(cliente, situacaoFuncionario);
			return listFuncionarios.size();
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FUNCIONARIO"}, ex);
		}
	}
	
	public List<Funcionario> getAptosValeTransporte() throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Funcionario.GET_APTOS_VALETRANSPORTE);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"SITUACAO TRABALHANDO"}, ex);
		}
	}
	
	public List<Funcionario> getAptosValeRefeicao() throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Funcionario.GET_APTOS_VALEREFEICAO);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"SITUACAO TRABALHANDO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Funcionario.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll(){
		return Funcionario.COUNT_ALL;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Funcionario.class;
	}
}