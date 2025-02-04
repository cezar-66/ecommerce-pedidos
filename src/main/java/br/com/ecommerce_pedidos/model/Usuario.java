package br.com.ecommerce_pedidos.model;

import br.com.ecommerce_pedidos.model.enumeration.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    private Long id;

    private String nome;
    private String email;
    private String senha;

    private List<Long> pedidosId;

    private Perfil perfil;
}
