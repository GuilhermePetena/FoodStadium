package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Cartao;
import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.Local;
import br.com.java.foodstadiumapi.repository.CartaoRepository;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
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
public class CartaoController {
    @Autowired
    private CartaoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    /*@ApiOperation(value = "Listar cartoes de um cliente")
    @GetMapping("/cartao/cliente/{id}")
    public List<Cartao> listar(@PathVariable Long id){
        return repository.findAllByClientes_Id(id);
    }*/

    @ApiOperation(value = "Detalhar cartao")
    @GetMapping("/cartao/{id}")
    public Optional<Cartao> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }

    @ApiOperation(value = "Cadastrar cartao")
    @PostMapping("/cartao")
    public ResponseEntity<Cartao> cadastrar(@RequestBody Cartao cartao, UriComponentsBuilder uriBuilder) throws Exception {
        Cliente cliente = clienteRepository.findById(cartao.getCliente().getId())
                .orElseThrow(Exception::new);
        cartao.setCliente(cliente);
        repository.save(cartao);
        URI uri = uriBuilder.path("/cartao/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(cartao);
    }

    @ApiOperation(value = "Atualizar cartao")
    @PutMapping("/cartao/{id}")
    public Cartao atualizar(@RequestBody Cartao novoCartao, @PathVariable Long id){
        return repository.findById(id)
                .map(cartao -> {
                    cartao.setNumeroCartao(novoCartao.getNumeroCartao());
                    cartao.setCvv(novoCartao.getCvv());
                    cartao.setDataCartao(novoCartao.getDataCartao());
                    return repository.save(cartao);
                })
                .orElseGet(() -> {
                    novoCartao.setId(id);
                    return repository.save(novoCartao);
                });
    }

    @ApiOperation(value = "Deletar cartao")
    @DeleteMapping("/cartao/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @ApiOperation(value = "Deletar todos cartoes por id cliente")
    @DeleteMapping("/cartao/cliente{id}")
    void deletar2(@PathVariable Long id) {
        repository.deleteByCliente_Id(id);
    }
}
