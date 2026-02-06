package dev.loopstudio.countryvoteapi.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * The type New user request.
 * @author Victor Morales
 */
public record NewUserRequest(
        @NotEmpty(message = "is required.") String name,
        @NotEmpty(message = "is required.") String email,
        @NotEmpty(message = "is required.") String country) { }
