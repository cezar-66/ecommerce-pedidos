package br.com.ecommerce_pedidos.config.exceptions.global;

import br.com.ecommerce_pedidos.config.exceptions.PedidoNaoEncontradoException;
import br.com.ecommerce_pedidos.config.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce_pedidos.config.exceptions.ProdutoSemEstoqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro de execução", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoSemEstoqueException.class)
    public ResponseEntity<ErrorResponse> handleProdutoSemEstoqueException(ProdutoSemEstoqueException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Estoque Insuficiente", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Pedido não encontrado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlePedidoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Produto não encontrado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro inesperado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}