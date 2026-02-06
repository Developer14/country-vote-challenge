package dev.loopstudio.countryvoteapi.service;

import dev.loopstudio.countryvoteapi.client.CountriesClient;
import dev.loopstudio.countryvoteapi.constant.CountryVoteErrors;
import dev.loopstudio.countryvoteapi.client.CountryModel;
import dev.loopstudio.countryvoteapi.dto.NewUserRequest;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.exception.CountryVoteException;
import dev.loopstudio.countryvoteapi.repository.CountryVoteRepository;
import dev.loopstudio.countryvoteapi.repository.projection.CountryVotesProjection;
import dev.loopstudio.countryvoteapi.service.impl.VotedCountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VotedCountryDtoServiceTest {

    @Mock
    private CountryVoteRepository countryVoteRepository;

    @Mock
    private CountriesClient countriesClient;

    @InjectMocks
    private VotedCountryServiceImpl votedCountryService;

    @Test
    public void createNewUser_shouldReturnOk() {

        NewUserRequest newUserRequest = mockUserRequest();
        List<CountryModel> countryModelList = mockCountryModelList();

        when(countriesClient.getCountries()).thenReturn(countryModelList);

        votedCountryService.createNewUser(newUserRequest);
    }

    @Test
    public void createNewUser_shouldThrowCountryNotValidException() {

        NewUserRequest newUserRequest = mockUserRequest();
        List<CountryModel> countryModelList = Collections.emptyList();

        when(countriesClient.getCountries()).thenReturn(countryModelList);

        CountryVoteException countryVoteException = assertThrows(
                CountryVoteException.class, () -> votedCountryService.createNewUser(newUserRequest));

        assertTrue(countryVoteException.getMessage()
                .contains(CountryVoteErrors.COUNTRY_NOT_VALID_ERROR.getMsg()));
    }

    @Test
    public void createNewUser_shouldThrowCountryUserDuplicatedException() {

        NewUserRequest newUserRequest = mockUserRequest();
        List<CountryModel> countryModelList = mockCountryModelList();

        when(countriesClient.getCountries()).thenReturn(countryModelList);
        when(countryVoteRepository.save(ArgumentMatchers.any())).thenThrow(DataIntegrityViolationException.class);

        CountryVoteException countryVoteException = assertThrows(
                CountryVoteException.class, () -> votedCountryService.createNewUser(newUserRequest));

        assertTrue(countryVoteException.getMessage()
                .contains(CountryVoteErrors.USER_DUPLICATED_ERROR.getMsg()));
    }

    @Test
    public void createNewUser_shouldThrowCountryUnexpectedException() {

        NewUserRequest newUserRequest = mockUserRequest();
        List<CountryModel> countryModelList = mockCountryModelList();

        when(countriesClient.getCountries()).thenReturn(countryModelList);
        when(countryVoteRepository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);

        CountryVoteException countryVoteException = assertThrows(
                CountryVoteException.class, () -> votedCountryService.createNewUser(newUserRequest));

        assertTrue(countryVoteException.getMessage()
                .contains(CountryVoteErrors.UNEXPECTED_ERROR.getMsg()));
    }

    @Test
    public void getMostVoted_shouldReturnOk() {
        List<CountryVotesProjection> countryVotesProjectionList = mockCountryVotesProjectionList();
        when(countryVoteRepository.getMostVotedCountries(10)).thenReturn(countryVotesProjectionList);
        when(countriesClient.getCountries()).thenReturn(mockCountryModelList());

        List<VotedCountryDto> votedCountryDtoList = votedCountryService.getMostVoted();

    }

    private List<CountryVotesProjection> mockCountryVotesProjectionList() {
        return List.of(
                buildCountryVotesProjection("Chile", 20),
                buildCountryVotesProjection("Uruguay", 30),
                buildCountryVotesProjection("Suiza", 40)
        );
    }

    private List<CountryModel> mockCountryModelList() {
        return List.of(
                new CountryModel(
                        new CountryModel.CountryName("Chile", "Chile"),
                        new String[]{"Santiago"},
                        "America",
                        "SouthAmerica"),
                new CountryModel(
                        new CountryModel.CountryName("Suiza", "Suiza"),
                        new String[]{"Berna"},
                        "Europe",
                        "Europe"),
                new CountryModel(
                        new CountryModel.CountryName("Uruguay", "Montevideo"),
                        new String[]{"Santiago"},
                        "America",
                        "SouthAmerica")
        );
    }

    private static NewUserRequest mockUserRequest() {
        return  new NewUserRequest("John", "johnd@gmail.com", "Chile");
    }

    private CountryVotesProjection buildCountryVotesProjection(String country, Integer votes) {
        return new CountryVotesProjection() {
            @Override
            public String getCountry() {
                return country;
            }

            @Override
            public Integer getVotes() {
                return votes;
            }
        };
    }
}
