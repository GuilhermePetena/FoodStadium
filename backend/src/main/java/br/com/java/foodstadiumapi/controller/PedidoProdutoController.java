package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.model.dto.PedidoProdutoDTO;
import br.com.java.foodstadiumapi.model.form.PedidoProdutoForm;
import br.com.java.foodstadiumapi.repository.PedidoProdutoRepository;
import br.com.java.foodstadiumapi.repository.PedidoRepository;
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
public class PedidoProdutoController {
    @Autowired
    private PedidoProdutoRepository repository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RestauranteProdutoRepository restauranteProdutoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar pedidos com produtos")
    @GetMapping("/pedidos/produtos")
    public List<PedidoProdutoDTO> listar(){
        return paraListaDTO(repository.findAll());
    }

    @ApiOperation(value = "Listar pedidosProduto apartir do ID do pedido ")
    @GetMapping("/pedidos/{id}/produtos")
    public List<PedidoProdutoDTO> listarPedidoProduto(@PathVariable Long id){
        return paraListaDTO(repository.findAllByPedido_Id(id));
    }

    @ApiOperation(value = "Listar pedidosProduto para aceitar (restaurante)apartir do ID do restaurante_local_setor ")
    @GetMapping("/pedidos/restauranteAceitar/{id}")
    public List<PedidoProdutoDTO> listarPedidoProdutoAceitar(@PathVariable Long id){
        return paraListaDTO(repository.findAllByPedido_Status_NomeAndPedido_RestauranteLocalSetor_Id("ABERTO",id));
    }

    @ApiOperation(value = "Listar pedidosProduto entregues (restaurante)apartir do ID do restaurante_local_setor ")
    @GetMapping("/pedidos/restauranteEntregues/{id}")
    public List<PedidoProdutoDTO> listarPedidoProdutoEntregue(@PathVariable Long id){
        return paraListaDTO(repository.findAllByPedido_Status_NomeAndPedido_RestauranteLocalSetor_Id("ENTREGUE",id));
    }

    @ApiOperation(value = "Listar pedidosProduto andamento (restaurante)apartir do ID do restaurante_local_setor ")
    @GetMapping("/pedidos/restaurantePreparando/{id}")
    public List<PedidoProdutoDTO> listarPedidoProdutoAndamento(@PathVariable Long id){
        return paraListaDTO(repository.findAllByPedido_Status_NomeAndPedido_RestauranteLocalSetor_Id("PREPARANDO",id));
    }

    @ApiOperation(value = "Detalhar pedido com produtos")
    @GetMapping("/pedidos/produtos/{id}")
    public ResponseEntity<PedidoProdutoDTO> detalhar(@PathVariable Long id){
        Optional<PedidoProduto> pedidoProduto = repository.findById(id);
        if (pedidoProduto.isPresent()){
            PedidoProdutoDTO pedidoProdutoDTO = paraDTO(pedidoProduto.get());
            return ResponseEntity.ok(pedidoProdutoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Atualizar pedido com produtos")
    @PutMapping("/pedidos/produtos/{id}")
    public PedidoProdutoDTO atualizar(@RequestBody PedidoProdutoForm novoPedidoForm, @PathVariable Long id) throws Exception {
        PedidoProduto pedidoProduto = paraEntity(novoPedidoForm);
        RestauranteProduto restauranteProduto = restauranteProdutoRepository.findById(novoPedidoForm.getRestauranteProduto().getId())
                .orElseThrow(Exception::new);
        Pedido pedidoClass = pedidoRepository.findById(novoPedidoForm.getPedido().getId())
                .orElseThrow(Exception::new);
        pedidoProduto.setPedido(pedidoClass);
        pedidoProduto.setRestauranteProduto(restauranteProduto);
        return repository.findById(id)
                .map(pedido -> {
                    pedido.setObservacao(pedidoProduto.getObservacao());
                    pedido.setQuantidade(pedidoProduto.getQuantidade());
                    return paraDTO(repository.save(pedido));
                })
                .orElseGet(() -> {
                    pedidoProduto.setId(id);
                    return paraDTO(repository.save(pedidoProduto));
                });
    }
    @ApiOperation(value = "Cadastrar pedido com produtos")
    @PostMapping("/pedidos/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoProdutoDTO deletar(@RequestBody PedidoProdutoForm pedidoProdutoForm) throws Exception {
        PedidoProduto pedidoProduto = paraEntity(pedidoProdutoForm);
        RestauranteProduto restauranteProduto = restauranteProdutoRepository.findById(pedidoProdutoForm.getRestauranteProduto().getId())
                .orElseThrow(Exception::new);
        Pedido pedido = pedidoRepository.findById(pedidoProdutoForm.getPedido().getId())
                .orElseThrow(Exception::new);
        pedidoProduto.setPedido(pedido);
        pedidoProduto.setRestauranteProduto(restauranteProduto);
        return paraDTO(repository.save(pedidoProduto));
    }

    @ApiOperation(value = "Deletar pedido com produtos")
    @DeleteMapping("/pedidos/produtos/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private PedidoProdutoDTO paraDTO(PedidoProduto pedidoProduto){
        return modelMapper.map(pedidoProduto, (Type) PedidoProdutoDTO.class);
    }
    private List<PedidoProdutoDTO> paraListaDTO(List<PedidoProduto> pedidoProdutoList){
        return pedidoProdutoList.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }
    private PedidoProduto paraEntity(PedidoProdutoForm pedidoProdutoForm) {
        return modelMapper.map(pedidoProdutoForm, PedidoProduto.class);
    }
    private PedidoProdutoForm paraForm(PedidoProduto pedidoProduto){
        return modelMapper.map(pedidoProduto, PedidoProdutoForm.class);
    }
}
