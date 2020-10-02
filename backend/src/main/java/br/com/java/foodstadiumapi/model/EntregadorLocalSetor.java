package br.com.java.foodstadiumapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EntregadorLocalSetor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataPresente;
    @ManyToOne
    private Entregador entregador;
    @ManyToOne
    private LocalSetor localSetor;

}
