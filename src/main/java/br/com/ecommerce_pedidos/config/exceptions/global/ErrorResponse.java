package br.com.ecommerce_pedidos.config.exceptions.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String tipoErro;
    private String mensagem;

    public ErrorResponse(String tipoErro, String mensagem) {
        this.tipoErro = tipoErro;
        this.mensagem = mensagem;
    }
}
