package dev.loopstudio.countryvoteapi.controller;

import dev.loopstudio.countryvoteapi.constant.CountryVoteErrors;
import dev.loopstudio.countryvoteapi.dto.NewUserRequest;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.exception.CountryVoteException;
import dev.loopstudio.countryvoteapi.service.VotedCountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import static dev.loopstudio.countryvoteapi.constant.AppConstants.APPLICATION_ERROR;
import static dev.loopstudio.countryvoteapi.constant.AppConstants.BUSINESS_ERROR;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountryVoteController.class)
public class CountryVoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CacheManager cacheManager;

    @MockitoBean
    private VotedCountryService votedCountryService;

    @Test
    public void createNewUser_shouldReturnStatusCreated() throws Exception {

        String json = objectMapper.writeValueAsString(mockUserRequest());

        doNothing().when(votedCountryService).createNewUser(Mockito.any(NewUserRequest.class));

        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void createNewUser_shouldReturnBadRequestStatus() throws Exception {

        String json = objectMapper.writeValueAsString(mockUserRequest());

        doThrow(new CountryVoteException(
                CountryVoteErrors.USER_DUPLICATED_ERROR.getCode(),
                CountryVoteErrors.USER_DUPLICATED_ERROR.getMsg(), BUSINESS_ERROR)
        ).when(votedCountryService).createNewUser(Mockito.any(NewUserRequest.class));

        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewUser_shouldReturnInternalErrorStatus() throws Exception {

        String json = objectMapper.writeValueAsString(mockUserRequest());

        doThrow(new CountryVoteException(
                CountryVoteErrors.UNEXPECTED_ERROR.getCode(),
                CountryVoteErrors.UNEXPECTED_ERROR.getMsg(), APPLICATION_ERROR)
        ).when(votedCountryService).createNewUser(Mockito.any(NewUserRequest.class));

        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void createNewUser_shouldReturnInvalidRequestStatus() throws Exception {

        String json = objectMapper.writeValueAsString(mockInvalidUserRequest());

        doNothing().when(votedCountryService).createNewUser(Mockito.any(NewUserRequest.class));

        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    private static NewUserRequest mockUserRequest() {
        return  new NewUserRequest("John", "johnd@gmail.com", "Chile");
    }

    private static NewUserRequest mockInvalidUserRequest() {
        return  new NewUserRequest("John", null, "Chile");
    }

}
