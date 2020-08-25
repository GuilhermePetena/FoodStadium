package br.com.java.foodstadiumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorLocalSetorDTO {
    private Long id;
    private LocalDate dataPresente;
    private EntregadorDTO entregador;
    private LocalSetorDTO localSetor;
}
