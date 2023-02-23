package br.com.gerenciaconsultas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.NutricionistaDto;
import br.com.gerenciaconsultas.models.Nutricionista;
import br.com.gerenciaconsultas.repositories.NutricionistaRepository;

@Service
public class NutricionistaService {
    
    private final NutricionistaRepository nutricionistaRepository;
    private final ModelMapper modelMapper;

    public NutricionistaService(NutricionistaRepository nutricionistaRepository, ModelMapper modelMapper) {
        this.nutricionistaRepository = nutricionistaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Nutricionista create(NutricionistaDto nutricionistaDto) {

        Nutricionista nutricionista = this.modelMapper.map(nutricionistaDto, Nutricionista.class);
        nutricionista.setAtivo(true);

        return nutricionistaRepository.save(nutricionista);
    }

    @Transactional
    public Nutricionista update(NutricionistaDto nutricionistaDto, Integer id) {

        Optional<Nutricionista> optNutricionista = nutricionistaRepository.findById(id);

        if(!optNutricionista.isPresent()) {
            throw new RuntimeException("NUTRICIONISTA não encontrada!");
        }

        Nutricionista nutricionistaAtualizado = this.modelMapper.map(nutricionistaDto, Nutricionista.class);
        nutricionistaAtualizado.setId(optNutricionista.get().getId());

        return nutricionistaRepository.save(nutricionistaAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Nutricionista> optNutricionista = nutricionistaRepository.findById(id);

        if(!optNutricionista.isPresent()) {
            throw new RuntimeException("NUTRICIONISTA não encontrada!");
        }

        Nutricionista nutricionista = optNutricionista.get();
        nutricionistaRepository.delete(nutricionista);
    }

    public Optional<Nutricionista> findById(Integer id) {

        Optional<Nutricionista> optNutricionista = nutricionistaRepository.findById(id);

        if(!optNutricionista.isPresent()) {
            throw new RuntimeException("NUTRICIONISTA não encontrada!");
        }

        return optNutricionista;
    }

    public List<Nutricionista> findAll() {
        return nutricionistaRepository.findAll();
    }
}