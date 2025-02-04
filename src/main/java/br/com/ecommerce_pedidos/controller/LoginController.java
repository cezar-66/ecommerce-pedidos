package br.com.ecommerce_pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 Criei essa classe apenas para validar a autenticação!
 Afinal para chamar o recurso é necessario estar autenticado
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("usuario logado com sucesso!");
    }
}