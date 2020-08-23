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
public class Entregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;
    private int idade;
    @ManyToOne
    private Usuario usuario;
}
