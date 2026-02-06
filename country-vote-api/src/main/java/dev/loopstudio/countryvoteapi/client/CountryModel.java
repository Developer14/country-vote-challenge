package dev.loopstudio.countryvoteapi.client;

/**
 * The type Country model.
 * @author Victor Morales
 */
public record CountryModel(
        CountryName name,
        String[] capital,
        String region,
        String subregion
) {

    public record CountryName(
            String common,
            String official
    ) {}
}
