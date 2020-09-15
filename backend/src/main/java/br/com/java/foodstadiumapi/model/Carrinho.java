package br.com.java.foodstadiumapi.model;

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
    private String nomeProduto;
    private double preco;
    private int quantidade;
    private String observacao;
}
