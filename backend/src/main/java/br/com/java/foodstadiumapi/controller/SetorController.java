package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Setor;
import br.com.java.foodstadiumapi.repository.SetorRepository;
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
public class SetorController {

    @Autowired
    private SetorRepository repository;

    @ApiOperation(value = "Listar setores")
    @GetMapping("/setores")
    public List<Setor> listar(){
        return repository.findAll();
    }

    @ApiOperation(value = "Detalhar setor")
    @GetMapping("/setores/{id}")
    public Optional<Setor> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }
}
