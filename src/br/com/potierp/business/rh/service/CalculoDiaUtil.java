package br.com.potierp.business.rh.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.potierp.model.Feriado;
import br.com.potierp.model.TipoFeriadoEnum;
import br.com.potierp.util.DateUtil;

public class CalculoDiaUtil implements Serializable{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 8025987842245633483L;
	
	private Integer diaUtil;
	
	private Date dataReferencia;
	
	private List<Feriado> feriados;
	
	private Date dataDiaUtil;
	
	public CalculoDiaUtil(final Integer diaUtil, final Date dataReferencia,
			final List<Feriado> feriados) {
		this.feriados = feriados;
		this.diaUtil = diaUtil;
		this.dataReferencia = dataReferencia;
	}
	
	public void calcular() throws Exception{
		Calendar calDiaUtil = DateUtil.convertDateToCalendar(dataReferencia);
		if(diaUtil != null && diaUtil > 0){
			Integer dia = 1;
			calDiaUtil.set(Calendar.DAY_OF_MONTH, dia);
			
			while(diaUtil > 0){
				Boolean isFeriado = isFeriado(calDiaUtil);
				Boolean isSabado = isSabado(calDiaUtil);
				Boolean isDomingo = isDomingo(calDiaUtil);
				if(!isFeriado && !isSabado && !isDomingo){
					diaUtil--;
				}
				if(diaUtil > 0){
					dia++;
					calDiaUtil.set(Calendar.DAY_OF_MONTH, dia);
				}
			}
		}
		dataDiaUtil = calDiaUtil.getTime();
	}
	
	private Boolean isFeriado(final Calendar calDiaUtil) {
		if(feriados != null){
			for(Feriado feriado : feriados){
				if(feriado.getData().compareTo(calDiaUtil.getTime()) == 0 
						&& feriado.getTipoFeriado().equals(TipoFeriadoEnum.NACIONAL)){
					return true;
				}
			}
		}
		return false;
	}
	
	private Boolean isSabado(final Calendar calDiaUtil) {
		return DateUtil.isSabado(calDiaUtil);
	}

	private Boolean isDomingo(final Calendar calDiaUtil) {
		return DateUtil.isDomingo(calDiaUtil);
	}
	
	public Date getDiaUtilDoMes(){
		return this.dataDiaUtil;
	}
}