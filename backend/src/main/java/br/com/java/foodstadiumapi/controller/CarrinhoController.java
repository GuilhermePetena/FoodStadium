package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.repository.CarrinhoRepository;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ClienteLocalSetorBlocoRepository clienteLocalSetorBlocoRepository;

    @ApiOperation(value = "Detalhar carrinho")
    @GetMapping("/carrinho/{id}")
    public Optional<Carrinho> detalhar(@PathVariable Long id){
        return carrinhoRepository.findById(id);
    }

    @ApiOperation(value = "Cadastrar carrinho")
    @PostMapping("/carrinho")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrinho cadastrar(@RequestBody Carrinho carrinho, UriComponentsBuilder uriBuilder) throws Exception {
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(carrinho.getClienteLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        carrinho.setClienteLocalSetorBloco(clienteLocalSetorBloco);
        return carrinhoRepository.save(carrinho);
    }

    @ApiOperation(value = "Atualizar carrinho")
    @PutMapping("/carrinho/{id}")
    public Carrinho atualizar(@RequestBody Carrinho novoCarrinho, @PathVariable Long id){
        return carrinhoRepository.findById(id)
                .map(carrinho -> {
                    carrinho.setObservacao(novoCarrinho.getObservacao());
                    carrinho.setPreco(novoCarrinho.getPreco());
                    return carrinhoRepository.save(carrinho);
                })
                .orElseGet(() -> {
                    novoCarrinho.setId(id);
                    return carrinhoRepository.save(novoCarrinho);
                });
    }

    @ApiOperation(value = "Deletar carrinho")
    @DeleteMapping("/carrinho/{id}")
    void deletar(@PathVariable Long id) {
        carrinhoRepository.deleteById(id);
    }
}
