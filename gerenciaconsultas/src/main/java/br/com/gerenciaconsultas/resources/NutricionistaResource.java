package br.com.gerenciaconsultas.resources;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciaconsultas.dtos.NutricionistaDto;
import br.com.gerenciaconsultas.models.Nutricionista;
import br.com.gerenciaconsultas.services.NutricionistaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/nutricionistas")
@Tag(name = "Nutricionistas")
public class NutricionistaResource {

    private final NutricionistaService nutricionistaService;

    public NutricionistaResource(NutricionistaService nutricionistaService) {
        this.nutricionistaService = nutricionistaService;
    }
    
    @PostMapping
    public ResponseEntity<Nutricionista> createNutricionista(@RequestBody @Valid NutricionistaDto nutricionistaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.nutricionistaService.create(nutricionistaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nutricionista> updateNutricionista(@PathVariable(value = "id") Integer id, 
                                                            @RequestBody @Valid NutricionistaDto nutricionistaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.nutricionistaService.update(nutricionistaDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNutricionista(@PathVariable(value = "id") Integer id) {

        this.nutricionistaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("NUTRICIONISTA deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> getOneNutricionista(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.nutricionistaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Nutricionista>> getAllNutricionistas(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.nutricionistaService.findAll(pageable));
    }
}