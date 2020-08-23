package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Entregador;
import br.com.java.foodstadiumapi.model.EntregadorLocalSetor;
import br.com.java.foodstadiumapi.model.LocalSetor;
import br.com.java.foodstadiumapi.repository.EntregadorLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.EntregadorRepository;
import br.com.java.foodstadiumapi.repository.LocalSetorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EntregadorLocalSetorController {
    @Autowired
    private EntregadorLocalSetorBlocoRepository repository;
    @Autowired
    private LocalSetorRepository localSetorRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;

    @ApiOperation(value = "Listar local dos entregadores")
    @GetMapping("/entregadores/local")
    public List<EntregadorLocalSetor> listar() {
        return repository.findAll();
    }

    @ApiOperation(value = "Detalhar local do entregador")
    @GetMapping("/entregadores/local/{id}")
    public Optional<EntregadorLocalSetor> detalhes(@PathVariable Long id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Cadastrar local do entregador")
    @PostMapping("entregadores/local")
    public ResponseEntity<EntregadorLocalSetor> cadastrar(@RequestBody EntregadorLocalSetor entregadorLocalSetor, UriComponentsBuilder uriBuilder) throws Exception {
        Entregador entregador = entregadorRepository.findById(entregadorLocalSetor.getEntregador().getId())
                .orElseThrow(Exception::new);
        LocalSetor localSetor = localSetorRepository.findById(entregadorLocalSetor.getLocalSetor().getId())
                .orElseThrow(Exception::new);
        entregadorLocalSetor.setEntregador(entregador);
        entregadorLocalSetor.setLocalSetor(localSetor);
        entregadorLocalSetor.setDataPresente(LocalDate.now());
        repository.save(entregadorLocalSetor);
        URI uri = uriBuilder.path("clientes/local/{id}").buildAndExpand(entregadorLocalSetor.getId()).toUri();
        return ResponseEntity.created(uri).body(entregadorLocalSetor);
    }

    @ApiOperation(value = "Atualizar local do entregador")
    @PutMapping("entregadores/local/{id}")
    public EntregadorLocalSetor atualizar(@RequestBody EntregadorLocalSetor novoEntregadorLocalSetor, @PathVariable Long id){
        return repository.findById(id)
                .map(entregadorLocalSetor -> {
                    entregadorLocalSetor.setEntregador(novoEntregadorLocalSetor.getEntregador());
                    entregadorLocalSetor.setLocalSetor(novoEntregadorLocalSetor.getLocalSetor());
                    entregadorLocalSetor.setDataPresente(novoEntregadorLocalSetor.getDataPresente());
                    return repository.save(entregadorLocalSetor);
                })
                .orElseGet(() -> {
                    novoEntregadorLocalSetor.setId(id);
                    return repository.save(novoEntregadorLocalSetor);
                });
    }

    @ApiOperation(value = "Deletar local do entregador")
    @DeleteMapping("/entregadores/local/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
