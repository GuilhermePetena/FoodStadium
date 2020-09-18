package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.model.dto.CarrinhoDTO;
import br.com.java.foodstadiumapi.model.dto.ClienteLocalSetorBlocoDTO;
import br.com.java.foodstadiumapi.repository.CarrinhoRepository;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteProdutoRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ClienteLocalSetorBlocoRepository clienteLocalSetorBlocoRepository;
    @Autowired
    private RestauranteProdutoRepository restauranteProdutoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Detalhar carrinho")
    @GetMapping("/carrinho/{id}")
    public ResponseEntity<CarrinhoDTO> detalhar(@PathVariable Long id) {
        Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
        if (carrinho.isPresent()){
            CarrinhoDTO carrinhoDTO = paraDTO(carrinho.get());
            return ResponseEntity.ok(carrinhoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Detalhar carrinho passando id do cliente como parametro")
    @GetMapping("/carrinho/cliente")
    public List<CarrinhoDTO> detalharCliente(@RequestParam Long id){
        return paraListaDTO(carrinhoRepository.findAllByClienteLocalSetorBloco_id(id));
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

    @ApiOperation(value = "Deletar carrinho por Pessoa")
    @DeleteMapping("/carrinho/pessoa/{id}")
    void deletarPessoa(@PathVariable Long id) {
        carrinhoRepository.deletarPessoa(id);
    }

    @ApiOperation(value = "Deletar carrinho por Produto")
    @DeleteMapping("/carrinho/pessoa/{id}/produto/{id2}")
    void deletarProduto(@PathVariable Long id, @PathVariable Long id2) {
        carrinhoRepository.deletarProduto(id, id2);
    }

    private CarrinhoDTO paraDTO(Carrinho carrinho){
        return modelMapper.map(carrinho, (Type) CarrinhoDTO.class);
    }
    private List<CarrinhoDTO> paraListaDTO(List<Carrinho> carrinhoList){
        return carrinhoList.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }
}
