package br.com.gerenciaconsultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciaconsultas.models.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    
}