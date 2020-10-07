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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;
    private int idade;
    private String numeroCartao;
    private String cvv;
    private String dataCartao;
    @ManyToOne
    private Usuario usuario;


}
