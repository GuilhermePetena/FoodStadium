package br.com.java.foodstadiumapi.model.form;

import br.com.java.foodstadiumapi.model.Status;
import br.com.java.foodstadiumapi.model.TipoEntrega;
import br.com.java.foodstadiumapi.model.dto.ClienteLocalSetorBlocoDTO;
import br.com.java.foodstadiumapi.model.dto.EntregadorLocalSetorDTO;
import br.com.java.foodstadiumapi.model.dto.RestauranteLocalSetorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoForm {
    private Long id;
    private int avaliacaoEntregador;
    private int avaliacaoPedido;
    private Status status;
    private TipoEntrega tipoEntrega;
    private ClienteLocalSetorBlocoForm clienteLocalSetorBloco;
    private RestauranteLocalSetorForm restauranteLocalSetor;
    private EntregadorLocalSetorForm entregadorLocalSetor;

}
