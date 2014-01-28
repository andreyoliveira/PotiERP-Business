package br.com.potierp.util.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Define contrato para manipulaçãoo de arquivos, em algum repositório.
 */
public interface GerenciadorArquivos {

	/**
	 * Faz a busca do arquivo, e retorna se conteudo.
	 * @param path do diretorio.
	 * @param fileName
	 * @return
	 * @throws IOException caso algo falhe.
	 */
	byte[] downloadArquivo(String path, String fileName) throws IOException;

	/**
	 * Escreve o conteudo do File no OutputStream. Essa versao pode ser usada em um servlet por exemplo.
	 * @param file
	 * @param output
	 * @throws IOException
	 */
	void downloadArquivo(final File file, final OutputStream output) throws IOException;

	/**
	 * Retorna a data de ultima modificacao do arquivo.
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	long getUltimaModificacao(final String path, final String fileName) throws IOException;

	/**
	 * Exclui o arquivo, retorna boolean indicando se foi possivel realizar operacao.
	 * @param path do diretorio.
	 * @param fileName
	 * @return
	 * @throws IOException caso algo falhe.
	 */
	boolean apagarArquivo(String path, String fileName) throws IOException;

	/**
	 * Exclui o diret�rio.
	 * @param path do diretorio.
	 * @return
	 * @throws IOException caso algo falhe.
	 */
	boolean apagarDiretorio(String path) throws IOException;

	/**
	 * Cria o arquivo, se existir substitui.
	 * @param path
	 * @param fileName
	 * @param file
	 * @return
	 * @throws IOException caso algo falhe.
	 */
	boolean gravarArquivo(String path, String fileName, byte[] data) throws IOException;

	/**
	 * Atualiza o arquivo existente.
	 * @param path
	 * @param fileName
	 * @param file
	 * @return
	 * @throws IOException caso algo falhe.
	 */
	boolean atualizarArquivo(String path, String fileName, byte[] data) throws IOException;

}