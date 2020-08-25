package br.com.java.foodstadiumapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private int avaliacaoEntregador;
    private int avaliacaoPedido;
    @ManyToOne
    private ClienteLocalSetorBloco clienteLocalSetorBloco;
    @ManyToOne
    private RestauranteLocalSetor restauranteLocalSetor;
    @ManyToOne
    private TipoEntrega tipoEntrega;
    @ManyToOne
    private EntregadorLocalSetor entregadorLocalSetor;
    @ManyToOne
    private Status status;
}
