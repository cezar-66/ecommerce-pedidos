package br.com.ecommerce_pedidos.config.exceptions;

public class ProdutoSemEstoqueException extends RuntimeException {

    public ProdutoSemEstoqueException(String message) {
        super(message);
    }
}
