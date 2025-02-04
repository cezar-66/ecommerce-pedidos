package br.com.ecommerce_pedidos.dto.request;

public record CadastrarUsuarioRequestDTO(String name, String email, String password, String perfil) {
}