package dev.loopstudio.countryvoteapi.service;

import dev.loopstudio.countryvoteapi.dto.CountryDto;
import dev.loopstudio.countryvoteapi.dto.NewUserRequest;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;

import java.util.List;

/**
 * The interface Voted country service.
 * @author Victor Morales
 */
public interface VotedCountryService {

    /**
     * Create new user.
     *
     * @param newUserRequest the new user request
     */
    void createNewUser(NewUserRequest newUserRequest);

    /**
     * Gets most voted.
     *
     * @return the most voted
     */
    List<VotedCountryDto> getMostVoted();

    /**
     * Gets simple country list.
     *
     * @return the simple country list
     */
    List<CountryDto> getSimpleCountryList();
}
