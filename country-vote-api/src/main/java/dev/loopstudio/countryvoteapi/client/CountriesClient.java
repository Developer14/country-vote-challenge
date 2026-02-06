package dev.loopstudio.countryvoteapi.client;

import java.util.List;

/**
 * The interface Countries client.
 * @author Victor Morales
 */
public interface CountriesClient {

    /**
     * Gets countries.
     *
     * @return the countries
     */
    List<CountryModel> getCountries();
}
