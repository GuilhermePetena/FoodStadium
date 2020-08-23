package br.com.java.foodstadiumapi.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RestauranteProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean habilitadoVenda;
    private Double preco;
    private String nomePersonalizado;
    private String detalhes;
    private String imagem;
    @ManyToOne
    private Restaurante restaurante;
    @ManyToOne
    private Produto produto;


}
