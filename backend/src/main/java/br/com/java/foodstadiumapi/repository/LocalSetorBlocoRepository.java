package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Local;
import br.com.java.foodstadiumapi.model.LocalSetor;
import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalSetorBlocoRepository extends JpaRepository<LocalSetorBloco, Long> {
        List<LocalSetorBloco> findAllByLocalSetor_Id(Long id);
}
