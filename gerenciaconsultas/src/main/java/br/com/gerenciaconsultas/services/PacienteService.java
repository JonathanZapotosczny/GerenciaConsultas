package br.com.gerenciaconsultas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.PacienteDto;
import br.com.gerenciaconsultas.models.Paciente;
import br.com.gerenciaconsultas.repositories.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Paciente create(PacienteDto pacienteDto) {

        Paciente paciente = this.modelMapper.map(pacienteDto, Paciente.class);
        paciente.setAtivo(true);

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente update(PacienteDto pacienteDto, Integer id) {

        Optional<Paciente> optPaciente = pacienteRepository.findById(id);
        
        if(!optPaciente.isPresent()) {
            throw new RuntimeException("PACIENTE não encontrado!");
        }

        Paciente pacienteAtualizado = this.modelMapper.map(pacienteDto, Paciente.class);
        pacienteAtualizado.setId(optPaciente.get().getId());

        return pacienteRepository.save(pacienteAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Paciente> optPaciente = pacienteRepository.findById(id);

        if(!optPaciente.isPresent()) {
            throw new RuntimeException("PACIENTE não encontrado!");
        }

        Paciente paciente = optPaciente.get();

        pacienteRepository.delete(paciente);
    }

    public Optional<Paciente> findById(Integer id) {

        Optional<Paciente> optPaciente = pacienteRepository.findById(id);

        if(!optPaciente.isPresent()) {
            throw new RuntimeException("PACIENTE não encontrado!");
        }
        
        return optPaciente;
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}