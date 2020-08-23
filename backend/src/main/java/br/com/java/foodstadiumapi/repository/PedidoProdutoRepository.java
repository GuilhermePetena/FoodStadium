package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    }
