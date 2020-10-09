package br.com.java.foodstadiumapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    private String cvv;
    private String dataCartao;
    @ManyToOne
    private Cliente cliente;
}
