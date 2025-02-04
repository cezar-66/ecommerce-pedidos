package br.com.ecommerce_pedidos.controller;

import br.com.ecommerce_pedidos.dto.request.PedidoRequest;
import br.com.ecommerce_pedidos.model.Pedido;
import br.com.ecommerce_pedidos.model.Usuario;
import br.com.ecommerce_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criarPedido")
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequest pedidoRequest,
                                              @AuthenticationPrincipal Usuario usuario) {
        Pedido pedido = pedidoService.criarPedido(usuario.getId(), pedidoRequest.itens());
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PostMapping("/{pedidoId}/pagamento")
    public ResponseEntity<Pedido> processarPagamento(@PathVariable Long pedidoId,
                                                     @AuthenticationPrincipal Usuario usuario) {
        Pedido pedido = pedidoService.processarPagamento(pedidoId, usuario.getId());
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }
}