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

import br.com.gerenciaconsultas.dtos.PacienteDto;
import br.com.gerenciaconsultas.models.Paciente;
import br.com.gerenciaconsultas.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
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

        pacienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("O PACIENTE foi deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getOnePaciente(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes(){
        return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.findAll());
    }   
}