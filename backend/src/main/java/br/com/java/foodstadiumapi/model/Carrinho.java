package br.com.java.foodstadiumapi.model;

import br.com.java.foodstadiumapi.model.dto.ClienteLocalSetorBlocoDTO;
import br.com.java.foodstadiumapi.model.dto.RestauranteProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteLocalSetorBloco clienteLocalSetorBloco;
    @ManyToOne
    private RestauranteProduto restauranteProduto;
    private int quantidade;
    private String observacao;

}
