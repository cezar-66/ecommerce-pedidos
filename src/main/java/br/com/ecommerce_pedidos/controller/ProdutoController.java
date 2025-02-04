package br.com.ecommerce_pedidos.controller;

import br.com.ecommerce_pedidos.model.Produto;
import br.com.ecommerce_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax) {

        List<Produto> produtos = produtoService.buscarProdutos(nome, categoria, precoMin, precoMax);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
