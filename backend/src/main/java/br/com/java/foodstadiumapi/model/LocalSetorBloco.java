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
public class LocalSetorBloco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Bloco bloco;
    @ManyToOne
    private LocalSetor localSetor;
}
