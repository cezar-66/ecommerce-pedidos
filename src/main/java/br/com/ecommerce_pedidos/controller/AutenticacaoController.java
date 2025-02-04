package br.com.ecommerce_pedidos.controller;

import br.com.ecommerce_pedidos.dto.request.LoginRequestDTO;
import br.com.ecommerce_pedidos.dto.request.CadastrarUsuarioRequestDTO;
import br.com.ecommerce_pedidos.dto.response.ResponseDTO;
import br.com.ecommerce_pedidos.service.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AutenticacaoService authService;

    public AutenticacaoController(AutenticacaoService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        ResponseDTO response = authService.login(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> cadastrarUsuario(@RequestBody CadastrarUsuarioRequestDTO body) {
        ResponseDTO response = authService.cadastrarUsuario(body);
        return ResponseEntity.ok(response);
    }
}