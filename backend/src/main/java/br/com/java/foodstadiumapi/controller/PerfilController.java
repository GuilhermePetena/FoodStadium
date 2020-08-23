package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Perfil;
import br.com.java.foodstadiumapi.repository.PerfilRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @ApiOperation(value="Listar perfis")
    @GetMapping("/perfis")
    public List<Perfil> listar(){
        List<Perfil> listaPerfil = perfilRepository.findAll();
        return listaPerfil;
    }
}
