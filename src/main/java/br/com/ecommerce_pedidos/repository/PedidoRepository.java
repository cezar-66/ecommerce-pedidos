package br.com.ecommerce_pedidos.repository;

import br.com.ecommerce_pedidos.domain.Pedido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PedidoRepository extends ElasticsearchRepository<Pedido, Long> {
}
