package br.com.java.foodstadiumapi.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteLocalSetorForm {
    private Long id;
    private RestauranteForm restaurante;
    private LocalSetorForm localSetor;
}
