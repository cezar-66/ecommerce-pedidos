package br.com.ecommerce_pedidos.model;

import br.com.ecommerce_pedidos.model.enumeration.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

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

    private Long usuarioId;

    private StatusPedido status;

    private List<Long> itensId;

    private LocalDateTime dataCriacao;
}