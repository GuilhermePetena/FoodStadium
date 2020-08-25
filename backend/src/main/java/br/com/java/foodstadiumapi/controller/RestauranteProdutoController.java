package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.model.dto.RestauranteProdutoDTO;
import br.com.java.foodstadiumapi.model.form.RestauranteProdutoForm;
import br.com.java.foodstadiumapi.repository.ProdutoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteProdutoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class RestauranteProdutoController {

    @Autowired
    private RestauranteProdutoRepository repository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar produtos de um restaurante")
    @GetMapping(value = "/restaurantes/produtos/{id}",produces="application/json", consumes="application/json")
    public List<RestauranteProdutoDTO> listarTudo(@PathVariable Long id){
      return paraListaModel(repository.findAllByRestauranteId(id));
    }
    @ApiOperation(value = "Listar produtos dos restaurantes por nome do produto")
    @GetMapping(value = "restaurantes/produtos",produces="application/json", consumes="application/json")
    public List<RestauranteProdutoDTO> listarPorProduto(@PathParam(value = "nomeProduto") String nomeProduto){
        return paraListaModel(repository.findAllByNomePersonalizado(nomeProduto));
    }

    @ApiOperation(value = "Cadastrar produtos do restaurante")
    @PostMapping(value = "restaurantes/produtos",produces="application/json", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteProdutoDTO cadastrar(@RequestBody RestauranteProdutoForm restauranteProdutoForm) throws Exception {
        RestauranteProduto restauranteProduto = paraEntity(restauranteProdutoForm);
        Restaurante restaurante = restauranteRepository.findById(restauranteProdutoForm.getRestaurante().getId())
                .orElseThrow(Exception::new);
        Produto produto = produtoRepository.findById(restauranteProdutoForm.getProduto().getId())
                .orElseThrow(Exception::new);
        restauranteProduto.setProduto(produto);
        restauranteProduto.setRestaurante(restaurante);
        return paraModel(repository.save(restauranteProduto));
    }

    @ApiOperation(value = "Atualizar produtos do restaurante")
    @PutMapping(value = "restaurantes/produtos/{id}",produces="application/json", consumes="application/json")
    public RestauranteProdutoDTO atualizar(@RequestBody RestauranteProdutoForm restauranteProdutoForm, @PathVariable Long id) throws Exception {
        RestauranteProduto restauranteProduto = paraEntity(restauranteProdutoForm);
        Restaurante restaurante = restauranteRepository.findById(restauranteProduto.getRestaurante().getId())
                .orElseThrow(Exception::new);
        Produto produto = produtoRepository.findById(restauranteProduto.getProduto().getId())
                .orElseThrow(Exception::new);
        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setProduto(produto);
        return repository.findById(id)
                .map(restauranteProdutoClass -> {
                    restauranteProdutoClass.setNomePersonalizado(restauranteProduto.getNomePersonalizado());
                    restauranteProdutoClass.setHabilitadoVenda(restauranteProduto.getHabilitadoVenda());
                    restauranteProdutoClass.setDetalhes(restauranteProduto.getDetalhes());
                    restauranteProdutoClass.setPreco(restauranteProduto.getPreco());
                    restauranteProdutoClass.setImagem(restauranteProduto.getImagem());
                    return paraModel(repository.save(restauranteProdutoClass));
                })
                .orElseGet(() -> {
                    restauranteProduto.setId(id);
                    return paraModel(repository.save(restauranteProduto));
                });
    }
    @ApiOperation(value = "Deletar produtos do restaurante")
    @DeleteMapping("restaurantes/produtos/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

    private RestauranteProdutoDTO paraModel(RestauranteProduto restauranteProduto){
        return modelMapper.map(restauranteProduto, (Type) RestauranteProdutoDTO.class);
    }
    private List<RestauranteProdutoDTO> paraListaModel(List<RestauranteProduto> restauranteProdutoList){
        return restauranteProdutoList.stream()
                .map(this::paraModel)
                .collect(Collectors.toList());
    }

    private RestauranteProduto paraEntity(RestauranteProdutoForm restauranteProdutoForm) {
        return modelMapper.map(restauranteProdutoForm, RestauranteProduto.class);
    }

}
