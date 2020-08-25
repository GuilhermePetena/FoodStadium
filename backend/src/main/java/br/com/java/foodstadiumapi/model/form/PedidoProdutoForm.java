package br.com.java.foodstadiumapi.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoForm {
    private Long id;
    private int quantidade;
    private String observacao;
    private RestauranteProdutoForm restauranteProduto;
    private PedidoForm pedido;
}
