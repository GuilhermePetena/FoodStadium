package br.com.java.foodstadiumapi.controller;

import br.com.java.foodstadiumapi.model.ClienteLocalSetorBloco;
import br.com.java.foodstadiumapi.model.LocalSetor;
import br.com.java.foodstadiumapi.model.Restaurante;
import br.com.java.foodstadiumapi.model.RestauranteLocalSetor;
import br.com.java.foodstadiumapi.model.dto.RestauranteLocalSetorDTO;
import br.com.java.foodstadiumapi.model.form.RestauranteLocalSetorForm;
import br.com.java.foodstadiumapi.repository.ClienteLocalSetorBlocoRepository;
import br.com.java.foodstadiumapi.repository.LocalSetorRepository;
import br.com.java.foodstadiumapi.repository.RestauranteLocalSetorRepository;
import br.com.java.foodstadiumapi.repository.RestauranteRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class RestauranteLocalSetorController {
    @Autowired
    private RestauranteLocalSetorRepository repository;
    @Autowired
    private LocalSetorRepository localSetorRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private ClienteLocalSetorBlocoRepository clienteLocalSetorBlocoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Listar local dos restaurantes")
    @GetMapping("/restaurantes/local")
    public List<RestauranteLocalSetorDTO> listar() {
        return paraListaModel(repository.findAll());
    }

    @ApiOperation(value="Listar restaurantes no setor do cliente")
    @GetMapping("/restaurantes/clientelocalsetor/{id}")
    public List<RestauranteLocalSetor> listar2(@PathVariable Long id) throws Exception {
        ClienteLocalSetorBloco clienteLocalSetorBloco = clienteLocalSetorBlocoRepository.findById(id)
                .orElseThrow(Exception::new);
        List<RestauranteLocalSetor> restaurantes = repository.findAll();
        return restaurantes.stream()
                .filter(restaurante -> clienteLocalSetorBloco.getLocalSetorBloco().getLocalSetor().getId().equals(restaurante.getLocalSetor().getId()))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Detalhar local do restaurante")
    @GetMapping("/restaurantes/local/{id}")
    public ResponseEntity<RestauranteLocalSetorDTO> detalhes(@PathVariable Long id) {
        Optional<RestauranteLocalSetor> restauranteLocalSetor = repository.findById(id);
        if (restauranteLocalSetor.isPresent()){
            RestauranteLocalSetorDTO restauranteLocalSetorDTO = paraModel(restauranteLocalSetor.get());
            return ResponseEntity.ok(restauranteLocalSetorDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cadastrar local do restaurante")
    @PostMapping(value = "restaurantes/local",produces="application/json", consumes="application/json")
    public RestauranteLocalSetorDTO cadastrar(@RequestBody RestauranteLocalSetorForm restauranteLocalSetorForm) throws Exception {
        RestauranteLocalSetor restauranteLocalSetor = paraEntity(restauranteLocalSetorForm);
        Restaurante restaurante = restauranteRepository.findById(restauranteLocalSetorForm.getRestaurante().getId())
                .orElseThrow(Exception::new);
        LocalSetor localSetor = localSetorRepository.findById(restauranteLocalSetorForm.getLocalSetor().getId())
                .orElseThrow(Exception::new);
        restauranteLocalSetor.setLocalSetor(localSetor);
        restauranteLocalSetor.setRestaurante(restaurante);
        return paraModel(repository.save(restauranteLocalSetor));
    }

    @ApiOperation(value = "Atualizar local do restaurante")
    @PutMapping(value = "restaurantes/local/{id}",produces="application/json", consumes="application/json" )
    public ResponseEntity<RestauranteLocalSetorDTO> atualizar(@RequestBody RestauranteLocalSetorForm novoRestauranteLocalSetor, @PathVariable Long id) throws Exception {
        RestauranteLocalSetor restauranteLocalSetor = paraEntity(novoRestauranteLocalSetor);
        LocalSetor localSetor = localSetorRepository.findById(restauranteLocalSetor.getLocalSetor().getId())
                .orElseThrow(Exception::new);
        restauranteLocalSetor.setLocalSetor(localSetor);
        return repository.findById(id)
                .map(restauranteLocalSetorClass -> {
                    restauranteLocalSetorClass.setLocalSetor(restauranteLocalSetor.getLocalSetor());
                    return ResponseEntity.ok(paraModel(repository.save(restauranteLocalSetorClass)));
                })
                .orElseGet(() -> {
                    restauranteLocalSetor.setId(id);
                    return ResponseEntity.ok(paraModel(repository.save(restauranteLocalSetor)));
                });
    }

    @ApiOperation(value = "Deletar local do restaurante")
    @DeleteMapping("/restaurantes/local/{id}")
    void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private RestauranteLocalSetorDTO paraModel(RestauranteLocalSetor restauranteLocalSetor){
        return modelMapper.map(restauranteLocalSetor, (Type) RestauranteLocalSetorDTO.class);
    }
    private List<RestauranteLocalSetorDTO> paraListaModel(List<RestauranteLocalSetor> restauranteLocalSetor){
        return restauranteLocalSetor.stream()
                .map(restauranteLocalSetor1 -> paraModel(restauranteLocalSetor1))
                .collect(Collectors.toList());
    }

    private RestauranteLocalSetor paraEntity(RestauranteLocalSetorForm restauranteLocalSetorForm) {
        return modelMapper.map(restauranteLocalSetorForm, RestauranteLocalSetor.class);
    }
}
