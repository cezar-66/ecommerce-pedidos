package br.com.ecommerce_pedidos.config.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super(String.valueOf(id));
    }

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
