package br.com.ecommerce_pedidos.config.security;

import br.com.ecommerce_pedidos.domain.enumeration.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasAuthority(Perfil.ADMIN.getDescricao())

                        // Pedidos
                        .requestMatchers(HttpMethod.POST, "/pedido/criarPedido")
                            .hasAnyAuthority(Perfil.USER.getDescricao(), Perfil.ADMIN.getDescricao())

                        .requestMatchers(HttpMethod.POST, "/pedido/{pedidoId}/pagamento")
                            .hasAnyAuthority(Perfil.USER.getDescricao(), Perfil.ADMIN.getDescricao())

                        //Produtos
                        .requestMatchers(HttpMethod.POST, "/produtos/buscar")
                            .hasAnyAuthority(Perfil.USER.getDescricao(), Perfil.ADMIN.getDescricao())

                        // Relatórios - apenas ADMIN
                        .requestMatchers(HttpMethod.GET, "/relatorios/**").hasAuthority(Perfil.ADMIN.getDescricao())

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
