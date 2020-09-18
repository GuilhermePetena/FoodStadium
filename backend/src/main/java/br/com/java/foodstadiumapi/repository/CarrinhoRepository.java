package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    List<Carrinho> findAllByClienteLocalSetorBloco_id (Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CARRINHO WHERE CLIENTE_LOCAL_SETOR_BLOCO_ID = ?",nativeQuery = true)
    void deletarPessoa(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CARRINHO WHERE RESTAURANTE_PRODUTO_ID = ? AND CLIENTE_LOCAL_SETOR_BLOCO_ID = ?",nativeQuery = true)
    void deletarProduto(Long id, Long id2);

}
