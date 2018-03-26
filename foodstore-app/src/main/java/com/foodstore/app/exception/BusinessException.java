package com.foodstore.app.exception;

/**
 * Classe de Exceção de Negócio.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1598356443758347218L;

    /**
     * Instancia uma nova business exception.
     *
     * @param exceptionKey Chave da exceção no repositório de Mensagens
     */
    public BusinessException(final String exceptionKey) {
        super(exceptionKey);
    }

    /**
     * Instancia uma nova business exception.
     *
     * @param exceptionKey Chave da exceção no repositório de Mensagens
     * @param cause Causa raiz da exceção
     */
    public BusinessException(final String exceptionKey, final Exception cause) {
        super(exceptionKey, cause);
    }

}
