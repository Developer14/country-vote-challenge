package dev.loopstudio.countryvoteapi.controller;

import dev.loopstudio.countryvoteapi.dto.CountryDto;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.service.VotedCountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountriesController.class)
public class CountriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CacheManager cacheManager;

    @MockitoBean
    private VotedCountryService votedCountryService;


    @Test
    public void getMostVoted_shouldReturnStatusNoContent() throws Exception {

        when(votedCountryService.getMostVoted()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/countries/most-voted"))
                //.andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void getMostVoted_shouldReturnStatusOK() throws Exception {

        when(votedCountryService.getMostVoted()).thenReturn(mostVotedList());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/countries/most-voted"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        List<VotedCountryDto> votedCountryDtoList = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Assertions.assertEquals(2, votedCountryDtoList.size());
    }

    @Test
    public void getSimpleCountryList_shouldReturnStatusNoContent() throws Exception {

        when(votedCountryService.getSimpleCountryList()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/countries"))
                .andReturn();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void getSimpleCountryList_shouldReturnStatusOK() throws Exception {

        when(votedCountryService.getSimpleCountryList()).thenReturn(countryDtoList());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/countries"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        List<VotedCountryDto> votedCountryDtoList = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
        });

        Assertions.assertEquals(4, votedCountryDtoList.size());
    }


    private static List<VotedCountryDto> mostVotedList() {
        return List.of(
                new VotedCountryDto("Uruguay", "Montevideo", "Americas", "Sudamerica", 23),
                new VotedCountryDto("Chile", "Santiago", "Americas", "Sudamerica", 20)
        );
    }

    private static List<CountryDto> countryDtoList() {
        return List.of(
                new CountryDto("Uruguay"),
                new CountryDto("Suiza"),
                new CountryDto("Serbia"),
                new CountryDto("Chile")
        );
    }
}
