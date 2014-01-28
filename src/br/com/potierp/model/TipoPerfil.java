package br.com.potierp.model;

/**
 * Define contrato para TipoPerfil. Utilizado pelo TraceInfo.
 * 
 * @author felipe
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public interface TipoPerfil {

	/**
	 * @return Identificador do rastreável.
	 */
	Long getId();

	/**
	 * @return
	 */
	String getDescricao();

	/**
	 * @return Sigla do rastreável.
	 */
	String getSigla();


}
