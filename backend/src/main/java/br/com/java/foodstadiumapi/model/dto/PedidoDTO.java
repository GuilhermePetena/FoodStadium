package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private int avaliacaoEntregador;
    private int avaliacaoPedido;
    private Status status;
    private TipoEntrega tipoEntrega;
    private ClienteLocalSetorBlocoDTO clienteLocalSetorBloco;
    private RestauranteLocalSetorDTO restauranteLocalSetor;
    private EntregadorLocalSetorDTO entregadorLocalSetor;


}
