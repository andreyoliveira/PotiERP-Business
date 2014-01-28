package br.com.potierp.auditoria;

/**
 * Adiciona/Formata os campos que serão auditados.
 * 
 * @author
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public class AuditoriaBuilder {
	
	/**
	 * Valores do campos que serão logados.
	 */
	private StringBuilder descricaoAuditoria;
	
	
	/**
	 * Construtor.
	 */
	public AuditoriaBuilder(){
		this.descricaoAuditoria = new StringBuilder();
	}
	
	/**
	 * Adiciona os campos/valores para a realização do log.
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public AuditoriaBuilder append(final String propertyName, final Object propertyValue){
		descricaoAuditoria.append(propertyName);
		descricaoAuditoria.append(" = [");
		descricaoAuditoria.append(propertyValue);
		descricaoAuditoria.append("] ");
		return this;
	}
	
	/**
	 * @return
	 */
	public String toAuditoria(){
		return this.descricaoAuditoria.toString();
	}

}