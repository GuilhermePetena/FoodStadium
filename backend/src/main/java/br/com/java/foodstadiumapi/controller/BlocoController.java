package br.com.java.foodstadiumapi.controller;
import br.com.java.foodstadiumapi.model.Bloco;
import br.com.java.foodstadiumapi.repository.BlocoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BlocoController {
    @Autowired
    private BlocoRepository repository;

    @ApiOperation(value = "Listar blocos")
    @GetMapping("/blocos")
    public List<Bloco> listar(){
        return repository.findAll();
    }

    @ApiOperation(value = "Detalhe do bloco")
    @GetMapping("/blocos/{id}")
    public Optional<Bloco> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }
}
