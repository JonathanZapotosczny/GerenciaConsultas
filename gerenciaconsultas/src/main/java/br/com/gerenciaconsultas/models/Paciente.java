package br.com.gerenciaconsultas.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 16)
    private String telefone;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 255)
    private String logradouro;

    private int numero;

    @Column(length = 45)
    private String bairro;

    @Column(length = 45)
    private String cidade;

    @Column(length = 9)
    private String cep;

    @Column(length = 9)
    private String sexo;

    private Double altura;

    private Double peso;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    private Boolean ativo; 
    
}