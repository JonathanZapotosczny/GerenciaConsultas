package br.com.gerenciaconsultas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciaconsultas.models.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Integer> {
    
    Optional<Nutricionista> findByCrn(String crn);
}