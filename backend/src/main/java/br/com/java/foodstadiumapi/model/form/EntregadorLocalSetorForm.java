package br.com.java.foodstadiumapi.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorLocalSetorForm {
    private Long id;
    private EntregadorForm entregador;
    private LocalSetorForm localSetor;
}
