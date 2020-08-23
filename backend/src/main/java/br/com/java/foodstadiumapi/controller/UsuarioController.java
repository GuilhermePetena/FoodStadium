package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Perfil;
import br.com.java.foodstadiumapi.model.Usuario;
import br.com.java.foodstadiumapi.repository.PerfilRepository;
import br.com.java.foodstadiumapi.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @ApiOperation(value = "Listar usuários")
    @GetMapping("/usuarios")
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @ApiOperation(value = "Detalhes do usuário")
    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> detalhar(@PathVariable Long id){
        return usuarioRepository.findById(id);
    }

    @ApiOperation(value = "Validar login dos usuários")
    @GetMapping("/usuarios/login")
    public ResponseEntity validar(@RequestParam String email, String senha){
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        if(!usuario.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok().body("Usuario existe");
        }
    }

    @ApiOperation(value = "Cadastrar usuário")
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) throws Exception {
        Perfil perfil = perfilRepository.findById(usuario.getPerfil().getId())
                .orElseThrow(Exception::new);
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @ApiOperation(value = "Atualizar usuário")
    @PutMapping("/usuarios/{id}")
    public Usuario atualizar(@RequestBody Usuario novoUsuario, @PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setSenha(novoUsuario.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElseGet(() -> {
                    novoUsuario.setId(id);
                    return usuarioRepository.save(novoUsuario);
                });
    }

    @ApiOperation(value = "Deletar usuário")
    @DeleteMapping("/usuarios/{id}")
     void deletar(@PathVariable Long id) {
         usuarioRepository.deleteById(id);
    }
}
