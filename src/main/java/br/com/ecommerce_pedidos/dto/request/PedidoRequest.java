package br.com.ecommerce_pedidos.dto.request;

import br.com.ecommerce_pedidos.domain.ItemPedido;

import java.util.List;

public record PedidoRequest(List<ItemPedido> itens) {}