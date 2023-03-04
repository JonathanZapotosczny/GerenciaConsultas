package br.com.gerenciaconsultas.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gerenciaconsultas.dtos.NutricionistaDto;
import br.com.gerenciaconsultas.exceptions.DataIntegrityViolationException;
import br.com.gerenciaconsultas.exceptions.NotFoundException;
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

        this.validCrn(nutricionistaDto);

        Nutricionista nutricionista = this.modelMapper.map(nutricionistaDto, Nutricionista.class);
        nutricionista.setAtivo(true);
        return this.nutricionistaRepository.save(nutricionista);
    }

    @Transactional
    public Nutricionista update(NutricionistaDto nutricionistaDto, Integer id) {

        Optional<Nutricionista> optNutricionista = this.nutricionistaRepository.findById(id);

        if(optNutricionista.isEmpty()) {
            throw new NotFoundException("NUTRICIONISTA não encontrado(a) na base de dados!");
        }

        Nutricionista nutricionistaAtualizado = this.modelMapper.map(nutricionistaDto, Nutricionista.class);
        nutricionistaAtualizado.setId(optNutricionista.get().getId());
        nutricionistaAtualizado.setCrn(optNutricionista.get().getCrn());
        nutricionistaAtualizado.setAtivo(true);

        return this.nutricionistaRepository.save(nutricionistaAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Nutricionista> optNutricionista = this.nutricionistaRepository.findById(id);

        if(optNutricionista.isEmpty()) {
            throw new NotFoundException("NUTRICIONISTA não encontrado(a) na base de dados!");
        }

        Nutricionista nutricionista = optNutricionista.get();
        this.nutricionistaRepository.delete(nutricionista);
    }

    public Nutricionista findById(Integer id) {

        Optional<Nutricionista> optNutricionista = this.nutricionistaRepository.findById(id);

        return optNutricionista.orElseThrow(() -> new NotFoundException("NUTRICIONISTA não encontrado(a) na base de dados!"));
    }

    public Page<Nutricionista> findAll(Pageable pageable) {
        return this.nutricionistaRepository.findAll(pageable);
    }

    private void validCrn (NutricionistaDto nutricionistaDto) {
        Optional<Nutricionista> optNutricionista = this.nutricionistaRepository.findByCrn(nutricionistaDto.getCrn());

        if(optNutricionista.isPresent()) {
            throw new DataIntegrityViolationException("CRN Já cadastrado na base de dados!");
        }
    }
}