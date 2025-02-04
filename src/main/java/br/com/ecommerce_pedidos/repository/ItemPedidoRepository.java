package br.com.ecommerce_pedidos.repository;

import br.com.ecommerce_pedidos.model.ItemPedido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemPedidoRepository extends ElasticsearchRepository<ItemPedido, Long> {}

