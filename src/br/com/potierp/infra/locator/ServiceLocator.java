package br.com.potierp.infra.locator;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import br.com.potierp.infra.resource.PotiErpProperties;
import br.com.potierp.infra.resource.PotiErpPropertiesUtil;

/**
 * Service Locator para as chamadas dos Beans remoto para a camada de negócio.
 *
 * @author 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class ServiceLocator {

	/**
	 * Logger.
	 */
	private final Logger logger = Logger.getLogger(ServiceLocator.class);

	/**
	 * Property providerUrl.
	 */
	private static final String EJB_PROVIDER_URL = "java.naming.provider.url";

	/**
	 * Property Factory Initial.
	 */
	private static final String EJB_FACTORY_INITIAL = "java.naming.factory.initial";

	/**
	 * Property Url Pkgs.
	 */
	private static final String EJB_URL_PKGS = "java.naming.url.pkgs";

	/**
	 * Instância de ServiceLocator.
	 */
	private static final ServiceLocator instance;

	/**
	 * Configurações para o lookup de Ejbs.
	 */
	private Hashtable<String, String> potiErpConfig;

	/**
	 * Map que armazena ejbs em cache.
	 */
	private final Map<String, Object> potiErpEjbsCache = new HashMap<String, Object>();

	/**
	 * Carregando o ServiceLocator.
	 */
	static{
		instance = new ServiceLocator();
	}

	/**
	 * Construtor.
	 */
	private ServiceLocator() {
		loadLogosServiceLocatorConfig();
	}

	/**
	 * Carrega as configurações para o lookup de ejb.
	 */
	private void loadLogosServiceLocatorConfig() {
		try {
			PotiErpProperties jndi = PotiErpProperties.getInstance();
			potiErpConfig = new Hashtable<String, String>();
			potiErpConfig.put(EJB_PROVIDER_URL, jndi.getProperty(PotiErpPropertiesUtil.EJB_PROVIDER_URL));
			potiErpConfig.put(EJB_FACTORY_INITIAL, jndi.getProperty(PotiErpPropertiesUtil.EJB_FACTORY_INITIAL));
			potiErpConfig.put(EJB_URL_PKGS, jndi.getProperty(PotiErpPropertiesUtil.EJB_URL_PKGS));
			logger.info("Configurações para o lookup dos EJB 3.0 lidas");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	/**
	 * Recupera ejb remoto.
	 *
	 * @param <T>
	 * @param ejbName
	 * @throws NamingException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getPotiErpEjb(final PotiErpEjbEnum ejb) throws NamingException{
		if(instance.potiErpEjbsCache.containsKey(ejb.getEjbName())){
			return (T) instance.potiErpEjbsCache.get(ejb.getEjbName());
		}else{
			InitialContext ic = new InitialContext(instance.potiErpConfig);
			Object obj = ic.lookup(ejb.getEjbName());
			instance.potiErpEjbsCache.put(ejb.getEjbName(), obj);
			return (T) obj;
		}
	}
	
	public static final ServiceLocator getInstance(){
		return instance;
	}
	
}