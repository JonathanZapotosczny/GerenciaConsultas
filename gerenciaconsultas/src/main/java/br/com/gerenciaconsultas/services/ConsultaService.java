package br.com.gerenciaconsultas.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.ConsultaDto;
import br.com.gerenciaconsultas.exceptions.NotFoundException;
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

        Nutricionista nutricionista = this.nutricionistaService.findById(consultaDto.getNutricionista());
        Paciente paciente = this.pacienteService.findById(consultaDto.getPaciente());

        Consulta consulta = this.modelMapper.map(consultaDto, Consulta.class);
        
        consulta.setNutricionista(nutricionista);
        consulta.setPaciente(paciente);
        consulta.setStatus("AGENDADO");

        return this.consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta update(ConsultaDto consultaDto, Integer id) {

        Optional<Consulta> optConsulta = this.consultaRepository.findById(id);
        Nutricionista nutricionista = this.nutricionistaService.findById(consultaDto.getNutricionista());
        Paciente paciente = this.pacienteService.findById(consultaDto.getPaciente());

        if(optConsulta.isEmpty()) {
            throw new NotFoundException("CONSULTA não encontrada na base de dados!");
        }

        Consulta consultaAtualizado = this.modelMapper.map(consultaDto, Consulta.class);
        consultaAtualizado.setId(optConsulta.get().getId());
        consultaAtualizado.setPaciente(paciente);
        consultaAtualizado.setNutricionista(nutricionista);

        return this.consultaRepository.save(consultaAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Consulta> optConsulta = this.consultaRepository.findById(id);

        if(optConsulta.isEmpty()) {
            throw new NotFoundException("CONSULTA não encontrada na base de dados!");
        }

        Consulta consulta = optConsulta.get();

        this.consultaRepository.delete(consulta);
    }

    public Consulta findById(Integer id) {

        Optional<Consulta> optConsulta = this.consultaRepository.findById(id);
        
        return optConsulta.orElseThrow(() -> new NotFoundException("CONSULTA não encontrada na base de dados!"));
    }

    public Page<Consulta> findAll(Pageable pageable) {
        return this.consultaRepository.findAll(pageable);
    }  
}