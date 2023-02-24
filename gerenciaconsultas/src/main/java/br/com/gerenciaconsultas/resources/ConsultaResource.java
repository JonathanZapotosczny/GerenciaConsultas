package br.com.gerenciaconsultas.resources;

import java.util.List;

import javax.validation.Valid;

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

import br.com.gerenciaconsultas.dtos.ConsultaDto;
import br.com.gerenciaconsultas.models.Consulta;
import br.com.gerenciaconsultas.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaResource {

    private final ConsultaService consultaService;

    public ConsultaResource(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody @Valid ConsultaDto consultaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.consultaService.create(consultaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable(value = "id") Integer id, 
                                                 @RequestBody @Valid ConsultaDto consultaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.consultaService.update(consultaDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {

        consultaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("CONSULTA deletada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> findById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.consultaService.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.consultaService.findAll());    
    }
    
}