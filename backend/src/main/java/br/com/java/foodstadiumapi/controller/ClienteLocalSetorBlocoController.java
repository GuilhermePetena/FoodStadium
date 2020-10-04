package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import br.com.java.foodstadiumapi.model.dto.ClienteLocalSetorBlocoDTO;
import br.com.java.foodstadiumapi.model.form.ClienteLocalSetorBlocoForm;
import br.com.java.foodstadiumapi.model.form.ClienteForm;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
import br.com.java.foodstadiumapi.repository.LocalSetorBlocoRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ClienteLocalSetorBlocoController {

    @Autowired
    private ClienteLocalSetorBlocoRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LocalSetorBlocoRepository localSetorBlocoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar locais do cliente")
    @GetMapping("/clientes/local")
    public List<ClienteLocalSetorBlocoDTO> listar() {
        return paraListaModel(repository.findAll());
    }

    @ApiOperation(value = "Validar se o cliente tem localização do cliente")
    @GetMapping("/clientes/validarlocalizacao/{id}")
    public List<ClienteLocalSetorBlocoDTO> detalhar(@PathVariable Long id) {
        return paraListaModel(repository.findAllByCliente_Usuario_Id(id));
    }

    @ApiOperation(value = "Detalhes do local do cliente")
    @GetMapping("/clientes/local/{id}")
    public ResponseEntity<ClienteLocalSetorBlocoDTO> detalhes(@PathVariable Long id) {

        Optional<ClienteLocalSetorBloco> clienteLocalSetorBloco = repository.findById(id);
        if (clienteLocalSetorBloco.isPresent()){
            ClienteLocalSetorBlocoDTO clienteLocalSetorBlocoDTO = paraModel(clienteLocalSetorBloco.get());
            return ResponseEntity.ok(clienteLocalSetorBlocoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cadastrar local do cliente")
    @PostMapping(value = "clientes/local",produces="application/json", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public  ClienteLocalSetorBlocoDTO cadastrar(@RequestBody ClienteLocalSetorBlocoForm clienteLocalSetorBlocoForm) throws Exception {
        ClienteLocalSetorBloco clienteLocalSetorBloco = paraEntity(clienteLocalSetorBlocoForm);
        Cliente cliente = clienteRepository.findById(clienteLocalSetorBlocoForm.getCliente().getId())
                .orElseThrow(Exception::new);
        LocalSetorBloco localSetorBloco = localSetorBlocoRepository.findById(clienteLocalSetorBlocoForm.getLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        clienteLocalSetorBloco.setLocalSetorBloco(localSetorBloco);
        clienteLocalSetorBloco.setCliente(cliente);
        clienteLocalSetorBloco.setDataPresente(LocalDate.now());
        return paraModel(repository.save(clienteLocalSetorBloco));
    }

    @ApiOperation(value = "Atualizar local do cliente")
    @PutMapping(value = "clientes/local/{id}",produces="application/json", consumes="application/json" )
    public ResponseEntity<ClienteLocalSetorBlocoDTO> atualizar(@RequestBody ClienteLocalSetorBlocoForm novoClienteLocalSetorBloco, @PathVariable Long id) throws Exception {
            ClienteLocalSetorBloco clienteLocalSetorBloco = paraEntity(novoClienteLocalSetorBloco);
        LocalSetorBloco localSetorBloco = localSetorBlocoRepository.findById(clienteLocalSetorBloco.getLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        clienteLocalSetorBloco.setLocalSetorBloco(localSetorBloco);
            return repository.findById(id)
                    .map(clienteLocalSetorBlocoClass -> {
                        clienteLocalSetorBlocoClass.setLocalSetorBloco(clienteLocalSetorBloco.getLocalSetorBloco());
                        return ResponseEntity.ok(paraModel(repository.save(clienteLocalSetorBlocoClass)));
                    })
                    .orElseGet(() -> {
                        clienteLocalSetorBloco.setId(id);
                        return ResponseEntity.ok(paraModel(repository.save(clienteLocalSetorBloco)));
                    });
    }

    @ApiOperation(value = "Deletar local do cliente")
    @DeleteMapping("clientes/local/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

    @ApiOperation(value = "Deletar todos os locais do cliente")
    @DeleteMapping("clientes/local/todos/{id}")
    public void deletar2(@PathVariable Long id){
        repository.deleteAllByCliente_Usuario_Id(id);
    }


    private ClienteLocalSetorBlocoDTO paraModel(ClienteLocalSetorBloco clienteLocalSetorBloco){
        return modelMapper.map(clienteLocalSetorBloco, (Type) ClienteLocalSetorBlocoDTO.class);
    }
    private List<ClienteLocalSetorBlocoDTO> paraListaModel(List<ClienteLocalSetorBloco> clienteLocalSetorBlocoList){
        return clienteLocalSetorBlocoList.stream()
                .map(this::paraModel)
                .collect(Collectors.toList());
    }
    private ClienteLocalSetorBloco paraEntity(ClienteLocalSetorBlocoForm clienteLocalSetorBlocoForm) {
        return modelMapper.map(clienteLocalSetorBlocoForm, ClienteLocalSetorBloco.class);
    }

}
