package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.repository.CarrinhoRepository;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
import br.com.java.foodstadiumapi.repository.RestauranteProdutoRepository;
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
    @Autowired
    private RestauranteProdutoRepository restauranteProdutoRepository;

    @ApiOperation(value = "Detalhar carrinho")
    @GetMapping("/carrinho/{id}")
    public Optional<Carrinho> detalhar(@PathVariable Long id){
        return carrinhoRepository.findById(id);
    }

    @ApiOperation(value = "Detalhar carrinho passando id do cliente como parametro")
    @GetMapping("/carrinho/cliente/{id}")
    public List<Carrinho> detalharCliente(@PathVariable Long id){
        return carrinhoRepository.findByClienteLocalSetorBloco(id);
    }

    @ApiOperation(value = "Cadastrar carrinho")
    @PostMapping("/carrinho")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrinho cadastrar(@RequestBody Carrinho carrinho) throws Exception {
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(carrinho.getClienteLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        RestauranteProduto restauranteProduto = restauranteProdutoRepository.findById(carrinho.getRestauranteProduto().getId())
                .orElseThrow(Exception::new);
        carrinho.setClienteLocalSetorBloco(clienteLocalSetorBloco);
        carrinho.setRestauranteProduto(restauranteProduto);
        return carrinhoRepository.save(carrinho);
    }

    @ApiOperation(value = "Atualizar carrinho")
    @PutMapping("/carrinho/{id}")
    public Carrinho atualizar(@RequestBody Carrinho novoCarrinho, @PathVariable Long id){
        return carrinhoRepository.findById(id)
                .map(carrinho -> {
                    carrinho.setObservacao(novoCarrinho.getObservacao());
                    carrinho.setQuantidade(novoCarrinho.getQuantidade());
                    carrinho.setRestauranteProduto(novoCarrinho.getRestauranteProduto());
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
