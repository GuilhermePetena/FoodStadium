package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    Entregador findByUsuario_Id(Long id);
}
