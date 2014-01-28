package br.com.potierp.util.file;


/**
 * Complementa o nome de arquivos. Por exemplo, complementar o nome com milesegundos do momento de execucao.
 */
public class ComplementaNomeArquivo {

	/**
	 * Separador padrao.
	 */
	private static final char SEPARATOR_DEFAULT = '_';

	/**
	 * Separador do objeto.
	 */
	private char separator;

	/**
	 * Construtor default.
	 */
	public ComplementaNomeArquivo(){
		this.separator = SEPARATOR_DEFAULT;
	}

	/**
	 * Construtor recebe um separador.
	 * @param separator
	 */
	public ComplementaNomeArquivo(final char separator){
		this.separator = separator;
	}

	/**
	 * 
	 * @param baseName
	 * @return
	 */
	public String withMilliSeconds(final String baseName) {
		if (baseName == null || "".equals(baseName))
			return baseName;
		long milliSeconds = System.currentTimeMillis();
		return baseName.concat(""+this.separator).concat(""+milliSeconds);
	}

}
