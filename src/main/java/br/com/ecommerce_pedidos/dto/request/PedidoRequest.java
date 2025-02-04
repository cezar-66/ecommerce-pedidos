package br.com.ecommerce_pedidos.dto.request;

import br.com.ecommerce_pedidos.model.ItemPedido;

import java.util.List;

public record PedidoRequest(List<ItemPedido> itens) {}