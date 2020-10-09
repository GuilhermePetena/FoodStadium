package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao,Long> {
    //List<Cartao> findAllByClientes_Id(Long id);
    void deleteByCliente_Id(Long id);
}
