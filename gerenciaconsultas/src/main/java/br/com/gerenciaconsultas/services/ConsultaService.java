package br.com.gerenciaconsultas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.ConsultaDto;
import br.com.gerenciaconsultas.models.Consulta;
import br.com.gerenciaconsultas.models.Nutricionista;
import br.com.gerenciaconsultas.models.Paciente;
import br.com.gerenciaconsultas.repositories.ConsultaRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;
    private final NutricionistaService nutricionistaService;
    private final ModelMapper modelMapper;

    public ConsultaService(ConsultaRepository consultaRepository,
                           ModelMapper modelMapper,
                           PacienteService pacienteService,
                           NutricionistaService nutricionistaService) {
        this.consultaRepository = consultaRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.nutricionistaService = nutricionistaService;
    }
    
    @Transactional
    public Consulta create(ConsultaDto consultaDto) {

        Optional<Nutricionista> optNutricionista = this.nutricionistaService.findById(consultaDto.getNutricionista());
        Optional<Paciente> optPaciente = this.pacienteService.findById(consultaDto.getPaciente());

        if(!optPaciente.isPresent()) {
            throw new RuntimeException("PACIENTE não encontrado!");
        }

        if(!optNutricionista.isPresent()) {
            throw new RuntimeException("NUTRICIONISTA não encontrada!");
        }
        
        Nutricionista nutricionista = optNutricionista.get();
        Paciente paciente = optPaciente.get();
        Consulta consulta = this.modelMapper.map(consultaDto, Consulta.class);
        
        consulta.setNutricionista(nutricionista);
        consulta.setPaciente(paciente);
        consulta.setAtivo(true);

        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta update(ConsultaDto consultaDto, Integer id) {

        Optional<Consulta> optConsulta = consultaRepository.findById(id);
        Optional<Nutricionista> optNutricionista = this.nutricionistaService.findById(consultaDto.getNutricionista());
        Optional<Paciente> optPaciente = this.pacienteService.findById(consultaDto.getPaciente());

        if(!optConsulta.isPresent()) {
            throw new RuntimeException("CONSULTA não encontrada!");
        }

        if(!optNutricionista.isPresent()) {
            throw new RuntimeException("NUTRICIONISTA não encontrada!");
        }

        if(!optPaciente.isPresent()) {
            throw new RuntimeException("PACIENTE não encontrado!");
        }

        Nutricionista nutricionista = optNutricionista.get();
        Paciente paciente = optPaciente.get();
        Consulta consultaAtualizado = this.modelMapper.map(consultaDto, Consulta.class);
        consultaAtualizado.setId(optConsulta.get().getId());
        consultaAtualizado.setPaciente(paciente);
        consultaAtualizado.setNutricionista(nutricionista);

        return consultaRepository.save(consultaAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Consulta> optConsulta = consultaRepository.findById(id);

        if(!optConsulta.isPresent()) {
            throw new RuntimeException("CONSULTA não encontrada!");
        }

        Consulta consulta = optConsulta.get();

        consultaRepository.delete(consulta);
    }

    public Optional<Consulta> findById(Integer id) {

        Optional<Consulta> optConsulta = consultaRepository.findById(id);

        if(!optConsulta.isPresent()) {
            throw new RuntimeException("CONSULTA não encontrada!");
        }
        
        return optConsulta;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }
    
}