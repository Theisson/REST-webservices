package io.github.theisson.webservices.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record ClientRequestDTO(
    @NotBlank(message = "Campo requerido")
    String name,

    @NotBlank(message = "Campo requerido")
    String cpf,

    @NotNull(message = "Renda deve ser informada")
    @PositiveOrZero(message = "Renda não pode ser negativa")
    Double income,

    @NotNull(message = "Data de nascimento deve ser informada")
    @PastOrPresent(message = "Data de nascimento inválida")
    LocalDate birthDate,

    @NotNull(message = "Quantidade de filhos deve ser informada")
    @PositiveOrZero(message = "Quantidade de filhos não pode ser negativa")
    Integer children
) {}
