package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.Usuario;
import br.com.java.foodstadiumapi.repository.RestauranteRepository;
import br.com.java.foodstadiumapi.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @ApiOperation(value="Listar restaurantes")
    @GetMapping("/restaurantes")
    public List<Restaurante> listar(){
        return repository.findAll();
    }

    @ApiOperation(value="Listar restaurantes por nome")
    @GetMapping("/restaurantes/nome")
    public List<Restaurante> listarPorNomeRestaurante(@PathParam(value = "nomeRestaurante") String nomeRestaurante){
        return repository.findByNome(nomeRestaurante);
    }

    @ApiOperation(value="Listar restaurantes por categoria")
    @GetMapping("/restaurantes/categoria")
    public List<Restaurante> listarPorProdut(@PathParam(value = "nomeCategoria") String nomeCategoria){
        return repository.findByCategoriaRestauranteNome(nomeCategoria);
    }

    @ApiOperation(value="Cadastrar restaurante")
    @PostMapping("/restaurantes")
    public ResponseEntity<Restaurante> cadastrar(@RequestBody Restaurante restaurante, UriComponentsBuilder uriBuilder) throws Exception {
        Usuario usuario = usuarioRepository.findById(restaurante.getUsuario().getId())
                .orElseThrow(Exception::new);
        restaurante.setUsuario(usuario);
        repository.save(restaurante);
        URI uri = uriBuilder.path("/restaurante/{id}").buildAndExpand(restaurante.getId()).toUri();
        return ResponseEntity.created(uri).body(restaurante);
    }
    @ApiOperation(value="Detalhar restaurante")
    @GetMapping("/restaurantes/{id}")
    public Optional<Restaurante> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }

    @ApiOperation(value="Atualizar restaurante")
    @PutMapping("/restaurantes/{id}")
    public Restaurante atualizar(@RequestBody Restaurante novoRestaurante, @PathVariable Long id){
        return repository.findById(id)
                .map(restaurante -> {
                    restaurante.setEmail(novoRestaurante.getEmail());
                    restaurante.setCategoriaRestaurante(novoRestaurante.getCategoriaRestaurante());
                    restaurante.setImagem(novoRestaurante.getImagem());
                    restaurante.setNome(novoRestaurante.getNome());
                    restaurante.setTelefone(novoRestaurante.getTelefone());
                    return repository.save(restaurante);
                })
                .orElseGet(() -> {
                    novoRestaurante.setId(id);
                    return repository.save(novoRestaurante);
                });
    }

    @ApiOperation(value="Deletar restaurante")
    @DeleteMapping("/restaurantes/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

}



