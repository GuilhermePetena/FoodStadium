package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Entregador;
import br.com.java.foodstadiumapi.model.EntregadorLocalSetor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregadorLocalSetorRepository extends JpaRepository<EntregadorLocalSetor, Long> {
    List<EntregadorLocalSetor> findAllByEntregador_Usuario_Id(Long id);
}
