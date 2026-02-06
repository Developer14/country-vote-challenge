package dev.loopstudio.countryvoteapi.service;

import dev.loopstudio.countryvoteapi.client.CountryModel;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class VotedCountryBuilder {

    private static final String NO_CAPITAL_STR = "";

    public static VotedCountryDto buildVotedCountry(String countryName, Map<String, CountryModel> countryMap,
                                                    Map<String, Integer> votesMap) {
        CountryModel countryModel = countryMap.get(countryName);

        return new VotedCountryDto(countryName, extractCapital(countryModel), countryModel.region(),
                countryModel.subregion(), votesMap.get(countryName));
    }

    private static String extractCapital(CountryModel countryModel) {
        return ObjectUtils.isEmpty(countryModel.capital()) ? NO_CAPITAL_STR : countryModel.capital()[0];
    }
}
