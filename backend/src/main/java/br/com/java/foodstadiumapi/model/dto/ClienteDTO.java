package br.com.java.foodstadiumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String numeroCartao;
    private String cvv;
    private String dataCartao;
}
