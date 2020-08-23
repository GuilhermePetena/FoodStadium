package br.com.java.foodstadiumapi.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String nome;
    private String email;
    private String telefone;
    private String imagem;
    @ManyToOne
    private CategoriaRestaurante categoriaRestaurante;
    @ManyToOne
    private Usuario usuario;

}
