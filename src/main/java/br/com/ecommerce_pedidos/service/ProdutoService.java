package br.com.ecommerce_pedidos.service;

import br.com.ecommerce_pedidos.config.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce_pedidos.model.Produto;
import br.com.ecommerce_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarProdutos(String nome, String categoria, BigDecimal precoMin, BigDecimal precoMax) {
        return produtoRepository.findByNomeContainingAndCategoriaContainingAndPrecoBetweenAndEstoqueGreaterThan(
                nome, categoria, precoMin, precoMax, 0);
    }

    public void atualizarEstoqueNoElasticSearch(Long produtoId, Integer novoEstoque) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
        produto.setEstoque(novoEstoque);
        produtoRepository.save(produto);
    }
}
