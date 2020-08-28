package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.RestauranteProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNomeContains (String nomeRestaurante);

    @Query(value = "SELECT * FROM RESTAURANTE AS R JOIN CATEGORIA_RESTAURANTE AS CR  ON R.ID = R.CATEGORIA_RESTAURANTE_ID WHERE CR.NOME = 'LANCHE';", nativeQuery = true)
    List<Restaurante> findByCategoriaRestauranteNome(String nomeCategoria);
}
