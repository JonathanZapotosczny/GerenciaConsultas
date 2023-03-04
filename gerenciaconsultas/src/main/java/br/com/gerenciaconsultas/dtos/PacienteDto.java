package br.com.gerenciaconsultas.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {

    @NotBlank(message = "O campo CPF é obrigátorio!")
    @Length(min = 14, max = 14, message = "O campo CPF deverá ter exatamente {max} caracteres!")
    @CPF
    private String cpf;

    @NotBlank(message = "O campo NOME é obrigátorio!")
    private String nome;
    
    @NotBlank(message = "O campo E-MAIL é obrigátorio!")
    @Email
    private String email;

    @NotBlank(message = "O campo TELEFONE é obrigátorio!")
    @Length(min = 16, max = 16, message = "O campo TELEFONE deverá ter exatamente {max} caracteres!")
    private String telefone;

    @NotNull(message = "O campo NASCIMENTO é obrigátorio!")
    @JsonFormat(pattern = "dd/MM/yyyy") 
    private LocalDate dataNascimento;

    private String logradouro;

    private int numero;

    private String bairro;

    private String cidade;

    private String cep;

    private String sexo;

    private Double altura;

    private Double peso;
    
    private String observacoes;

    private Boolean ativo;
}