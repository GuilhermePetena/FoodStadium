package br.com.java.foodstadiumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteLocalSetorDTO {
    private Long id;
    private RestauranteDTO restaurante;
    private LocalSetorDTO LocalSetor;
}
