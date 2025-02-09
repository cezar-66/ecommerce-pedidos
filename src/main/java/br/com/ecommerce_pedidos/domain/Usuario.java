package br.com.ecommerce_pedidos.domain;

import br.com.ecommerce_pedidos.domain.enumeration.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    @Field(type = FieldType.Long)
    private List<Long> pedidosId;

    @Field(type = FieldType.Keyword)
    private Perfil perfil;
}
