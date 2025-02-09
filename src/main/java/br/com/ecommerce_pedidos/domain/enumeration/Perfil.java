package br.com.ecommerce_pedidos.domain.enumeration;

import lombok.Getter;

@Getter
public enum Perfil {
    ADMIN("ADMIN"),
    USER("USER");

    private final String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }
}
