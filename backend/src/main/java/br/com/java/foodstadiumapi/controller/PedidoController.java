package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.model.dto.PedidoDTO;
import br.com.java.foodstadiumapi.model.form.PedidoForm;
import br.com.java.foodstadiumapi.repository.*;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
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
    private EntregadorLocalSetorRepository entregadorLocalSetorRepository;
    @Autowired
    private TipoEntregaRepository tipoEntregaRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar pedidos")
    @GetMapping(value = "/pedidos" ,produces="application/json", consumes="application/json")
    public List<PedidoDTO> listar(){
        return paraListaDTO(repository.findAll());
    }

    @ApiOperation(value = "Listar pedidos disponiveis para entrega")
    @GetMapping(value = "/pedidos/listaDisponiveis",produces="application/json", consumes="application/json")
    public List<PedidoDTO> listarDisponiveis(){
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntrega("ABERTO","ENTREGAR");
        List<Pedido> pedidos = pedido.stream()
              .filter(pedido1 -> pedido1.getClienteLocalSetorBloco().getLocalSetorBloco().getLocalSetor().getId().equals(pedido1.getRestauranteLocalSetor().getLocalSetor().getId()))
              .collect(Collectors.toList());
        return paraListaDTO(pedidos);
    }

    @ApiOperation(value = "Listar pedidos entregues por um entregador")
    @GetMapping(value = "/pedidos/listaEntregados/{id}",produces="application/json", consumes="application/json")
    public List<PedidoDTO> listarEntregues(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntregaAndEntregador("ENTREGUE","ENTREGAR", id);
        List<Pedido> pedidos = pedido.stream()
                .filter(pedido1 -> pedido1.getEntregadorLocalSetor().getEntregador().getId().equals(id))
                .collect(Collectors.toList());
        return paraListaDTO(pedidos);
    }


    @ApiOperation(value = "Detalhar pedido")
    @GetMapping(value = "/pedidos/{id}",produces="application/json", consumes="application/json")
    public ResponseEntity<PedidoDTO> detalhar(@PathVariable Long id){
        Optional<Pedido> optional = repository.findById(id);
        if (optional.isPresent()){
            PedidoDTO pedidoDTO = paraDTO(optional.get());
            return ResponseEntity.ok(pedidoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cadastrar pedido")
    @PostMapping(value = "/pedidos",produces="application/json", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastrar(@RequestBody PedidoForm pedidoForm) throws Exception {
        Pedido pedido = paraEntity(pedidoForm);
        EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorRepository.findById(pedidoForm.getEntregadorLocalSetor().getId())
                .orElseThrow(Exception::new);
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(pedidoForm.getClienteLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        RestauranteLocalSetor restauranteLocalSetor = restauranteLocalSetorRepository.findById(pedidoForm.getRestauranteLocalSetor().getId())
                .orElseThrow(Exception::new);
        TipoEntrega tipoEntrega = tipoEntregaRepository.findById(pedidoForm.getTipoEntrega().getId())
                .orElseThrow(Exception::new);
        Status status = statusRepository.findById(pedidoForm.getStatus().getId())
                .orElseThrow(Exception::new);
        pedido.setStatus(status);
        pedido.setTipoEntrega(tipoEntrega);
        pedido.setClienteLocalSetorBloco(clienteLocalSetorBloco);
        pedido.setEntregadorLocalSetor(entregadorLocalSetor);
        pedido.setRestauranteLocalSetor(restauranteLocalSetor);
        pedido.setData(LocalDate.now());
        pedido.setHora(LocalTime.now());

        return paraDTO(repository.save(pedido));
    }

    @ApiOperation(value = "Atualizar pedido")
    @PutMapping(value = "/pedidos/{id}" ,produces="application/json", consumes="application/json")
    public PedidoDTO atualizar(@RequestBody PedidoForm novoPedido, @PathVariable Long id) throws Exception {
        Pedido pedido = paraEntity(novoPedido);
        EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorRepository.findById(pedido.getEntregadorLocalSetor().getId())
                .orElseThrow(Exception::new);
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(pedido.getClienteLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        RestauranteLocalSetor restauranteLocalSetor = restauranteLocalSetorRepository.findById(pedido.getRestauranteLocalSetor().getId())
                .orElseThrow(Exception::new);
        TipoEntrega tipoEntrega = tipoEntregaRepository.findById(pedido.getTipoEntrega().getId())
                .orElseThrow(Exception::new);
        Status status = statusRepository.findById(pedido.getStatus().getId())
                .orElseThrow(Exception::new);
        pedido.setStatus(status);
        pedido.setTipoEntrega(tipoEntrega);
        pedido.setClienteLocalSetorBloco(clienteLocalSetorBloco);
        pedido.setEntregadorLocalSetor(entregadorLocalSetor);
        pedido.setRestauranteLocalSetor(restauranteLocalSetor);
        return repository.findById(id)
                .map(pedidoClass -> {
                    pedidoClass.setTipoEntrega(pedido.getTipoEntrega());
                    pedidoClass.setEntregadorLocalSetor(pedido.getEntregadorLocalSetor());
                    pedidoClass.setStatus(pedido.getStatus());
                    pedidoClass.setAvaliacaoEntregador(pedido.getAvaliacaoEntregador());
                    pedidoClass.setAvaliacaoPedido(pedido.getAvaliacaoPedido());
                    return paraDTO(repository.save(pedidoClass));
                })
                .orElseGet(() -> {
                    pedido.setId(id);
                    return paraDTO(repository.save(pedido));
                });
    }

    @ApiOperation(value = "Deletar pedido")
    @DeleteMapping("/pedidos/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private PedidoDTO paraDTO(Pedido pedido){
        return modelMapper.map(pedido, (Type) PedidoDTO.class);
    }
    private List<PedidoDTO> paraListaDTO(List<Pedido> pedidoList){
        return pedidoList.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }
    private Pedido paraEntity(PedidoForm pedidoForm) {
        return modelMapper.map(pedidoForm, Pedido.class);
    }
    private PedidoForm paraForm(Pedido pedido){
        return modelMapper.map(pedido, PedidoForm.class);
    }
}