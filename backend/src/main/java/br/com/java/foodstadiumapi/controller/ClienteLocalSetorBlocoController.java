package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import br.com.java.foodstadiumapi.model.LocalSetorBloco;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
import br.com.java.foodstadiumapi.repository.LocalSetorBlocoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClienteLocalSetorBlocoController {

    @Autowired
    private ClienteLocalSetorBlocoRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LocalSetorBlocoRepository localSetorBlocoRepository;

    @ApiOperation(value = "Listar locais do cliente")
    @GetMapping("/clientes/local")
    public List<ClienteLocalSetorBloco> listar() {
        return repository.findAll();
    }

    @ApiOperation(value = "Detalhes do local do cliente")
    @GetMapping("/clientes/local/{id}")
    public Optional<ClienteLocalSetorBloco> detalhes(@PathVariable Long id) {
        return repository.findById(id);
    }

    @ApiOperation(value = "Cadastrar local do cliente")
    @PostMapping("clientes/local")
    public ResponseEntity<ClienteLocalSetorBloco> cadastrar(@RequestBody ClienteLocalSetorBloco clienteLocalSetorBloco, UriComponentsBuilder uriBuilder) throws Exception {
        Cliente cliente = clienteRepository.findById(clienteLocalSetorBloco.getCliente().getId())
                .orElseThrow(Exception::new);
        LocalSetorBloco localSetorBloco = localSetorBlocoRepository.findById(clienteLocalSetorBloco.getLocalSetorBloco().getId())
                .orElseThrow(Exception::new);
        clienteLocalSetorBloco.setLocalSetorBloco(localSetorBloco);
        clienteLocalSetorBloco.setCliente(cliente);
        clienteLocalSetorBloco.setDataPresente(LocalDate.now());
        repository.save(clienteLocalSetorBloco);
        URI uri = uriBuilder.path("clientes/local/{id}").buildAndExpand(clienteLocalSetorBloco.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteLocalSetorBloco);
    }

    @ApiOperation(value = "Atualizar local do cliente")
    @PutMapping("clientes/local/{id}")
    public ClienteLocalSetorBloco atualizar(@RequestBody ClienteLocalSetorBloco novoClienteLocalSetorBloco, @PathVariable Long id){
        return repository.findById(id)
                .map(clienteLocalSetorBloco -> {
                    clienteLocalSetorBloco.setCliente(novoClienteLocalSetorBloco.getCliente());
                    clienteLocalSetorBloco.setLocalSetorBloco(novoClienteLocalSetorBloco.getLocalSetorBloco());
                    return repository.save(clienteLocalSetorBloco);
                })
                .orElseGet(() -> {
                    novoClienteLocalSetorBloco.setId(id);
                    return repository.save(novoClienteLocalSetorBloco);
                });
    }
    @ApiOperation(value = "Deletar local do cliente")
    @DeleteMapping("clientes/local/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
