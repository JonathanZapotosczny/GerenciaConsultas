package br.com.gerenciaconsultas.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.PacienteDto;
import br.com.gerenciaconsultas.exceptions.DataIntegrityViolationException;
import br.com.gerenciaconsultas.exceptions.NotFoundException;
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

        validCpf(pacienteDto);

        Paciente paciente = this.modelMapper.map(pacienteDto, Paciente.class);
        paciente.setAtivo(true);
        return this.pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente update(PacienteDto pacienteDto, Integer id) {

        Optional<Paciente> optPaciente = this.pacienteRepository.findById(id);
        
        if(optPaciente.isEmpty()) {
            throw new NotFoundException("PACIENTE não encontrado(a) na base de dados!");
        }

        Paciente pacienteAtualizado = this.modelMapper.map(pacienteDto, Paciente.class);
        pacienteAtualizado.setId(optPaciente.get().getId());
        pacienteAtualizado.setCpf(optPaciente.get().getCpf());

        return this.pacienteRepository.save(pacienteAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Paciente> optPaciente = this.pacienteRepository.findById(id);

        if(optPaciente.isEmpty()) {
            throw new NotFoundException("PACIENTE não encontrado(a) na base de dados!");
        }

        Paciente paciente = optPaciente.get();
        this.pacienteRepository.delete(paciente);
    }

    public Paciente findById(Integer id) {

        Optional<Paciente> optPaciente = this.pacienteRepository.findById(id);
        
        return optPaciente.orElseThrow(() -> new NotFoundException("PACIENTE não encontrado(a) na base de dados!"));
    }

    public Page<Paciente> findAll(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }

    private void validCpf (PacienteDto pacienteDto) {
        Optional<Paciente> optPaciente = this.pacienteRepository.findByCpf(pacienteDto.getCpf());

        if(optPaciente.isPresent()) {
            throw new DataIntegrityViolationException("CPF Já cadastrado na base de dados!");
        }
    }
}