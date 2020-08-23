package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByEmailAndSenha(String email, String senha);
}
