package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Entregador;
import br.com.java.foodstadiumapi.model.Usuario;
import br.com.java.foodstadiumapi.repository.EntregadorRepository;
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
public class EntregadorController {

    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @ApiOperation(value = "Listar entregadores")
    @GetMapping("/entregadores")
    public List<Entregador> listar(){
        return entregadorRepository.findAll();
    }

    @ApiOperation(value = "Detalhar entregador")
    @GetMapping("/entregadores/{id}")
    public Optional<Entregador> detalhar(@PathVariable Long id){
        return entregadorRepository.findById(id);
    }

    @ApiOperation(value = "Cadastrar entregador")
    @PostMapping("/entregadores")
    public ResponseEntity<Entregador> cadastrar(@RequestBody Entregador entregador, UriComponentsBuilder uriBuilder) throws Exception {
        Usuario usuario = usuarioRepository.findById(entregador.getUsuario().getId())
                .orElseThrow(Exception::new);
        entregador.setUsuario(usuario);
        entregadorRepository.save(entregador);
        URI uri = uriBuilder.path("/entregador/{id}").buildAndExpand(entregador.getId()).toUri();
        return ResponseEntity.created(uri).body(entregador);
    }
    @ApiOperation(value = "Atualizar entregador")
    @PutMapping("/entregadores/{id}")
    public Entregador atualizar(@RequestBody Entregador novoEntregador, @PathVariable Long id){
        return entregadorRepository.findById(id)
                .map(entregador -> {
                    entregador.setNome(novoEntregador.getNome());
                    entregador.setSobrenome(novoEntregador.getSobrenome());
                    entregador.setCpf(novoEntregador.getCpf());
                    entregador.setTelefone(novoEntregador.getTelefone());
                    entregador.setIdade(novoEntregador.getIdade());
                    return entregadorRepository.save(entregador);
                })
                .orElseGet(() -> {
                    novoEntregador.setId(id);
                    return entregadorRepository.save(novoEntregador);
                });
    }
    @ApiOperation(value = "Deletar entregador")
    @DeleteMapping("/entregadores/{id}")
    void deletar(@PathVariable Long id) {
        entregadorRepository.deleteById(id);
    }
}

