package dev.loopstudio.countryvoteapi.service.impl;

import dev.loopstudio.countryvoteapi.client.CountriesClient;
import dev.loopstudio.countryvoteapi.constant.AppConstants;
import dev.loopstudio.countryvoteapi.client.CountryModel;
import dev.loopstudio.countryvoteapi.constant.CountryVoteErrors;
import dev.loopstudio.countryvoteapi.dto.CountryDto;
import dev.loopstudio.countryvoteapi.dto.NewUserRequest;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.exception.CountryVoteException;
import dev.loopstudio.countryvoteapi.repository.CountryVoteRepository;
import dev.loopstudio.countryvoteapi.repository.projection.CountryVotesProjection;
import dev.loopstudio.countryvoteapi.repository.model.CountryVoteModel;
import dev.loopstudio.countryvoteapi.service.VotedCountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.loopstudio.countryvoteapi.constant.AppConstants.APPLICATION_ERROR;
import static dev.loopstudio.countryvoteapi.constant.AppConstants.BUSINESS_ERROR;
import static dev.loopstudio.countryvoteapi.service.VotedCountryBuilder.buildVotedCountry;

/**
 * The Voted country service implementation.
 * @author Victor Morales
 */
@Service
public class VotedCountryServiceImpl implements VotedCountryService {

    private final Logger logger = LoggerFactory.getLogger(VotedCountryServiceImpl.class);

    private final CountryVoteRepository countryVoteRepository;
    private final CountriesClient countriesClient;

    @Autowired
    public VotedCountryServiceImpl(CountryVoteRepository countryVoteRepository, CountriesClient countriesClient) {
        this.countryVoteRepository = countryVoteRepository;
        this.countriesClient = countriesClient;
    }

    @Override
    public void createNewUser(NewUserRequest newUserRequest) {
        logger.info("Creating user with params: {}", newUserRequest.toString());
        if (getSimpleCountryList().stream()
                .noneMatch(countryDto -> countryDto.name().equals(newUserRequest.country()))) {
            logger.error("ERROR: Unable to create user. Country does not exist.");
            throw new CountryVoteException(
                    CountryVoteErrors.COUNTRY_NOT_VALID_ERROR.getCode(),
                    CountryVoteErrors.COUNTRY_NOT_VALID_ERROR.getMsg(), BUSINESS_ERROR);
        }

        try {

            var countryVoteModel = new CountryVoteModel(
                    newUserRequest.name(),
                    newUserRequest.email(),
                    newUserRequest.country()
            );
            countryVoteRepository.save(countryVoteModel);

            logger.info("User created successfully.");

        }catch (RuntimeException exception) {
            logger.error("ERROR: Unable to create user", exception);
            if(exception instanceof DataIntegrityViolationException) {
                throw new CountryVoteException(CountryVoteErrors.USER_DUPLICATED_ERROR.getCode(),
                        CountryVoteErrors.USER_DUPLICATED_ERROR.getMsg(), BUSINESS_ERROR, exception);
            }
            throw new CountryVoteException(CountryVoteErrors.UNEXPECTED_ERROR.getCode(),
                    CountryVoteErrors.UNEXPECTED_ERROR.getMsg(), APPLICATION_ERROR, exception);
        }
    }

    @Override
    public List<VotedCountryDto> getMostVoted() {
        logger.info("Generating most voted list.");

        List<CountryVotesProjection> all = countryVoteRepository.getMostVotedCountries(AppConstants.MOST_VOTED_LIMIT);
        List<CountryModel> countries = countriesClient.getCountries();

        Map<String, Integer> votesMap = all.stream()
                .collect(Collectors.toMap(CountryVotesProjection::getCountry, CountryVotesProjection::getVotes));

        Map<String, CountryModel> countryMap = countries.stream()
                .collect(Collectors.toMap(
                        countryModel -> countryModel.name().common(),
                        countryModel -> countryModel));

        return votesMap.keySet().stream()
                .map(countryName -> buildVotedCountry(countryName, countryMap, votesMap))
                .sorted(Comparator.comparingInt(VotedCountryDto::votes).reversed())
                .collect(Collectors.toList());
    }


    @Override
    public List<CountryDto> getSimpleCountryList() {
        return countriesClient.getCountries().stream()
                .map(countryModel -> new CountryDto(countryModel.name().common()))
                .toList();
    }
}
