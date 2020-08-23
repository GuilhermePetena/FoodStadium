package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Local;
import br.com.java.foodstadiumapi.model.LocalSetor;
import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalSetorBlocoRepository extends JpaRepository<LocalSetorBloco, Long> {

        @Query(value = "SELECT * FROM LOCAL_SETOR_BLOCO AS LSB JOIN LOCAL_SETOR as LS on LSB.LOCAL_SETOR_ID = LS.ID WHERE LS.LOCAL_ID = ?",nativeQuery = true)
        List<LocalSetorBloco> findByLocalNomeSetorBloco(Long id);
}
