package br.com.potierp.util;

import static org.hibernate.criterion.Restrictions.between;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.in;
import static org.hibernate.criterion.Restrictions.isNull;
import static org.hibernate.criterion.Restrictions.like;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * Classe utilitaria em operações com o Criteria do Hibernate.
 *
 * @author renan.rodrigues
 */
public class CriteriaUtils {

	/**
	 * Construtor.
	 */
	private CriteriaUtils() {

	}

	/**
	 * Metodo de example com "like" default.
	 *
	 * @param entity
	 * @param matchMode
	 * @return
	 */
	public static Example getLikeExample(final Object entity, final MatchMode matchMode) {
		return Example.create(entity).ignoreCase().enableLike(matchMode);
	}

	/**
	 * Adiciona operador "=" ao criteria.
	 * @param c
	 * @param propertyName
	 * @param value
	 */
	public static void addEq(final Criteria c, final String propertyName, final Object value){
		if(!isStringVazia(value) && value != null)
			c.add(eq(propertyName, value));
	}

	/**
	 * Adiciona operador "like" ao criteria.
	 * @param c
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 */
	public static void addLike(final Criteria c, final String propertyName,
			final String value, final MatchMode matchMode) {
		if(!isStringVazia(value) && (value != null))
			c.add(like(propertyName, value, matchMode).ignoreCase());
	}
	
	/**
	 * Adiciona um "sqlRestriction" ao criteria.
	 * @param c
	 * @param sql
	 * @param value
	 * @param type
	 */
	public static void addSqlRestriction(final Criteria c, final String sql, final Object value, final Type type){
		if(value != null)
			c.add(Restrictions.sqlRestriction(sql, value, type));
	}
	

	/**
	 * Adiciona operador "between" ao criteria.
	 *
	 * @param c
	 * @param propertyName
	 * @param dataInicial
	 * @param dataFinal
	 */
	public static void addBetween(final Criteria c, final String propertyName,
			final Date dataInicial, final Date dataFinal) {
		if(dataInicial != null && dataFinal != null)
			c.add(between(propertyName, dataInicial, dataFinal));
	}

	/**
	 * Adiciona operador "in" ao criteria.
	 * @param c
	 * @param propertyName
	 * @param value
	 */
	public static void addIn(final Criteria c, final String propertyName, final Object ... value) {
		if(value != null)
			c.add(in(propertyName, value));
	}

	/**
	 * Adiciona operador "in" ao criteria.
	 * @param c
	 * @param propertyName
	 * @param value
	 */
	public static void addIn(final Criteria c, final String propertyName, final Collection<?> value) {
		if(value != null && !value.isEmpty())
			c.add(in(propertyName, value));
	}

	/**
	 * Adiciona operador "is null" ao criteria.
	 * @param c
	 * @param propertyName
	 */
	public static void addIsNull(final Criteria c, final String propertyName) {
		c.add(isNull(propertyName));
	}

	/**
	 * Verifica se � uma String vazia.
	 * @param obj
	 * @return
	 */
	private static boolean isStringVazia(final Object obj){
		return  (EntityUtils.getString(obj) != null) && ("".equals(EntityUtils.getString(obj)));
	}
}