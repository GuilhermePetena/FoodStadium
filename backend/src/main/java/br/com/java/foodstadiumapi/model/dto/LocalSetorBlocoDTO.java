package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.Bloco;
import br.com.java.foodstadiumapi.model.LocalSetor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalSetorBlocoDTO {
    private Long id;
    private String nomeBloco;
    private int numeroBloco;
    private LocalSetorDTO localSetor;
}
