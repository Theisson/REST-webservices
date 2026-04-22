package io.github.theisson.webservices.dto.error;

public record FieldMessage(
    String fieldName,
    String message
) {}
