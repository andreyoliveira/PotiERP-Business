package br.com.potierp.model;

public enum DiaSemanaEnum {
	
	SEGUNDA("Segunda", 2),
	
	TERCA("Terça", 3),
	
	QUARTA("Quarta", 4),
	
	QUINTA("Quinta", 5),
	
	SEXTA("Sexta", 6),
	
	SABADO("Sabado", 7),
	
	DOMINGO("Domingo", 1);
	
	private String diaSemana;
	
	private int diaCalendar;
	
	private DiaSemanaEnum(final String diaSemana, final int diaCalendar) {
		this.diaSemana = diaSemana;
		this.diaCalendar = diaCalendar;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public int getDiaCalendar() {
		return diaCalendar;
	}
}