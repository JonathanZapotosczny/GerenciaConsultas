package br.com.gerenciaconsultas.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Paciente paciente;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Nutricionista nutricionista;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;
    
    @Column(nullable = false, length = 9)
    private String status;

    private Boolean ativo;
    
}