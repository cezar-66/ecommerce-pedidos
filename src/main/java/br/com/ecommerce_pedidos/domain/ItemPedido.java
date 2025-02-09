package br.com.ecommerce_pedidos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long pedidoId;

    @Field(type = FieldType.Long)
    private Long produtoId;

    @Field(type = FieldType.Integer)
    private Integer quantidade;

    @Field(type = FieldType.Double)
    private BigDecimal precoUnitario;
}
