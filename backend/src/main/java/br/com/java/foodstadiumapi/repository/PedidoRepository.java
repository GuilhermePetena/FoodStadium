package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN TIPO_ENTREGA AS TE ON P.TIPO_ENTREGA_ID = TE.ID WHERE S.NOME = ? AND TE.NOME = ?",nativeQuery = true)
    List<Pedido> findAllbyStatusAndTipoEntrega(String status, String tipo);
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN TIPO_ENTREGA AS TE ON P.TIPO_ENTREGA_ID = TE.ID WHERE S.NOME = ? AND TE.NOME = ?",nativeQuery = true)
    List<Pedido> findAllbyStatusAndTipoEntregaAndEntregador(String status, String tipo, Long id);
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN CLIENTE_LOCAL_SETOR_BLOCO AS CLSB ON P.CLIENTE_LOCAL_SETOR_BLOCO_ID = CLSB.ID WHERE S.NOME = ? AND CLSB.CLIENTE_ID = ?",nativeQuery = true)
    List<Pedido> findAllByStatus_NomeAndClienteLocalSetorBloco_Cliente(String status, Long id);
}
