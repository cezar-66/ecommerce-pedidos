package br.com.ecommerce_pedidos.service;

import br.com.ecommerce_pedidos.repository.UsuarioRepository;
import br.com.ecommerce_pedidos.model.Usuario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RelatorioService {

    private final UsuarioRepository usuarioRepository;

    public RelatorioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getTop5UsuariosQueMaisCompraram() {
        return usuarioRepository.findTop5UsuariosQueMaisCompraram();
    }

    public List<Object[]> getTicketMedioPorUsuario() {
        return usuarioRepository.findTicketMedioPorUsuario();
    }

    public BigDecimal getValorTotalFaturadoNoMes() {
        return usuarioRepository.findValorTotalFaturadoNoMes();
    }
}
