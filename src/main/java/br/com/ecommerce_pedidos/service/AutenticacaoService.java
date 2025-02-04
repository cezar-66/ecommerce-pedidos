package br.com.ecommerce_pedidos.service;

import br.com.ecommerce_pedidos.model.Usuario;
import br.com.ecommerce_pedidos.dto.request.LoginRequestDTO;
import br.com.ecommerce_pedidos.dto.request.CadastrarUsuarioRequestDTO;
import br.com.ecommerce_pedidos.dto.response.ResponseDTO;
import br.com.ecommerce_pedidos.model.enumeration.Perfil;
import br.com.ecommerce_pedidos.config.security.TokenService;
import br.com.ecommerce_pedidos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseDTO login(LoginRequestDTO body){
        Usuario user = this.usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(passwordEncoder.matches(body.password(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return new ResponseDTO(user.getNome(), token);
        }

        throw new RuntimeException("Credenciais inválidas");
    }

    public ResponseDTO cadastrarUsuario(CadastrarUsuarioRequestDTO body) {

        if (usuarioRepository.findByEmail(body.email()).isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setNome(body.name());
            newUser.setPerfil(Perfil.valueOf(body.perfil()));
            usuarioRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return new ResponseDTO(newUser.getNome(), token);
        }

        throw new RuntimeException("Usuário com o email informado, já está cadastrado!");
    }
}