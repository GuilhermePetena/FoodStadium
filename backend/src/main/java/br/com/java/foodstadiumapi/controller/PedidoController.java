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

import java.lang.reflect.Type;
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
    @GetMapping(value = "/pedidos/listaDisponiveis/{id}")
    public List<PedidoDTO> listarDisponiveis(@PathVariable Long id) throws Exception {
        EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorRepository.findById(id).orElseThrow(Exception::new);
        List<Pedido> pedido = repository.findAllByStatus_IdAndAndTipoEntrega_Id(Long.parseLong("2"), Long.parseLong("2"));
        List<Pedido> pedidos = pedido.stream()
                .filter(pedido2 -> pedido2.getClienteLocalSetorBloco().getLocalSetorBloco().getLocalSetor().getId().equals(entregadorLocalSetor.getLocalSetor().getId()))
                .collect(Collectors.toList());
        return paraListaDTO(pedido);
    }
    @ApiOperation(value = "Lista pedido em andamento pelo cliente para entrega")
    @GetMapping(value = "/pedidos/listaandamento/{id}")
    public List<PedidoDTO> listasPedidosEmAndamentoCliente(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeOrStatus_NomeOrStatus_NomeAndClienteLocalSetorBloco_Cliente_Id("PREPARANDO","ABERTO","BUSCAR",id);
        return paraListaDTO(pedido);
    }


    @ApiOperation(value = "Detalhe pedido em andamento pelo entregador para entrega")
    @GetMapping(value = "/pedidos/listaDetalhes/{id}")
    public List<PedidoDTO> detalhesPedidosEmAndamentoEntregador(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntregaAndEntregadorLocalSetorId("ATRIBUIDO","ENTREGAR",id);
        return paraListaDTO(pedido);
    }
    
    @ApiOperation(value = "Listar pedidos realizados pelo cliente passando ID DO CLIENTE e status como parametro")
    @GetMapping(value = "/pedidos/listaPedidosRealizados/{id}")
    public List<PedidoDTO> listaPedidosRealizados(@PathVariable Long id, @RequestParam String status){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndClienteLocalSetorBloco_Cliente(status, id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos entregues por um entregador")
    @GetMapping(value = "/pedidos/listaEntregados/{id}")
    public List<PedidoDTO> listarEntregues(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndTipoEntrega_NomeAndAndEntregadorLocalSetor_Entregador_Id("ENTREGUE","ENTREGAR", id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos com status buscar por um entregador")
    @GetMapping(value = "/pedidos/listaBuscarEntregador/{id}")
    public List<PedidoDTO> listarBuscar(@PathVariable Long id) throws Exception {
        EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorRepository.findById(id).orElseThrow(Exception::new);
        List<Pedido> pedido = repository.findAllbyStatusAndTipoEntrega("BUSCAR","ENTREGAR");
        return paraListaDTO(pedido).stream()
                .filter(pedidoDT -> pedidoDT.getClienteLocalSetorBloco().getLocalSetorBloco().getLocalSetor().getId().equals(entregadorLocalSetor.getLocalSetor().getId()))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar pedidos com status buscar por um cliente")
    @GetMapping(value = "/pedidos/listaBuscarCliente/{id}")
    public List<PedidoDTO> listarBuscar2(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndTipoEntrega_NomeAndClienteLocalSetorBloco_Id("BUSCAR","BUSCAR", id);
        return paraListaDTO(pedido);
    }


    @ApiOperation(value = "Listar pedidos aceitar restaurante")
    @GetMapping(value = "/pedidos/listaAceitar/{id}")
    public List<PedidoDTO> listarAceitarRestaurante(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("ABERTO", id);
        return paraListaDTO(pedido);
    }
    @ApiOperation(value = "Listar pedidos aceitar Preparando restaurante")
    @GetMapping(value = "/pedidos/listaAceitarPreparando/{id}")
    public List<PedidoDTO> listarAceitarRestaurante2(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("PREPARANDO", id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos entregues pelo restaurante")
    @GetMapping(value = "/pedidos/listaEntregadosRestaurante/{id}")
    public List<PedidoDTO> listarEntreguesRestaurante(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("ENTREGUE", id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos com status atribuido pelo restaurante")
    @GetMapping(value = "/pedidos/listaAtribuidosRestaurante/{id}")
    public List<PedidoDTO> listarAtribuidosRestaurante(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("ATRIBUIDO", id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos com status BUSCAR pelo restaurante")
    @GetMapping(value = "/pedidos/listaBuscarRestaurante/{id}")
    public List<PedidoDTO> listarBuscarRestaurante(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("BUSCAR", id);
        return paraListaDTO(pedido);
    }

    @ApiOperation(value = "Listar pedidos com status BUSCAR e tipo de entrega BUSCAR pelo restaurante")
    @GetMapping(value = "/pedidos/listaEntregaBuscarRestaurante/{id}")
    public List<PedidoDTO> listarBuscarRestaurante2(@PathVariable Long id){
        List<Pedido> pedido = repository.findAllByStatus_NomeAndAndRestauranteLocalSetor_Id("BUSCAR", id);
        return paraListaDTO(pedido).stream()
                .filter(pedidos -> pedidos.getTipoEntrega().getNome().equals("BUSCAR"))
                .collect(Collectors.toList());
    }


    @ApiOperation(value = "Detalhar pedido")
    @GetMapping(value = "/pedidos/{id}")
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
        pedido.setValorTotal(pedidoForm.getValorTotal());
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
        if(pedido.getEntregadorLocalSetor() != null) {
            EntregadorLocalSetor entregadorLocalSetor = entregadorLocalSetorRepository.findById(pedido.getEntregadorLocalSetor().getId())
                    .orElseThrow(Exception::new);
            pedido.setEntregadorLocalSetor(entregadorLocalSetor);
        }
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
        pedido.setValorTotal(novoPedido.getValorTotal());
        pedido.setClienteLocalSetorBloco(clienteLocalSetorBloco);
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
