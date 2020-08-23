package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.repository.PedidoProdutoRepository;
import br.com.java.foodstadiumapi.repository.PedidoRepository;
import br.com.java.foodstadiumapi.repository.RestauranteProdutoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PedidoProdutoController {
    @Autowired
    private PedidoProdutoRepository repository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RestauranteProdutoRepository restauranteProdutoRepository;

    @ApiOperation(value = "Listar pedidos com produtos")
    @GetMapping("/pedidos/produtos")
    public List<PedidoProduto> listar(){
        return repository.findAll();
    }

    @ApiOperation(value = "Detalhar pedido com produtos")
    @GetMapping("/pedidos/produtos/{id}")
    public Optional<PedidoProduto> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }

    @ApiOperation(value = "Atualizar pedido com produtos")
    @PutMapping("/pedidos/produtos/{id}")
    public PedidoProduto atualizar(@RequestBody PedidoProduto novoPedido, @PathVariable Long id){
        return repository.findById(id)
                .map(pedido -> {
                    pedido.setObservacao(novoPedido.getObservacao());
                    pedido.setQuantidade(novoPedido.getQuantidade());
                    return repository.save(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return repository.save(novoPedido);
                });
    }
    @ApiOperation(value = "Cadastrar pedido com produtos")
    @PostMapping("/pedidos/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoProduto deletar(@RequestBody PedidoProduto pedidoProduto) throws Exception {
        RestauranteProduto restauranteProduto = restauranteProdutoRepository.findById(pedidoProduto.getRestauranteProduto().getId())
                .orElseThrow(Exception::new);
        Pedido pedido = pedidoRepository.findById(pedidoProduto.getPedido().getId())
                .orElseThrow(Exception::new);
        pedidoProduto.setPedido(pedido);
        pedidoProduto.setRestauranteProduto(restauranteProduto);
        return repository.save(pedidoProduto);
    }

    @ApiOperation(value = "Deletar pedido com produtos")
    @DeleteMapping("/pedidos/produtos/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
