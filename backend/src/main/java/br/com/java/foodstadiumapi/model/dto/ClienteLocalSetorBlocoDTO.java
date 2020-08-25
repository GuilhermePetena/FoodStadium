package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteLocalSetorBlocoDTO {
    private Long id;
    private LocalDate dataPresente;
    private ClienteDTO cliente;
    private LocalSetorBlocoDTO localSetorBloco;


}
