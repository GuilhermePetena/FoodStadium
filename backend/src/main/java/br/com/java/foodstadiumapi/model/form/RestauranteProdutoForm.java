package br.com.java.foodstadiumapi.model.form;

import br.com.java.foodstadiumapi.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteProdutoForm {
    private Long id;
    private Boolean habilitadoVenda;
    private Double preco;
    private String nomePersonalizado;
    private String detalhes;
    private String imagem;
    private RestauranteForm restaurante;
    private Produto produto;
}
