package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByUsuario_Id(Long id);
}
