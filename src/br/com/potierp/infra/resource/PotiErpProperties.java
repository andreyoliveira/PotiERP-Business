package br.com.potierp.infra.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Classe responsável pela busca das propriedades do Sistema Poti.
 * 
 * @author
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public class PotiErpProperties {

	/**
	 * Variavel de ambiente com o caminho do arquivo de properties de configuração.
	 */
	private static final String CONFIG_FILE_NAME = "config.home.potiErpServiceConfig";

	/**
	 * Caminho do arquivo recuperado pela variavel de ambiente.
	 */
	private static final String CONFIG_FILES;

	/**
	 * Nome padrão do arquivo.
	 */
	private static final String FILE_NAME_DEFAULT = "PotiErpJndi.properties";

	/**
	 * Diretorio padrão.
	 */
	private static final String DIRECTORY_NAME_DEFAULT = "potiErp";

	/**
	 * Instancia da classe de propriedades.
	 */
	private static final PotiErpProperties instance;

	/**
	 * Propriedades do potiErp.
	 */
	private Properties properties;

	/**
	 * Logger.
	 */
	private static final Logger logger = Logger.getLogger(PotiErpProperties.class);

	static {
		CONFIG_FILES = FILE_NAME_DEFAULT;
		if (CONFIG_FILES == null || CONFIG_FILES.equals("")) {
			logger.error("Variavel de ambiente " + CONFIG_FILE_NAME + " não definida.");
			instance = null;
		} else {
			logger.info(CONFIG_FILE_NAME + ": " + CONFIG_FILES);
			instance = new PotiErpProperties();
		}
	}

	/**
	 * Construtor.
	 */
	private PotiErpProperties(){
		loadProperties();
	}

	/**
	 * Carrega o arquivo de propriedades.
	 */
	private void loadProperties() {
		FileInputStream stream = null;

		try {
			 //O diretório onde esta o arquivo de configuração home da instalação java.
            String location     = System.getProperty ("java.home") ;
            String iasLocal     = System.getProperty("config.home");
            File file = null;

			char sep = File.separatorChar;
			if(iasLocal==null) {
				file = new File(location + sep + "lib" + sep + DIRECTORY_NAME_DEFAULT + sep + FILE_NAME_DEFAULT );
			}else {
				file = new File(iasLocal + sep + DIRECTORY_NAME_DEFAULT + sep + FILE_NAME_DEFAULT);
			}

			stream = new FileInputStream(file);
			properties = new Properties();
			properties.load(stream);
		}catch(IOException ioe) {
			logger.error("Não foi possível carregar o arquivo de propriedades do projeto PotiErp-Business."
									+ ioe.getMessage(), ioe);
			ioe.printStackTrace();
			throw new IllegalStateException(ioe.getMessage(), ioe);
		}finally {
			try {
				stream.close();
			} catch (IOException ioe) {
				logger.error("Erro no stream de comunicação com o arquivo de propriedades.");
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * Retorna instancia da classe de propriedades.
	 */
	public static PotiErpProperties getInstance() {
		return instance;
	}

	/**
	 * Retorna uma propriedade específica.
	 * @param propertie
	 * @return
	 */
	public String getProperty(final String propertie) {
		return properties.getProperty(propertie);
	}
	
	/**
	 * @return nome do arquivo de configuracao.
	 */
	public static String getConfigFileName() {
		return CONFIG_FILE_NAME;
	}
	
	/**
	 * Diretorio de gerecao de relatorios.
	 * @return
	 */
	public String getReportPath() {
		if (getInstance() == null)
			return null;
		return (String) properties.get("jndi.arquivosTemporarios");
	}
	
	public String getJasperPath(){
		if(getInstance() == null){
			return null;
		}
		return (String) properties.get("report.jasper.path");
	}
}