package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.*;
import br.com.java.foodstadiumapi.model.dto.ClienteLocalSetorBlocoDTO;
import br.com.java.foodstadiumapi.model.dto.EntregadorLocalSetorDTO;
import br.com.java.foodstadiumapi.model.form.ClienteLocalSetorBlocoForm;
import br.com.java.foodstadiumapi.model.form.EntregadorLocalSetorForm;
import br.com.java.foodstadiumapi.repository.EntregadorLocalSetorRepository;
import br.com.java.foodstadiumapi.repository.EntregadorRepository;
import br.com.java.foodstadiumapi.repository.LocalSetorRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class EntregadorLocalSetorController {
    @Autowired
    private EntregadorLocalSetorRepository repository;
    @Autowired
    private LocalSetorRepository localSetorRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar local dos entregadores")
    @GetMapping("/entregadores/local")
    public List<EntregadorLocalSetorDTO> listar() {
        return paraListaModel(repository.findAll());
    }

    @ApiOperation(value = "Detalhar local do entregador")
    @GetMapping("/entregadores/local/{id}")
    public ResponseEntity<EntregadorLocalSetorDTO> detalhes(@PathVariable Long id) {
        Optional<EntregadorLocalSetor> entregadorLocalSetor = repository.findById(id);
        if (entregadorLocalSetor.isPresent()){
            EntregadorLocalSetorDTO entregadorLocalSetorDTO = paraModel(entregadorLocalSetor.get());
            return ResponseEntity.ok(entregadorLocalSetorDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Lista Detalhar local do entregador buscando pelo ID DO USUARIO")
    @GetMapping("/entregadores/local/entregador/{id}")
    public List<EntregadorLocalSetorDTO> detalhes2(@PathVariable Long id) {
        return paraListaModel(repository.findAllByEntregador_Usuario_Id(id));
    }

    @ApiOperation(value = "Cadastrar local do entregador")
    @PostMapping(value = "entregadores/local",produces="application/json", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregadorLocalSetorDTO cadastrar(@RequestBody EntregadorLocalSetorForm entregadorLocalSetorForm) throws Exception {
        EntregadorLocalSetor entregadorLocalSetor = paraEntity(entregadorLocalSetorForm);
        Entregador entregador = entregadorRepository.findById(entregadorLocalSetorForm.getEntregador().getId())
                .orElseThrow(Exception::new);
        LocalSetor localSetor = localSetorRepository.findById(entregadorLocalSetorForm.getLocalSetor().getId())
                .orElseThrow(Exception::new);
        entregadorLocalSetor.setLocalSetor(localSetor);
        entregadorLocalSetor.setEntregador(entregador);
        entregadorLocalSetor.setDataPresente(LocalDate.now());
        return paraModel(repository.save(entregadorLocalSetor));
    }

    @ApiOperation(value = "Atualizar local do entregador")
    @PutMapping(value = "entregadores/local/{id}",produces="application/json", consumes="application/json" )
    public ResponseEntity<EntregadorLocalSetorDTO> atualizar(@RequestBody EntregadorLocalSetorForm novoEntregadorLocalSetor, @PathVariable Long id) throws Exception {
        EntregadorLocalSetor entregadorLocalSetor = paraEntity(novoEntregadorLocalSetor);
        LocalSetor localSetor = localSetorRepository.findById(entregadorLocalSetor.getLocalSetor().getId())
                .orElseThrow(Exception::new);
        entregadorLocalSetor.setLocalSetor(localSetor);
        return repository.findById(id)
                .map(entregadorLocalSetorClass -> {
                    entregadorLocalSetorClass.setLocalSetor(entregadorLocalSetor.getLocalSetor());
                    return ResponseEntity.ok(paraModel(repository.save(entregadorLocalSetorClass)));
                })
                .orElseGet(() -> {
                    entregadorLocalSetor.setId(id);
                    return ResponseEntity.ok(paraModel(repository.save(entregadorLocalSetor)));
                });
    }

    @ApiOperation(value = "Deletar local do entregador")
    @DeleteMapping("/entregadores/local/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private EntregadorLocalSetorDTO paraModel(EntregadorLocalSetor entregadorLocalSetor){
        return modelMapper.map(entregadorLocalSetor, (Type) EntregadorLocalSetorDTO.class);
    }
    private List<EntregadorLocalSetorDTO> paraListaModel(List<EntregadorLocalSetor> entregadorLocalSetor){
        return entregadorLocalSetor.stream()
                .map(entregadorLocalSetor1 -> paraModel(entregadorLocalSetor1))
                .collect(Collectors.toList());
    }
    private EntregadorLocalSetor paraEntity(EntregadorLocalSetorForm entregadorLocalSetorForm) {
        return modelMapper.map(entregadorLocalSetorForm, EntregadorLocalSetor.class);
    }
}
