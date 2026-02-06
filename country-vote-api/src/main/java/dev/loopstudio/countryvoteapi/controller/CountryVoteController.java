package dev.loopstudio.countryvoteapi.controller;

import dev.loopstudio.countryvoteapi.dto.NewUserRequest;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.exception.ExceptionResponse;
import dev.loopstudio.countryvoteapi.service.VotedCountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Country vote controller.
 * @author Victor Morales
 */
@RestController
@RequestMapping("/api/v1/votes")
public class CountryVoteController {

    private final Logger logger = LoggerFactory.getLogger(CountryVoteController.class);

    private final VotedCountryService votedCountryService;

    @Autowired
    public CountryVoteController(VotedCountryService votedCountryService) {
        this.votedCountryService = votedCountryService;
    }

    /**
     * Create new user operation.
     *
     * @param newUserRequest the new user request
     */
    @Operation(
            description = "Creates a new user along with his favourite country",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User and vote created successfully",
                            content = @Content(schema = @Schema(implementation = VotedCountryDto.class))),
                    @ApiResponse(responseCode = "400", description = "User email already exists",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Unexpected error",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        votedCountryService.createNewUser(newUserRequest);
    }

}
