package br.com.potierp.util.report;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public abstract class RelatorioIText extends PdfPageEventHelper{
	
	protected static final String ESPACO = " ";
	
	/**
	 * Construtor sobrecarregado.
	 * @param parametros
	 * @param titulo
	 */
	protected RelatorioIText(final Map<String, Object> parametros, final String titulo){
		this.parametros = parametros;
		this.titulo = titulo;
	}
	
	/**
	 * Parâmetros para a Geração do Relatório.
	 */
	private Map<String, Object> parametros;
	
	/**
	 * Título do Relatório.
	 */
	private String titulo;
	
	/**
	 * Método responsável pela geração do Relatório.
	 * @return
	 */
	public abstract Element gerarRelatorio();
	
	/**
	 * Método que escreve o relatório.
	 * @param element
	 * @return
	 */
	public byte[] getRelatorio(final Rectangle pageSize, 
			final float marginLeft, final float marginRight, final float marginTop, final float marginBottom){
		try{
			Document document = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setPageEvent(this);
			document.open();
			document.add(this.gerarRelatorio());
			document.close();
			return baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Método que escreve o relatório.
	 * @param element
	 * @return
	 */
	public byte[] getRelatorio(){
		return this.getRelatorio(PageSize.A4, 30, 30, 180, 20);
	}

	/**
	 * @return the parametros
	 */
	public Map<String, Object> getParametros() {
		return parametros;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * @return
	 */
	protected PdfPCell getEmptyCell() {
		PdfPCell celVazia = new PdfPCell(new Phrase(ESPACO));
		celVazia.setBorder(0);
		celVazia.setFixedHeight(5f);
		return celVazia;
	}
	

	/**
	 * @param fixedHeight
	 * @return
	 */
	protected PdfPCell getEmptyCell(final float fixedHeight) {
		PdfPCell celVazia = new PdfPCell(new Phrase(ESPACO));
		celVazia.setBorder(0);
		celVazia.setFixedHeight(fixedHeight);
		return celVazia;
	}
	
	/**
	 * @return
	 */
	protected PdfPCell getLineCell() {
		PdfPCell celVazia = new PdfPCell(new Phrase(ESPACO));
		celVazia.setBorder(1);
		return celVazia;
	}

}
