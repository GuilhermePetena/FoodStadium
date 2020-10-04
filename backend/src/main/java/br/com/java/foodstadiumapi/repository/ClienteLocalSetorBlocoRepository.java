package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClienteLocalSetorBlocoRepository extends JpaRepository<ClienteLocalSetorBloco, Long> {
        List<ClienteLocalSetorBloco> findAllByCliente_Usuario_Id(Long id);

}
