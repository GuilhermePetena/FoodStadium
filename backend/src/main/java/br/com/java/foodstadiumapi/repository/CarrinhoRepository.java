package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
