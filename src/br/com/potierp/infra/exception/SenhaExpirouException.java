package br.com.potierp.infra.exception;

public class SenhaExpirouException extends RuntimeException {

	/**
	 * Exceção para ser gerada quando a senha do usuário está expirada.
	 * Assim pode-se fazer a verificação e redirecionamento para a alteração de senha.
	 */
	private static final long serialVersionUID = 8371977128216151095L;

	public SenhaExpirouException() {
		super();
	}

	public SenhaExpirouException(final String message) {
		super(message);
	}
}
