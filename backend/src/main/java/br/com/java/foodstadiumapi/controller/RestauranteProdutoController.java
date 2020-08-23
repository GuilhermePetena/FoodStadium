package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Produto;
import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.RestauranteProduto;
import br.com.java.foodstadiumapi.repository.ProdutoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteProdutoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestauranteProdutoController {

    @Autowired
    private RestauranteProdutoRepository repository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @ApiOperation(value = "Listar produtos dos restaurante")
    @GetMapping("/restaurantes/produtos/{id}")
    public List<RestauranteProduto> listarTudo(@PathVariable Long id){
        return repository.findAllRestaurantesProdutos(id);
    }
    @ApiOperation(value = "Listar produtos dos restaurantes por nome do produto")
    @GetMapping("restaurantes/produtos")
    public List<RestauranteProduto> listarPorProduto(@PathParam(value = "nomeProduto") String nomeProduto){
        return repository.findByNomeDoProduto(nomeProduto);
    }

    @ApiOperation(value = "Cadastrar produtos do restaurante")
    @PostMapping("retaurantes/produtos")
    public ResponseEntity<RestauranteProduto> cadastrar(@RequestBody RestauranteProduto restauranteProduto, UriComponentsBuilder uriBuilder) throws Exception {
        Restaurante restaurante = restauranteRepository.findById(restauranteProduto.getRestaurante().getId())
                .orElseThrow(Exception::new);
        Produto produto = produtoRepository.findById(restauranteProduto.getProduto().getId())
                .orElseThrow(Exception::new);
        restauranteProduto.setProduto(produto);
        restauranteProduto.setRestaurante(restaurante);
        repository.save(restauranteProduto);
        URI uri = uriBuilder.path("/restaurantes/produtos/{id}").buildAndExpand(restauranteProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(restauranteProduto);
    }

    @ApiOperation(value = "Atualizar produtos do restaurante")
    @PutMapping("restaurantes/produtos/{id}")
    public RestauranteProduto atualizar(@RequestBody RestauranteProduto novoRestauranteProduto, @PathVariable Long id){
        return repository.findById(id)
                .map(restauranteProduto -> {
                    restauranteProduto.setNomePersonalizado(novoRestauranteProduto.getNomePersonalizado());
                    restauranteProduto.setHabilitadoVenda(novoRestauranteProduto.getHabilitadoVenda());
                    restauranteProduto.setDetalhes(novoRestauranteProduto.getDetalhes());
                    restauranteProduto.setPreco(novoRestauranteProduto.getPreco());
                    restauranteProduto.setImagem(novoRestauranteProduto.getImagem());
                    return repository.save(restauranteProduto);
                })
                .orElseGet(() -> {
                    novoRestauranteProduto.setId(id);
                    return repository.save(novoRestauranteProduto);
                });
    }
    @ApiOperation(value = "Deletar produtos do restaurante")
    @DeleteMapping("restaurantes/produtos/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

}
