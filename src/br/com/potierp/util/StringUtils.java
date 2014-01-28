package br.com.potierp.util;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Utilit�rios para String.
 * 
 * @author
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public final class StringUtils {
	
	/**
	 * Enum de tags de Html a serem removidas.
	 * @author diogo.carleto
	 *
	 */
	private enum TagsHtmlEnum{
		
		/**
		 * Tag de style.
		 */
		STYLE("<style", "</style>"),
		
		/**
		 * Tag de comentario.
		 */
		COMENTARIO("<!--", "-->"),
		
		/**
		 * tag de xml.
		 */
		XML("<xml", "</xml>");
		
		/**
		 * Inicio da tag.
		 */
		private String valor1;
		
		/**
		 * Fim da tag.
		 */
		private String valor2;
		
		/**
		 * Construtor.
		 * @param valor1
		 * @param valor2
		 */
		TagsHtmlEnum(final String valor1, final String valor2){		
			this.valor1 = valor1;
			this.valor2 = valor2;
		}

		/**
		 * @return the inicio da tag.
		 */
		public String getValor1() {
			return valor1;
		}

		/**
		 * @return the fim da tag.
		 */
		public String getValor2() {
			return valor2;
		}		
	}
		
	/**
	 * Para a normaliza��o dos caracteres de 32 a 255, primeiro caracter.
	 */
	private static final char[] FIRST_CHAR = (" !'#$%&'()*+\\-./0123456789:;<->?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ E ,f'.++^%S<O Z  ''''.-"
			+ "-~Ts>o ZY !C#$Y|$'(a<--(_o+23'u .,1o>113?AAAAAAACEEEEIIIIDNOO"
			+ "OOOXOUUUUyTsaaaaaaaceeeeiiiidnooooo/ouuuuyty").toCharArray();

	/**
	 * Para a normaliza��o dos caracteres de 32 a 255, segundo caracter.
	 */
	private static final char[] SECOND_CHAR = ("  '         ,                                               "
			+ "\\                                   $  r'. + o  E      ''  "
			+ "  M  e     #  =  'C.<  R .-..     ..>424     E E            "
			+ "   E E     hs    e e         h     e e     h ").toCharArray();

	/**
	 * ", ".
	 */
	private static final String VIRGULA_COM_ESPACO = ", ";

	/**
	 * ISO-8859-1.
	 */
	private static final String CHAR_SET_ISO_8859 = "ISO-8859-1";

	/**
	 * Construtor privado da classe utilit�ria.
	 */
	private StringUtils() {
	}

	/**
	 * Compara s1 com s2 ignorando acentos e caixa.
	 * 
	 * @param s1
	 *            String 1 para compara��o.
	 * @param s2
	 *            String 2 para compara��o.
	 * @return Um n < 0 se s1 < s2, n = 0 se s1.equals(s2), n > 0 se s1 > s2.
	 */
	public static int compareIgnoreAccentsAndCase(final String s1,
			final String s2) {
		Collator collator = Collator.getInstance(new Locale("pt", "BR"));
		collator.setStrength(Collator.PRIMARY);
		return collator.compare(s1, s2);
	}

	/**
	 * Compara String's numericas. Converte o conteudo para Long e retorna.
	 * Caso os valores sejam invalidos (strings nao numericas) retorna 0.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareNumbersInString(final String s1,
			final String s2) {
		try {
			Long l1 = Long.valueOf(s1);
			Long l2 = Long.valueOf(s2);
			return l1.compareTo(l2);
		} catch (NumberFormatException n) {
			return 0;
		}
	}

	/**
	 * Normaliza a string, retirando caracteres especiais de 1 e 2 digitos.
	 * Consultar os atributos <i>FIRST_CHAR</i> e <i>SECOND_CHAR</i> para verificar os 
	 * caracteres tradatos.
	 * @param str
	 * @return
	 */
	public static String normalize(final String str) {
		char[] chars = str.toCharArray();
		StringBuffer ret = new StringBuffer(chars.length * 2);
		for (int i = 0; i < chars.length; ++i) {
			char aChar = chars[i];
			if (aChar == ' ' || aChar == '\t') {
				ret.append(' '); // convertido para espa�o
			} else if (aChar > ' ' && aChar < 256) {
				if (FIRST_CHAR[aChar - ' '] != ' ') {
					ret.append(FIRST_CHAR[aChar - ' ']); // 1 caracter
				}
				if (SECOND_CHAR[aChar - ' '] != ' ') {
					ret.append(SECOND_CHAR[aChar - ' ']); // 2 caracteres
				}
			}
		}

		return ret.toString();
	}

	/**
	 * Transforma uma String em um array de bytes com charset ISO-8859-1.
	 * Retorna o conteudo binario em uma String.
	 * @param s
	 * @return
	 */
	public static String convertStringInStringOfBytes(final String s) {
		try {
			return Arrays.toString(s.getBytes(CHAR_SET_ISO_8859));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Recebe uma String composta por elementos de uma array de bytes e converte esse conteudo binario 
	 * em uma String aplicando o charset ISO-8859-1.
	 * @param s
	 * @return
	 */
	public static String convertStringOfBytesInString(final String s) {
		if (s == null || s.length() < 3)
			return "";
		String st = s.substring(1, s.length()-1);
		byte[] buffer = new byte[st.split(VIRGULA_COM_ESPACO).length];
		int i = 0;
		Scanner sc = new Scanner(st);
		sc.useDelimiter(VIRGULA_COM_ESPACO);
		while (sc.hasNextByte()) {
			buffer[i++] = sc.nextByte();
		}
		try {
			return new String(buffer,CHAR_SET_ISO_8859);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * M�todo respons�vel por retirar elementos do html.<br> 
	 * Consultar lista de tags <i>TagsHtmlEnum</i>
	 * @param str
	 * @return
	 */
	public static String limpaHtml(final String str){
		String retorno = str;
		StringBuilder tmpRetorno = null;
		for (TagsHtmlEnum t: TagsHtmlEnum.values()) {
			while (retorno.indexOf(t.getValor1()) != -1) {
				int posInicio = retorno.indexOf(t.getValor1());
				int posFim = retorno.indexOf(t.getValor2(), posInicio);
				tmpRetorno = new StringBuilder(retorno.substring(0, posInicio));
				tmpRetorno.append(retorno.substring(posFim+t.getValor2().length()));
				retorno = tmpRetorno.toString();
			}
		}
		return retorno.replaceAll("&nbsp;", " ").replaceAll( "<[^>]*>", "");
	}
}