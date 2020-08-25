package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.Cliente;
import br.com.java.foodstadiumapi.model.Usuario;
import br.com.java.foodstadiumapi.repository.ClienteRepository;
import br.com.java.foodstadiumapi.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @ApiOperation(value = "Listar clientes")
    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @ApiOperation(value = "Detalhar cliente")
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> detalhar(@PathVariable Long id){
        return clienteRepository.findById(id);
    }

    @ApiOperation(value = "Cadastrar cliente")
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente,  UriComponentsBuilder uriBuilder) throws Exception {
        Usuario usuario = usuarioRepository.findById(cliente.getUsuario().getId())
                .orElseThrow(Exception::new);
        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @ApiOperation(value = "Atualizar cliente")
    @PutMapping("/clientes/{id}")
    public Cliente atualizar(@RequestBody Cliente novoCliente, @PathVariable Long id){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(novoCliente.getNome());
                    cliente.setSobrenome(novoCliente.getSobrenome());
                    cliente.setCpf(novoCliente.getCpf());
                    cliente.setTelefone(novoCliente.getTelefone());
                    cliente.setIdade(novoCliente.getIdade());
                    return clienteRepository.save(cliente);
                })
                .orElseGet(() -> {
                    novoCliente.setId(id);
                    return clienteRepository.save(novoCliente);
                });
    }

    @ApiOperation(value = "Deletar cliente")
    @DeleteMapping("/clientes/{id}")
    void deletar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
