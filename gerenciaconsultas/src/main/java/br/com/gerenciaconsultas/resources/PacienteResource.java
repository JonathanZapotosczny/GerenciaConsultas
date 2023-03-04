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

import br.com.gerenciaconsultas.dtos.PacienteDto;
import br.com.gerenciaconsultas.models.Paciente;
import br.com.gerenciaconsultas.services.PacienteService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes")
public class PacienteResource {

    private final PacienteService pacienteService;

    public PacienteResource(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pacienteService.create(pacienteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable(value = "id") Integer id, 
                                                 @RequestBody @Valid PacienteDto pacienteDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.update(pacienteDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable(value = "id") Integer id) {

        this.pacienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("PACIENTE deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getOnePaciente(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> getAllPacientes(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.findAll(pageable));
    }   
}