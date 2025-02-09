package br.com.ecommerce_pedidos.domain;

import br.com.ecommerce_pedidos.domain.enumeration.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long usuarioId;

    @Field(type = FieldType.Keyword)
    private StatusPedido status;

    @Field(type = FieldType.Long)
    private List<Long> itensId;

    @Field(type = FieldType.Date)
    private LocalDateTime dataCriacao;
}
