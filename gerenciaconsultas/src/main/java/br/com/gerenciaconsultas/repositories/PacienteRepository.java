package br.com.gerenciaconsultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciaconsultas.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    
}