package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteProdutoDTO {
    private Long id;
    private Boolean habilitadoVenda;
    private Double preco;
    private String nomePersonalizado;
    private String detalhes;
    private String imagem;
    private RestauranteDTO restaurante;
    private Produto produto;
}
