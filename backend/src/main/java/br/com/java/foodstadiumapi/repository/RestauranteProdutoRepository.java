package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.RestauranteProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteProdutoRepository extends JpaRepository<RestauranteProduto, Long> {

    @Query(value = "SELECT * FROM RESTAURANTE as R JOIN RESTAURANTE_PRODUTO as RP on R.ID = RP.RESTAURANTE_ID WHERE RP.RESTAURANTE_ID = ?;",nativeQuery = true)
    List<RestauranteProduto> findAllRestaurantesProdutos(Long id);

    @Query(value = "SELECT * FROM RESTAURANTE as R JOIN RESTAURANTE_PRODUTO as RP on R.ID = RP.RESTAURANTE_ID WHERE RP.NOME_PERSONALIZADO = ?", nativeQuery = true)
    List<RestauranteProduto> findByNomeDoProduto(String nomeProduto);

}
