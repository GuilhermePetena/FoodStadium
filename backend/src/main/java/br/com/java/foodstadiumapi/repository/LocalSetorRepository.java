package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.LocalSetor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalSetorRepository extends JpaRepository<LocalSetor, Long> {
    List<LocalSetor> findByLocal_Nome(String nome);
}
