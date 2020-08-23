package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.LocalSetor;
import br.com.java.foodstadiumapi.repository.LocalSetorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LocalSetorController {

    @Autowired
    private LocalSetorRepository localSetorRepository;

    @ApiOperation(value = "Listar setores de um local")
    @GetMapping("/locais/setores")
    public List<LocalSetor> listar(@RequestParam String nome){
        return localSetorRepository.findByLocal_Nome(nome);
    }

    @ApiOperation(value = "Detalhar setor de um local")
    @GetMapping("/locais/setores/{id}")
    public Optional<LocalSetor> detalhar(@PathVariable Long id){
        return localSetorRepository.findById(id);
    }
}
