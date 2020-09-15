package br.com.java.foodstadiumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarrinhoDTO {
        private Long id;
        private ClienteLocalSetorBlocoDTO clienteLocalSetorBloco;
        private RestauranteProdutoDTO restauranteProduto;
        private int quantidade;
        private String observacao;
    }
