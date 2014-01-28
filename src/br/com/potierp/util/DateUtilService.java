package br.com.potierp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaria para manipulacao de datas.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public final class DateUtilService {
	/**
	 * Formato: dd/MM/yyyy.
	 */
	private static final String dd_MM_yyyy = "dd/MM/yyyy";

	/**
	 * Formato: dd/MM/yyyy HH:mm:ss.
	 */
	private static final String dd_MM_yyyy_HH_mm_ss = dd_MM_yyyy+" HH:mm:ss";
	
	/**
	 * Formato: yyyyMMdd.
	 */
	private static final String yyyyMMdd = "yyyyMMdd";

	/**
	 * Formato: yyyyMMddHHmm.
	 */
	private static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    /**
     * Formato: yyyyMMddHHmmss.
     */
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
	 * O formatador com o padr�o do provisionamento.
	 */
	private final SimpleDateFormat sppFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * O formatador com o padr�o ptBr.
	 */
	private final SimpleDateFormat ptBrFormat = new SimpleDateFormat(dd_MM_yyyy);

	/**
	 * O formatador com o padr�o ptBr.
	 */
	private final SimpleDateFormat ptBrComHoraFormat = new SimpleDateFormat(dd_MM_yyyy_HH_mm_ss);

	/**
	 * Formatador com o padr�o de Log.
	 */
	private final SimpleDateFormat logFormat = new SimpleDateFormat("HHmmss");

	/**
	 * DateUtil.
	 */
	private static final DateUtilService instance;

	static {
		instance = new DateUtilService();
	}

	/**
	 * Construtor privado para evitar a instancia��o indevida da classe.
	 */
	private DateUtilService() {}

	/**
	 * Converte String com no formato dd/MM/yyyy em Date.
	 * @param string
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToPtBrDate(final String string) throws ParseException {
		if (string == null || instance == null)
			return null;
		return instance.ptBrFormat.parse(string);
	}

	/**
	 * Converte String com no formato dd/MM/yyyy HH:mm:ss em Date.
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date stringPtBrComHoraToDate(final String source) throws ParseException {
		if (source == null || instance == null) 
			return null;
		return instance.ptBrComHoraFormat.parse(source);
	}

	/**
	 * Transforma um <code>Date</code> em <code>String</code>.
	 * 
	 * @param date
	 * @return <code>String</code> no <i>pattern</i> definido para log.
	 */
	public static String dateToLogTime(final Date date) {
		if (date == null || instance == null)
			return null;
		return instance.logFormat.format(date);
	}

	/**
	 * Nos d� uma <code>String</code> com o padr�o de formata��o usado pelos
	 * converters das p�ginas JSF.
	 * 
	 * @return o padr�o usado pelos converters das p�ginas JSF.
	 */
	public static String getJsfConverterDatePattern() {
		return dd_MM_yyyy;
	}

	/**
	 * Retorna qual o Semestre, conforem a Data Atual.
	 * 
	 * @return
	 */
	public static Integer getSemestre() {
		return getSemestre(new Date());
	}

	/**
	 * Constante Primeiro Semestre.
	 */
	private static final Integer PRIMEIRO_SEMESTRE = 1;

	/**
	 * Constante Segundo Semestre.
	 */
	private static final Integer SEGUNDO_SEMESTRE = 2;

	/**
	 * Retorna qual o Semestre (1� ou 2�), conforme a <i>Data</i> informada.
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getSemestre(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer mes = calendar.get(Calendar.MONTH) + 1;
		if (mes >= 1 && mes < 7)
			return PRIMEIRO_SEMESTRE;

		return SEGUNDO_SEMESTRE;
	}
	
	/**
	 * Compara o dia, m�s e ano de duas datas.
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static int compararDatasSemHoras(final Date data1, final Date data2) {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(yyyyMMdd);
		String dataInicio = format.format(data1);
		String dataFim = format.format(data2);
		
		return dataInicio.compareTo(dataFim);
	}
	
	/**
	 * Compara duas datas completas (ano, m�s, dia, hora e minuto).
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static int compararDatasComHoras(final Date data1, final Date data2) {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(yyyyMMddHHmm);
		
		String dataInicio = format.format(data1);
		String dataFim = format.format(data2);
		
		return dataInicio.compareTo(dataFim);
	}

    /**
     * Compara duas datas completas (ano, m�s, dia, hora, minuto e segundo).
     * @param data1
     * @param data2
     * @return
     */
    public static int compararDatasComSegundos(final Date data1, final Date data2) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(yyyyMMddHHmmss);
        
        String dataInicio = format.format(data1);
        String dataFim = format.format(data2);
        
        return dataInicio.compareTo(dataFim);
    }

    /**
	 * Transforma um <code>Date</code> em <code>String</code>.
	 * 
	 * @param date
	 *            o <code>Date</code> a ser transformada em
	 *            <code>String</code>.
	 * @return a <code>String</code> correspondente ao <code>Date</code>.
	 */
	public static String dateToSppString(final Date date) {
		if (date == null || instance == null)
			return null;
		return instance.sppFormat.format(date);
	}

	/**
	 * Transforma um <code>String</code> em <code>Date</code>.
	 * 
	 * @param string
	 *            a <code>String</code> a ser transformada em
	 *            <code>Date</code>.
	 * @return o <code>Date</code> correspondente ao <code>String</code>.
	 * @throws ParseException
	 *             se a <code>String</code> n�o p�de ser transformada.
	 */
	public static Date sppStringToDate(final String string)
			throws ParseException {
		if (string == null || instance == null)
			return null;
		return instance.sppFormat.parse(string);
	}
}
