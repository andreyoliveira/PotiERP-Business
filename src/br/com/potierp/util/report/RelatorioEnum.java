package br.com.potierp.util.report;

public enum RelatorioEnum {
	
	TESTE("Teste", "Teste.jasper");

	private RelatorioEnum(String nomeArquivoSaida, String nomeArquivoJasper){
		this.nomeArquivoSaida = nomeArquivoSaida;
		this.nomeArquivoJasper = nomeArquivoJasper;
	}
	
	private String nomeArquivoSaida;
	
	private String nomeArquivoJasper;
	
	public String getNomeArquivoSaida(){
		return this.nomeArquivoSaida;
	}
	
	public String getNomeArquivoJasper(){
		return this.nomeArquivoJasper;
	}
}
