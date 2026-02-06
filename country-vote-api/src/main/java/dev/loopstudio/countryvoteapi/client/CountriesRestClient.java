package dev.loopstudio.countryvoteapi.client;

import dev.loopstudio.countryvoteapi.constant.AppConstants;
import dev.loopstudio.countryvoteapi.constant.CountryVoteErrors;
import dev.loopstudio.countryvoteapi.exception.CountryVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

/**
 * The type Countries rest client.
 * @author Victor Morales
 */
@Component
public class CountriesRestClient implements CountriesClient {

    private static final Logger logger = LoggerFactory.getLogger(CountriesRestClient.class);

    @Value("${countryapi.countriesResource}")
    private String allCountriesResource;

    private final RestClient countriesApiRestClient;

    @Autowired
    public CountriesRestClient(RestClient countriesApiRestClient) {
        this.countriesApiRestClient = countriesApiRestClient;
    }

    @Override
    @Cacheable("countries")
    public List<CountryModel> getCountries() {
        logger.info("Retrieving countries from external api");

        try {

            CountryModel[] countryModels = countriesApiRestClient.get()
                    .uri(allCountriesResource)
                    .retrieve()
                    .body(CountryModel[].class);

            if (ObjectUtils.isEmpty(countryModels)) {
                throw new RuntimeException();
            }
            return Arrays.asList(countryModels);

        } catch (RuntimeException exception) {
            logger.error("ERROR: Failed to retrieve countries.", exception);
            throw new CountryVoteException(
                    CountryVoteErrors.COUNTRIES_API_RESOURCE_ERROR.getCode(),
                    CountryVoteErrors.COUNTRIES_API_RESOURCE_ERROR.getMsg(),
                    AppConstants.APPLICATION_ERROR);
        }
    }

}
