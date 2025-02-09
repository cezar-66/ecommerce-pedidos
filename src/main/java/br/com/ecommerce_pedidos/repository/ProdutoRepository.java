package br.com.ecommerce_pedidos.repository;

import br.com.ecommerce_pedidos.domain.Produto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends ElasticsearchRepository<Produto, Long> {

    List<Produto> findByNomeContainingAndCategoriaContainingAndPrecoBetweenAndEstoqueGreaterThan(
            String nome, String categoria, BigDecimal precoMin, BigDecimal precoMax, Integer estoque);
}