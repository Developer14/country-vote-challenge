package dev.loopstudio.countryvoteapi.dto;

/**
 * The type Voted country.
 * @author Victor Morales
 */
public record VotedCountryDto(
        String country,
        String capital,
        String region,
        String subregion,
        Integer votes
) {}
