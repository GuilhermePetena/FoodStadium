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
public class RestauranteLocalSetor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Restaurante restaurante;
    @ManyToOne
    private LocalSetor localSetor;
}
