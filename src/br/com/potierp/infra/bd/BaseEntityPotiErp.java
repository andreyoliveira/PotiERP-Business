package br.com.potierp.infra.bd;

import br.com.potierp.core.Identifiable;
import br.com.potierp.model.BaseEntity;

/**
 * Classe abstrata que faz a integração do BaseEntity de service com as entidades do PotiErp.
 *
 * @author
 * 
 *  	   <p>
 *         $LastChangedBy:$
 *         <p>
 *         $LastChangedDate: $
 */
public abstract class BaseEntityPotiErp implements BaseEntity, Identifiable{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 3283715684150314733L;

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.model.BaseEntity#isNew()
	 */
	public boolean isNew() {
		return getId() == null || Long.valueOf(0).equals(getId());
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.core.Identifiable#getIdentity()
	 */
	public Object getIdentity() {
		return this.getId();
	}
	
	/**
	 * (non-Javadoc).
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}