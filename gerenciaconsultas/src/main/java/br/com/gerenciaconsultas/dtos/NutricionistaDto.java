package br.com.gerenciaconsultas.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutricionistaDto {
    
    @NotBlank(message = "O campo CRN é obrigátorio!")
    @Length(min = 11, max = 11, message = "O campo CRN deverá ter exatamente {max} caracteres!")
    private String crn;

    @NotBlank(message = "O campo NOME é obrigátorio!")
    private String nome;
    
    @NotBlank(message = "O campo E-MAIL é obrigátorio!")
    @Email
    private String email;

    @NotBlank(message = "O campo TELEFONE é obrigátorio!")
    @Length(min = 16, max = 16, message = "O campo TELEFONE deverá ter exatamente {max} caracteres!")
    private String telefone;

    private Boolean ativo;
    
}