package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
            List<PedidoProduto> findAllByPedido_Id(Long id);
            List<PedidoProduto> findAllByPedido_Status_NomeAndPedido_RestauranteLocalSetor_Id(String status, Long id);
}
