package br.com.ecommerce_pedidos.repository;

import br.com.ecommerce_pedidos.domain.ItemPedido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemPedidoRepository extends ElasticsearchRepository<ItemPedido, Long> {}

