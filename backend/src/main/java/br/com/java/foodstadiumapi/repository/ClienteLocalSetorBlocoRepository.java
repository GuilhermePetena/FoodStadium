package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteLocalSetorBlocoRepository extends JpaRepository<ClienteLocalSetorBloco, Long> {
        List<ClienteLocalSetorBloco> findAllByCliente_Usuario_Id(Long id);
        void deleteAllByCliente_Usuario_Id(Long id);
}
