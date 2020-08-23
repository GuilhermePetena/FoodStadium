package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.repository.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClienteLocalSetorBlocoRepository clienteLocalSetorBlocoRepository;
    @Autowired
    private RestauranteLocalSetorRepository restauranteLocalSetorRepository;
    @Autowired
    private EntregadorLocalSetorBlocoRepository entregadorLocalSetorBlocoRepository;
    @Autowired
    private TipoEntregaRepository tipoEntregaRepository;
    @Autowired
    private StatusRepository statusRepository;

    @ApiOperation(value = "Listar pedidos")
    @GetMapping("/pedidos")
    public List<Pedido> listar(){
        return repository.findAll();
    }

    @ApiOperation(value = "Listar pedidos disponiveis para entrega")
    @GetMapping("/pedidos/listaDisponiveis")
    public List<Pedido> listarDisponiveis(){
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntrega("ABERTO","ENTREGAR");
        List<Pedido> pedidos = pedido.stream()
              .filter(pedido1 -> pedido1.getClienteLocalSetorBloco().getLocalSetorBloco().getLocalSetor().getId().equals(pedido1.getRestauranteLocalSetor().getLocalSetor().getId()))
              .collect(Collectors.toList());
        return pedidos;
    }

    @ApiOperation(value = "Listar pedidos entregues por um entregador")
    @GetMapping("/pedidos/listaEntregados/{id}")
    public List<Pedido> listarEntregues(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntregaAndEntregador("ENTREGUE","ENTREGAR", id);
        List<Pedido> pedidos = pedido.stream()
                .filter(pedido1 -> pedido1.getEntregadorLocalSetor().getEntregador().getId().equals(id))
                .collect(Collectors.toList());
        return pedidos;
    }


    @ApiOperation(value = "Detalhar pedido")
    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> detalhar(@PathVariable Long id){
        return repository.findById(id);
    }

    @ApiOperation(value = "Cadastrar pedido")
    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido, UriComponentsBuilder uriBuilder) throws Exception {
        EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorBlocoRepository.findById(pedido.getEntregadorLocalSetor().getId())
                .orElseThrow(Exception::new);
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(pedido.getClienteLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        RestauranteLocalSetor restauranteLocalSetor = restauranteLocalSetorRepository.findById(pedido.getRestauranteLocalSetor().getId())
                .orElseThrow(Exception::new);
        TipoEntrega tipoEntrega = tipoEntregaRepository.findById(pedido.getTipoEntrega().getId())
                .orElseThrow(Exception::new);
        Status status = statusRepository.findById(pedido.getStatus().getId())
                .orElseThrow(Exception::new);
        pedido.setData(LocalDate.now());
        pedido.setHora(LocalTime.now());
        repository.save(pedido);
        URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @ApiOperation(value = "Atualizar pedido")
    @PutMapping("/pedidos/{id}")
    public Pedido atualizar(@RequestBody Pedido novoPedido, @PathVariable Long id){
        return repository.findById(id)
                .map(pedido -> {
                    pedido.setTipoEntrega(novoPedido.getTipoEntrega());
                    pedido.setEntregadorLocalSetor(novoPedido.getEntregadorLocalSetor());
                    pedido.setStatus(novoPedido.getStatus());
                    return repository.save(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return repository.save(novoPedido);
                });
    }

    @ApiOperation(value = "Deletar pedido")
    @DeleteMapping("/pedidos/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
