package br.com.potierp.infra.locator;

/**
 * Enum que classifica os Ejb's remotos disponíveis no PotiErp.
 *
 * @author 
 * 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public enum PotiErpEjbEnum {

	/**
	 * Ejb default.
	 */
	DEFAULT(""),

	/**
	 * Ejb do modulo Administrativo.
	 */
	ADMINISTRATIVO("AdmModulo"),
	
	/**
	 * Ejb do modulo de RH.
	 */
	RH("RhModulo");

	/**
	 * Service Name.
	 */
	private String ejbName;

	/**
	 * @param serviceName
	 */
	private PotiErpEjbEnum(final String ejbName) {
		this.ejbName = ejbName;
	}

	/**
	 * @return
	 */
	public String getEjbName() {
		return ejbName;
	}
}
