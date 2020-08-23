package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
