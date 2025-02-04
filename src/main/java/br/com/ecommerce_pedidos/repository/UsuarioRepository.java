package br.com.ecommerce_pedidos.repository;

import br.com.ecommerce_pedidos.model.Usuario;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends ElasticsearchRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u " +
            "JOIN u.pedidos p " +
            "JOIN p.itens i " +
            "GROUP BY u.id " +
            "ORDER BY SUM(i.precoUnitario * i.quantidade) DESC")
    List<Usuario> findTop5UsuariosQueMaisCompraram();

    @Query("SELECT u, AVG(i.precoUnitario * i.quantidade) " +
            "FROM Usuario u " +
            "JOIN u.pedidos p " +
            "JOIN p.itens i " +
            "GROUP BY u.id")
    List<Object[]> findTicketMedioPorUsuario();

    @Query("SELECT SUM(i.precoUnitario * i.quantidade) FROM Pedido p " +
            "JOIN p.itens i " +
            "WHERE FUNCTION('MONTH', p.dataCriacao) = MONTH(CURRENT_DATE) " +
            "AND FUNCTION('YEAR', p.dataCriacao) = YEAR(CURRENT_DATE)")
    BigDecimal findValorTotalFaturadoNoMes();

}
