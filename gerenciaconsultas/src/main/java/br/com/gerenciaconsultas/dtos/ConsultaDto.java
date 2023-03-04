package br.com.gerenciaconsultas.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDto {

    @NotNull(message = "O campo PACIENTE é obrigátorio!")
    private int paciente;

    @NotNull(message = "O campo NUTRICIONISTA é obrigátorio!")
    private int nutricionista;

    @NotNull(message = "O campo DATA DA CONSULTA é obrigátorio!")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataConsulta;
    
    @NotBlank(message = "O campo STATUS é obrigátorio!")
    private String status;
}