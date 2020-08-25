package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.RestauranteProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteProdutoRepository extends JpaRepository<RestauranteProduto, Long> {


    List<RestauranteProduto> findAllByRestauranteId(Long id);

    @Query(value = "SELECT * FROM RESTAURANTE as R JOIN RESTAURANTE_PRODUTO as RP on R.ID = RP.RESTAURANTE_ID WHERE RP.NOME_PERSONALIZADO = ?", nativeQuery = true)
    List<RestauranteProduto> findByNomeDoProduto(String nomeProduto);

    List<RestauranteProduto> findAllByNomePersonalizado(String nomeProduto);

}
