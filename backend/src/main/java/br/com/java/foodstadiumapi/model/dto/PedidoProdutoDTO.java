package br.com.java.foodstadiumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoDTO {
    private Long id;
    private int quantidade;
    private String observacao;
    private RestauranteProdutoDTO restauranteProduto;
    private PedidoDTO pedido;
}



