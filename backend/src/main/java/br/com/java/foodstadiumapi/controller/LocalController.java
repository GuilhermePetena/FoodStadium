package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Local;
import br.com.java.foodstadiumapi.repository.LocalRepository;
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
public class LocalController {
    @Autowired
    private LocalRepository localRepository;

    @ApiOperation(value = "Listar locais")
    @GetMapping("/locais")
    public List<Local> listar(){
        return localRepository.findAll();
    }

    @ApiOperation(value = "Detalhar local")
    @GetMapping("/locais/{id}")
    public Optional<Local> detalhar(@PathVariable Long id){
        return localRepository.findById(id);
    }

    @ApiOperation(value = "Cadastrar local")
    @PostMapping("/locais")
    public ResponseEntity<Local> cadastrar(@RequestBody Local local, UriComponentsBuilder uriBuilder){
        localRepository.save(local);
        URI uri = uriBuilder.path("/local/{id}").buildAndExpand(local.getId()).toUri();
        return ResponseEntity.created(uri).body(local);
    }

    @ApiOperation(value = "Atualizar local")
    @PutMapping("/locais/{id}")
    public Local atualizar(@RequestBody Local novoLocal, @PathVariable Long id){
        return localRepository.findById(id)
                .map(local -> {
                    local.setEmail(novoLocal.getEmail());
                    local.setEndereco(novoLocal.getEndereco());
                    local.setNome(novoLocal.getNome());
                    local.setTelefone(novoLocal.getTelefone());
                    return localRepository.save(local);
                })
                .orElseGet(() -> {
                    novoLocal.setId(id);
                    return localRepository.save(novoLocal);
                });
    }

    @ApiOperation(value = "Deletar local")
    @DeleteMapping("/locais/{id}")
    void deletar(@PathVariable Long id) {
        localRepository.deleteById(id);
    }

}
