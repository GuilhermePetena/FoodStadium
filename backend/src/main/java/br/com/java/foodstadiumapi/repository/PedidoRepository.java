package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Pedido;
import br.com.java.foodstadiumapi.model.Produto;
import br.com.java.foodstadiumapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN TIPO_ENTREGA AS TE ON P.TIPO_ENTREGA_ID = TE.ID WHERE S.NOME = ? AND TE.NOME = ?",nativeQuery = true)
    List<Pedido> findAllbyStatusAndTipoEntrega(String status, String tipo);
    List<Pedido> findAllByStatus_IdAndAndTipoEntrega_Id(Long status, Long tipo);
    List<Pedido> findAllByStatus_NomeAndAndRestauranteLocalSetor_Id(String status, Long id);
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN TIPO_ENTREGA AS TE ON P.TIPO_ENTREGA_ID = TE.ID WHERE S.NOME = ? AND TE.NOME = ? AND P.ENTREGADOR_LOCAL_SETOR_ID = ? ",nativeQuery = true)
    List<Pedido> findAllbyStatusAndTipoEntregaAndEntregadorLocalSetorId(String status, String tipo, Long id);
    //@Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN TIPO_ENTREGA AS TE ON P.TIPO_ENTREGA_ID = TE.ID WHERE S.NOME = ? AND TE.NOME = ? AND",nativeQuery = true)
    List<Pedido> findAllByStatus_NomeAndAndTipoEntrega_NomeAndAndEntregadorLocalSetor_Entregador_Id(String status, String entrega, Long id);
    @Query(value = "SELECT * FROM PEDIDO AS P JOIN STATUS AS S ON P.STATUS_ID = S.ID JOIN CLIENTE_LOCAL_SETOR_BLOCO AS CLSB ON P.CLIENTE_LOCAL_SETOR_BLOCO_ID = CLSB.ID WHERE S.NOME = ? AND CLSB.CLIENTE_ID = ?",nativeQuery = true)
    List<Pedido> findAllByStatus_NomeAndClienteLocalSetorBloco_Cliente(String status, Long id);
    List<Pedido> findAllByStatusEqualsAndRestauranteLocalSetor_Id(String status, Long id);
    List<Pedido> findAllByStatus_NomeAndAndTipoEntrega_NomeAndClienteLocalSetorBloco_Id(String status, String entrega, Long id);
    List<Pedido> findAllByStatus_NomeOrStatus_NomeOrStatus_NomeOrStatus_NomeAndClienteLocalSetorBloco_Cliente_Id(String preparando, String aberto, String buscar, String atribuido, Long id);
}
