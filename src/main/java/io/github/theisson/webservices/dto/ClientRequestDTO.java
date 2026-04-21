package io.github.theisson.webservices.dto;

import java.time.LocalDate;

public record ClientRequestDTO(
    String name,
    String cpf,
    Double income,
    LocalDate birthDate,
    Integer children
) {}
