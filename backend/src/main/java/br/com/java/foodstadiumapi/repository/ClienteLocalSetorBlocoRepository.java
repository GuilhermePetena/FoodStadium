package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteLocalSetorBlocoRepository extends JpaRepository<ClienteLocalSetorBloco, Long> {
        ClienteLocalSetorBloco findByCliente_Usuario_Id(Long id);
}
