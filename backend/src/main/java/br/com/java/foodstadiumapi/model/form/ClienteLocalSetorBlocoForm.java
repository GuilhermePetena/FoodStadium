package br.com.java.foodstadiumapi.model.form;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLocalSetorBlocoForm {
    private Long id;
    private ClienteForm cliente;
    private LocalSetorBlocoForm localSetorBloco;

}
