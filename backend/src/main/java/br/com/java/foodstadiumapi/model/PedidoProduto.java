package br.com.java.foodstadiumapi.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PedidoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private String observacao;
    @ManyToOne
    private RestauranteProduto restauranteProduto;
    @ManyToOne
    private Pedido pedido;

}
