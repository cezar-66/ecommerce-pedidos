package br.com.ecommerce_pedidos.controller;

import br.com.ecommerce_pedidos.model.Usuario;
import br.com.ecommerce_pedidos.service.RelatorioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/top-usuarios")
    public List<Usuario> getTop5UsuariosQueMaisCompraram() {
        return relatorioService.getTop5UsuariosQueMaisCompraram();
    }

    @GetMapping("/ticket-medio")
    public List<Object[]> getTicketMedioPorUsuario() {
        return relatorioService.getTicketMedioPorUsuario();
    }

    @GetMapping("/faturamento-mes")
    public BigDecimal getValorTotalFaturadoNoMes() {
        return relatorioService.getValorTotalFaturadoNoMes();
    }
}
