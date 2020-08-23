package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import br.com.java.foodstadiumapi.repository.LocalSetorBlocoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LocalSetorBlocoController {

    @Autowired
    private LocalSetorBlocoRepository repository;

    @ApiOperation(value = "Listar setor + bloco de um local")
    @GetMapping("/locais/blocos/{id}")
    public List<LocalSetorBloco> listaDeDetalhesDoEstadio(@PathVariable Long id){
        return repository.findByLocalNomeSetorBloco(id);
    }
}
