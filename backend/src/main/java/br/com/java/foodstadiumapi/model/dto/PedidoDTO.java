package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    Pedido pedido;
    PedidoProduto pedidoProduto;
    ClienteLocalSetorBloco clienteLocalSetorBloco;
    EntregadorLocalSetor entregadorLocalSetor;
    RestauranteLocalSetor restauranteLocalSetor;
}
