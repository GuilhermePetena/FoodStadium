package br.com.java.foodstadiumapi.model.dto;

import br.com.java.foodstadiumapi.model.Local;
import br.com.java.foodstadiumapi.model.Setor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalSetorDTO {
    private Long id;
    private String nomeLocal;
    private String nomeSetor;
}
